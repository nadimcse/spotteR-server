package com.butterfly.spotter.model;

import java.util.Map;

/**
 * @author: Nadim
 * @since: 1/12/14
 */
public class MapResponseObject {
    private Map<String, LocationObject> peerLocationMap;

    public MapResponseObject(Map<String, LocationObject> peerLocationMap) {
        this.peerLocationMap = peerLocationMap;
    }

    public Map<String, LocationObject> getPeerLocationMap() {
        return peerLocationMap;
    }

    public void setPeerLocationMap(Map<String, LocationObject> peerLocationMap) {
        this.peerLocationMap = peerLocationMap;
    }
}
