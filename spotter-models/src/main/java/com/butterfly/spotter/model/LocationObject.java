package com.butterfly.spotter.model;

/**
 * @author: Nadim
 * @since: 1/12/14
 */
public class LocationObject {
    private String coordinateX;
    private String coordinateY;

    public LocationObject(String coordinateX, String coordinateY) {
        this.coordinateX = coordinateX;
        this.coordinateY = coordinateY;
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
