package com.butterfly.spotter.model;

/**
 * @author : Nadim
 * @since : 12/14/13
 */


public abstract class AbstractHttpObject {
    private String groupId;
    private String status;
    public String senderId;
    public String receiverId;


    protected AbstractHttpObject(String groupId, String status,
                                 String senderId, String receiverId) {
        this.groupId = groupId;
        this.status = status;
        this.senderId = senderId;
        this.receiverId = receiverId;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSenderId() {
        return senderId;
    }

    public void setSenderId(String senderId) {
        this.senderId = senderId;
    }

    public String getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(String receiverId) {
        this.receiverId = receiverId;
    }
}
