package com.butterfly.spotter.model;

import java.util.Set;

/**
 * @author: Nadim
 * @since: 1/12/14
 */
public class GroupHttpObject extends AbstractHttpObject {
    private Set<String> peers;

    public GroupHttpObject(String groupId, String status,
                              String senderId, String receiverId,
                              Set<String> peers) {
        super(groupId, status, senderId, receiverId);
        this.peers = peers;
    }

    public Set<String> getPeers() {
        return peers;
    }

    public void setPeers(Set<String> peers) {
        this.peers = peers;
    }
}
