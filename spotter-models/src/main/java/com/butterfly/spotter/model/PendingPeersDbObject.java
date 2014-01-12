package com.butterfly.spotter.model;

import javax.persistence.*;

/**
 * @author : Nadim
 * @since : 1/11/14
 */
@Entity
@Table(name = "pending_peers")
public class PendingPeersDbObject extends AbstractDbObject {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "pending_peers_seq_gen")
    @SequenceGenerator(name = "pending_peers_seq_gen", sequenceName = "spotter_seq_pending_peers")
    private long id;
    private String pendingId;
    private String senderId;
    private String receiverId;

    public PendingPeersDbObject(String pendingId, String senderId, String receiverId) {
        this.pendingId = pendingId;
        this.senderId = senderId;
        this.receiverId = receiverId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getPendingId() {
        return pendingId;
    }

    public void setPendingId(String pendingId) {
        this.pendingId = pendingId;
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

    @Override
    public Object getContent() {
        return this;
    }
}
