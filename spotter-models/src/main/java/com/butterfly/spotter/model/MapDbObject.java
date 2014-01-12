package com.butterfly.spotter.model;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author : Nadim
 * @since : 12/13/13
 */

@Entity
@Table(name = "map")
public class MapDbObject extends AbstractDbObject implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "map_seq_gen")
    @SequenceGenerator(name = "map_seq_gen", sequenceName = "spotter_seq_map")
    private long id;
    private int xCoordinate;
    private int yCoordinate;
    private String callerId;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getxCoordinate() {
        return xCoordinate;
    }

    public void setxCoordinate(int xCoordinate) {
        this.xCoordinate = xCoordinate;
    }

    public int getyCoordinate() {
        return yCoordinate;
    }

    public void setyCoordinate(int yCoordinate) {
        this.yCoordinate = yCoordinate;
    }

    public String getCallerId() {
        return callerId;
    }

    public void setCallerId(String callerId) {
        this.callerId = callerId;
    }

    @Override
    public MapDbObject getContent() {
       return this;
    }
}
