package com.butterfly.spotter.model;

/**
 * @author : Nadim
 * @since : 12/14/13
 */
public class MapHttpObject extends AbstractHttpObject {
    private String XCoordinate;
    private String YCoordinate;

    public MapHttpObject(String groupId, String status, String senderId, String receiverId, String XCoordinate, String YCoordinate) {
        super(groupId, status, senderId, receiverId);
        this.XCoordinate = XCoordinate;
        this.YCoordinate = YCoordinate;
    }

    public String getXCoordinate() {
        return XCoordinate;
    }

    public void setXCoordinate(String XCoordinate) {
        this.XCoordinate = XCoordinate;
    }

    public String getYCoordinate() {
        return YCoordinate;
    }

    public void setYCoordinate(String YCoordinate) {
        this.YCoordinate = YCoordinate;
    }
}
