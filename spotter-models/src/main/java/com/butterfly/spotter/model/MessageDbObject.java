package com.butterfly.spotter.model;

import javax.persistence.*;

/**
 * @author : Nadim
 * @since : 12/11/13
 */
@Entity
@Table(name = "message")
public class MessageDbObject extends AbstractDbObject {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "message_seq_gen")
    @SequenceGenerator(name = "message_seq_gen", sequenceName = "spotter_seq_message")
    private long id;
    private String callerId;
    private String message;
    //sync date with android phone
    private String created;

    public MessageDbObject() {

    }

    public MessageDbObject(String callerId, String message) {
        this.callerId = callerId;
        this.message = message;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCallerId() {
        return callerId;
    }

    public void setCallerId(String callerId) {
        this.callerId = callerId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    //@Transient
    @Override
    public MessageDbObject getContent() {
        return this;
    }
}
