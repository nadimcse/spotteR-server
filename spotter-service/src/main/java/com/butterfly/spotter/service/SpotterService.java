package com.butterfly.spotter.service;

import com.butterfly.spotter.model.*;
import com.google.common.cache.Cache;

import javax.inject.Inject;
import java.util.List;

/**
 * @author : Nadim
 * @since : 12/11/13
 */

public class SpotterService {
    private SpotterBlockingQueue<AbstractDbObject> databaseJobQueue;
    private SpotterBlockingQueue<AbstractHttpObject> httpJobQueue;
    private Cache<Object, CallerDetailObject> callerInfoCache;

    @Inject
    public SpotterService(SpotterBlockingQueue<AbstractDbObject> databaseJobQueue,
                          SpotterBlockingQueue<AbstractHttpObject> httpJobQueue,
                          Cache<Object, CallerDetailObject> callerInfoCache) {
        this.databaseJobQueue = databaseJobQueue;
        this.httpJobQueue = httpJobQueue;
        this.callerInfoCache = callerInfoCache;
    }

   // @Inject
    public void handleMessage(String message, String groupId) {
        String senderId = "";
        String receiverId = "";
        if (groupId.contains("@")) {
               senderId = groupId.split("@")[0];
               receiverId = groupId.split("@")[1];
        }
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

    public void handlePeerRequest(PeerHttpObject msgHttpObj) {
        try {
            httpJobQueue.put(msgHttpObj);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public void handleLogin(LoginObject login) {
         callerInfoCache.put(login.getCallerId(), new CallerDetailObject(login.getCallerId(),
                 login.getGcmKey()));

        System.out.println("gcmkey..." + callerInfoCache.getIfPresent(login.getCallerId()).getGcmKey());

    }
}
