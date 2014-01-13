package com.butterfly.spotter.resource;

import com.butterfly.spotter.StringUtils;
import com.butterfly.spotter.service.SpotterService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

import static com.google.common.base.Preconditions.checkState;

/**
 * User: Nadim
 * @author : 1/4/14
 * @since : 1:32 AM
 */
@Path("/peer")
public class PeerRequestResource {
    Logger logger = LoggerFactory.getLogger(this.getClass());
    private SpotterService spotterService;

    @Inject
    public PeerRequestResource(SpotterService spotterService) {
        this.spotterService = spotterService;
    }

    @POST
    public void processPeerRequest(@FormParam("callerId") String callerId,
                                   @FormParam("peerId") String peerId) {
        checkState(!StringUtils.isBlank(callerId), "Caller id is required");
        checkState(!StringUtils.isBlank(peerId), "Peer id is required");

        ///TODO: handle authentication
        spotterService.handlePeerRequest(callerId, peerId);
    }
}
