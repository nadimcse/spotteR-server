package com.butterfly.spotter.model;

import java.util.Map;
import java.util.Set;

/**
 * @author : Nadim
 * @since : 1/11/14
 */
public class LoginResponseObject {
    private String status;
    private Map<String, String> urlMap;
    private String authKey;
    private Set<String> pendingPeerRequests;

    public LoginResponseObject(String status) {
        this.status = status;
    }

    public LoginResponseObject(String status, Map<String, String> urlMap,
                               String authKey, Set<String> pendingPeerRequests) {
        this.status = status;
        this.urlMap = urlMap;
        this.authKey = authKey;
        this.pendingPeerRequests = pendingPeerRequests;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Map<String, String> getUrlMap() {
        return urlMap;
    }

    public void setUrlMap(Map<String, String> urlMap) {
        this.urlMap = urlMap;
    }

    public String getAuthKey() {
        return authKey;
    }

    public void setAuthKey(String authKey) {
        this.authKey = authKey;
    }

    public Set<String> getPendingPeerRequests() {
        return pendingPeerRequests;
    }

    public void setPendingPeerRequests(Set<String> pendingPeerRequests) {
        this.pendingPeerRequests = pendingPeerRequests;
    }
}
