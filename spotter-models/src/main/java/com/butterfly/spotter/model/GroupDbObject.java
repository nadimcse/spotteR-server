package com.butterfly.spotter.model;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author: Nadim
 * @since : 1/11/14
 */
@Entity
@Table(name = "group")
public class GroupDbObject extends AbstractDbObject implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "group_seq_gen")
    @SequenceGenerator(name = "group_seq_gen", sequenceName = "spotter_seq_group")
    private long id;
    private String groupId;
    private String peers;

    public GroupDbObject() {

    }

    public GroupDbObject(String groupId, String peers) {
        this.groupId = groupId;
        this.peers = peers;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getPeers() {
        return peers;
    }

    public void setPeers(String peers) {
        this.peers = peers;
    }

    @Override
    public Object getContent() {
        return this;
    }
}
