package com.butterfly.spotter.model;

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

    public CallerDetailObject(String callerId, String gcmKey) {
        this.callerId = callerId;
        this.gcmKey = gcmKey;
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
}
