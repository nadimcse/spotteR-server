package com.butterfly.spotter.model;

/**
 * @author : Nadim
 * @since : 12/11/13
 */
public class BroadcastMsgObject {
    private String receiverGcmKey;
    private String message;
    private String receiverId;

    public BroadcastMsgObject() {

    }

    public BroadcastMsgObject(String receiverId, String message) {
        this.receiverId = receiverId;
        this.message = message;
    }

    public String getReceiverGcmKey() {
        return receiverGcmKey;
    }

    public void setReceiverGcmKey(String receiverGcmKey) {
        this.receiverGcmKey = receiverGcmKey;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(String receiverId) {
        this.receiverId = receiverId;
    }
}
