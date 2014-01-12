package com.butterfly.spotter.model;

import com.butterfly.spotter.DateUtils;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @author : Nadim
 * @since : 1/11/14
 */
@Entity
@Table(name = "caller_info")
public class CallerDbObject extends AbstractDbObject implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "caller_info_seq_gen")
    @SequenceGenerator(name = "caller_info_seq_gen", sequenceName = "spotter_seq_caller_info")
    private long id;
    @Version
    long version;
    private String email;
    private String callerId;
    private String firstName;
    private String lastName;
    private String middleName;
    private String hashPassword;
    private String peers = "";
    private String assignedGroup = "";
    private String pendingPeers = "";
    @Temporal(value = TemporalType.TIMESTAMP)
    private Date created;
    @Temporal(value = TemporalType.TIMESTAMP)
    private Date updated;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCallerId() {
        return callerId;
    }

    public void setCallerId(String callerId) {
        this.callerId = callerId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getHashPassword() {
        return hashPassword;
    }

    public void setHashPassword(String hashPassword) {
        this.hashPassword = hashPassword;
    }

    public String getPeers() {
        return peers;
    }

    public void setPeers(String peers) {
        this.peers = peers;
    }

    public String getAssignedGroup() {
        return assignedGroup;
    }

    public void setAssignedGroup(String assignedGroup) {
        this.assignedGroup = assignedGroup;
    }

    public String getPendingPeers() {
        return pendingPeers;
    }

    public void setPendingPeers(String pendingPeers) {
        this.pendingPeers = pendingPeers;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }

    @PrePersist
    protected void onCreate() {
        setCreated(DateUtils.convertDateWithTZ(new Date()));
        setUpdated(DateUtils.convertDateWithTZ(new Date()));
    }

    @PreUpdate
    protected void onUpdate() {
        setUpdated(DateUtils.convertDateWithTZ(new Date()));
    }

    @Version
    public long getVersion() {
        return version;
    }

    public void setVersion(long version) {
        this.version = version;
    }

    @Override
    public Object getContent() {
        return this;
    }
}
