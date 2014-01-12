package com.butterfly.spotter.model;

/**
 * @author: Nadim
 * @since: 1/11/14
 */
public class PeerConfirmationHttpObject extends AbstractHttpObject {
    public PeerConfirmationHttpObject(String groupId, String status, String senderId, String receiverId) {
        super(groupId, status, senderId, receiverId);
    }
}
