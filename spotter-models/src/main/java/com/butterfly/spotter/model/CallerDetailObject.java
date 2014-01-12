package com.butterfly.spotter.model;

import java.util.List;

/**
 * @author : Nadim
 * @since : 12/10/13
 *
 */
public class CallerDetailObject {
    private String callerId;
    private String gcmKey;
    private String passwordHash;
    private String coordinateX;
    private String coordinateY;
    private List<String> groups;
    private String peers = "";

    public CallerDetailObject(String callerId, String gcmKey, String peers) {
        this.callerId = callerId;
        this.gcmKey = gcmKey;
        this.peers = peers;
    }

    public String getCallerId() {
        return callerId;
    }

    public void setCallerId(String callerId) {
        this.callerId = callerId;
    }

    public String getGcmKey() {
        return gcmKey;
    }

    public void setGcmKey(String gcmKey) {
        this.gcmKey = gcmKey;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public String getCoordinateX() {
        return coordinateX;
    }

    public void setCoordinateX(String coordinateX) {
        this.coordinateX = coordinateX;
    }

    public String getCoordinateY() {
        return coordinateY;
    }

    public void setCoordinateY(String coordinateY) {
        this.coordinateY = coordinateY;
    }

    public List<String> getGroups() {
        return groups;
    }

    public void setGroups(List<String> groups) {
        this.groups = groups;
    }

    public String getPeers() {
        return peers;
    }

    public void setPeers(String peers) {
        this.peers = peers;
    }
}

