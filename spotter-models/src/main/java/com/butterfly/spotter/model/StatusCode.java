package com.butterfly.spotter.model;

import org.eclipse.persistence.internal.jpa.config.queries.PlsqlRecordImpl;

/**
 * @author : Nadim
 * @since : 12/14/13
 */
public enum StatusCode {
    REGISTRATION_REQUEST (1),
    LOGIN_REQUEST (2),
    SEND_MESSAGE_REQUEST (3),
    SEND_MAP_REQUEST (4),
    BLOCK_REQUEST (5),
    PEERS_REQUEST (6),
    PEER_CONFORMATION_REQUEST(7),
    LOGIN_ERROR(8),
    LOGIN_OK(9),
    LOGOUT_OK(10),
    PEER_REQUEST(11),
    GROUP_REQUEST(12);

    private int statusCode;

    StatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public int getStatusCode() {
        return statusCode;
    }
}
