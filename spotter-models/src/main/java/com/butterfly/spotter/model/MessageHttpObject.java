package com.butterfly.spotter.model;

/**
 * @author : Nadim
 * @since : 12/14/13
 */
public class MessageHttpObject extends AbstractHttpObject {

    public MessageHttpObject(String groupId, String status, String senderId, String receiverId, String message) {
        super(groupId, status, senderId, receiverId);
        this.message = message;
    }

    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
