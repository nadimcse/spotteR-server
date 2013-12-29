package com.butterfly.spotter.model;

import java.util.Set;

/**
 * @author : Nadim
 * @since : 12/14/13
 */
public class PeersHttpObject extends AbstractHttpObject {
    private Set<String> peerList;

    public PeersHttpObject(String groupId, String status, String senderId, String receiverId, Set<String> peerList) {
        super(groupId, status, senderId, receiverId);
        this.peerList = peerList;
    }

    public Set<String> getPeerList() {
        return peerList;
    }

    public void setPeerList(Set<String> peerList) {
        this.peerList = peerList;
    }
}
