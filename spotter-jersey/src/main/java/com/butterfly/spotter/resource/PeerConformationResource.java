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
 * @author: Nadim
 * @since: 1/11/14
 */
@Path("/peerConformation")
public class PeerConformationResource {
    Logger logger = LoggerFactory.getLogger(this.getClass());

    private SpotterService spotterService;

    @Inject
    public PeerConformationResource(SpotterService spotterService) {
        this.spotterService = spotterService;
    }

    @POST
    public void processPeerConformation(@FormParam("callerId") String callerId,
                                        @FormParam("groupId") String groupId) {

        checkState(!StringUtils.isBlank(callerId), "Caller id required");
        checkState(!StringUtils.isBlank(groupId), "pending key required");
        ///TODO: authentication
        spotterService.handlePeerConformationRequest(groupId);
    }
}
