package com.butterfly.spotter.service;

import com.butterfly.spotter.StringUtils;
import com.butterfly.spotter.dao.SpotterDao;
import com.butterfly.spotter.model.*;
import com.google.common.cache.Cache;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.google.gson.Gson;

import javax.inject.Inject;
import java.util.*;

import static com.google.common.base.Preconditions.checkNotNull;
import static com.google.common.base.Preconditions.checkState;

/**
 * @author : Nadim
 * @since : 12/11/13
 */

public class SpotterService {
    private ValidationService validationService;
    private SpotterDao spotterDao;
    private SpotterBlockingQueue<AbstractDbObject> databaseJobQueue;
    private SpotterBlockingQueue<AbstractHttpObject> httpJobQueue;
    private Cache<Object, CallerDetailObject> callerInfoCache;
    private Cache<Object, List<String>> groupInfoCache;

    @Inject
    public SpotterService(SpotterBlockingQueue<AbstractDbObject> databaseJobQueue,
                          SpotterBlockingQueue<AbstractHttpObject> httpJobQueue,
                          Cache<Object, CallerDetailObject> callerInfoCache,
                          Cache<Object, List<String>> groupInfoCache,
                          ValidationService validationService,
                          SpotterDao spotterDao) {
        this.databaseJobQueue = databaseJobQueue;
        this.httpJobQueue = httpJobQueue;
        this.callerInfoCache = callerInfoCache;
        this.groupInfoCache = groupInfoCache;
        this.validationService = validationService;
        this.spotterDao = spotterDao;
    }

    public void handleMessage(String message, String groupId) {
        String senderId = "";
        String receiverId = "";
        Set<String> receiverSet;
        if (groupId.contains("@")) {
            senderId = groupId.split("@")[0];
            //receiverId = groupId.split("@")[1];
            receiverSet = Sets.newHashSet(groupId.split("@")[1]);
        } else {
            receiverSet = new HashSet<>(groupInfoCache.getIfPresent(groupId));
            receiverSet.remove(senderId);
            checkState(receiverSet.size() == 0, "Group list must not be empty!");
        }
        sentBroadcastMessageRequestToPeers(groupId, senderId, receiverSet, message);
    }

    private void sentBroadcastMessageRequestToPeers(String groupId, String senderId,
                                                    Set<String> receiverSet, String message) {
        for (String receiverId : receiverSet) {
            try {
                databaseJobQueue.put(new MessageDbObject(senderId, message));
                httpJobQueue.put(new MessageHttpObject(groupId, StatusCode.SEND_MESSAGE_REQUEST.name(),
                        senderId, receiverId, message));
            } catch (InterruptedException e) {
                ///consider it carefully
                Thread.currentThread().interrupt();
                return;
            }
        }
    }

    public void handlePeerRequest(String callerId, String peerId) {
        String pendingId = UUID.randomUUID().toString();
        try {
            databaseJobQueue.put(new PendingPeersDbObject(pendingId, callerId, peerId));
            httpJobQueue.put(new PendingPeerHttpObject(
                    pendingId, StatusCode.PEER_REQUEST.name(),
                    callerId, peerId)
            );
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return;
        }

    }

    public String handleLogin(LoginObject login) {

        boolean authorized = validationService.validateSubject(login);
        if (!authorized) {
            return new Gson().toJson(new LoginResponseObject(StatusCode.LOGIN_ERROR.name()));
        }

        CallerDbObject callerObject = spotterDao.getCallerObject(login.getCallerId());
        callerInfoCache.put(callerObject.getCallerId(), new CallerDetailObject(callerObject.getCallerId(),
                login.getGcmKey(), callerObject.getPeers()));

        return new Gson().toJson(
                new LoginResponseObject(
                        StatusCode.LOGIN_OK.name(),
                        new HashMap<String, String>(),
                        login.generateAuthKey(callerObject.getCreated().getTime()),
                        new HashSet<String>()
                )
        );
    }

    public String handleLogout(String callerId) {
        callerInfoCache.invalidate(callerId);
        System.out.println("caller id exists..........." + callerInfoCache.getIfPresent(callerId));
        //TODO: delete callerid from group cache info
        return StatusCode.LOGOUT_OK.name();
    }

    public void handlePeerConformationRequest(String pendingKey) {
        PendingPeersDbObject pendingObj = spotterDao.getPendingPeer(pendingKey);
        //add peer
        addPeer(pendingObj.getSenderId(), pendingObj.getReceiverId());
        addPeer(pendingObj.getReceiverId(), pendingObj.getSenderId());
        spotterDao.deletePendingPeer(pendingKey);
        try {
            httpJobQueue.put(new PeerConfirmationHttpObject("", StatusCode.PEER_CONFORMATION_REQUEST.name(), "", pendingObj.getSenderId()));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void addPeer(String callerId, String peerId) {
        CallerDbObject callerObj = spotterDao.getCallerObject(callerId);
        callerObj.setPeers(callerObj.getPeers() + ":" + peerId);
        spotterDao.saveCaller(callerObj);
    }


    public String handleGroupRequest(String senderId, List<String> groupList) {
        Set<String> actualPeers = getActualPeers(senderId, groupList);

        //addd in group cache
        String groupId = UUID.randomUUID().toString();
        groupInfoCache.put(groupId, new ArrayList<>(actualPeers));

        //send to broadcast request to other peers
        try {
            databaseJobQueue.put(new GroupDbObject(groupId, StringUtils.getPeerStrFromPeerSet(actualPeers)));
            broadcastGroupRequestsToPeers(actualPeers, groupId, senderId);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return groupId;

    }

    private Set<String> getActualPeers(String senderId, List<String> groupList) {
        CallerDbObject callerObj = spotterDao.getCallerObject(senderId);
        Set<String> callerGroupSet = StringUtils.extractStringIntoList(callerObj.getPeers());
        Set<String> actualPeers = Sets.union(callerGroupSet, new HashSet<>(groupList));

        if (actualPeers.size() == 0) {
            throw new RuntimeException("Invalid peer list");
        }
        return actualPeers;
    }

    private void broadcastGroupRequestsToPeers(Set<String> actualPeers,
                                               String groupId, String senderId) {
        String initiator = senderId;
        for (String peer : actualPeers) {
            if (initiator.equals(peer)) {
                continue;
            }
            Set<String> modifiedPeerSet = ImmutableSet.copyOf(actualPeers);
            modifiedPeerSet.remove(peer);
            try {
                httpJobQueue.put(new GroupHttpObject(groupId, StatusCode.GROUP_REQUEST.name(),
                        senderId, peer, modifiedPeerSet));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void handleMapService(String senderId, String coordinateX,
                                 String coordinateY) {
        ImmutableSet<Object> connectedPeers = getConnectedPeers(senderId);
        for (Object o : connectedPeers) {
            try {
                httpJobQueue.put(new MapHttpObject("", StatusCode.SEND_MAP_REQUEST.name(), senderId,
                        (String) o, coordinateX, coordinateY));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private ImmutableSet<Object> getConnectedPeers(String callerId) {
        CallerDetailObject callerObj = callerInfoCache.getIfPresent(callerId);
        checkNotNull(callerObj);

        Set<String> peerSet = StringUtils.extractStringIntoList(callerObj.getPeers());
        checkState(peerSet == null || peerSet.size() == 0, "Peer set is empty");
        ///broadcast Location
        return callerInfoCache.getAllPresent(peerSet).keySet();
    }

    public MapResponseObject getUpdatedPeersLocation(String senderId) {
        CallerDetailObject callerObj = callerInfoCache.getIfPresent(senderId);
        checkNotNull(callerObj);

        Set<String> peerSet = StringUtils.extractStringIntoList(callerObj.getPeers());
        checkState(peerSet == null || peerSet.size() == 0, "Peer set is empty");
        ///broadcast Location
        Map<String, LocationObject> locationMap = new HashMap<>();

        ImmutableSet<Map.Entry<Object, CallerDetailObject>> set = callerInfoCache.getAllPresent(peerSet).entrySet();
        for (Map.Entry<Object, CallerDetailObject> e : set) {
            locationMap.put((String) e.getKey(),
                    new LocationObject(e.getValue().getCoordinateX(), e.getValue().getCoordinateY()));
        }
        return new MapResponseObject(locationMap);
    }

}
