package com.butterfly.spotter.model;

/**
 * @author: Nadim
 * @since : 12/14/13
 */
public class SenderHttpObject extends AbstractHttpObject {
  //  private String senderId;

    public SenderHttpObject(String groupId, String status, String senderId, String receiverId) {
        super(groupId, status, senderId, receiverId);
       // this.senderId = senderId;
    }

//    public String getSenderId() {
//        return senderId;
//    }
//
//    public void setSenderId(String senderId) {
//        this.senderId = senderId;
//    }
}
