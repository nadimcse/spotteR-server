package com.butterfly.spotter.resource;

import com.butterfly.spotter.StringUtils;
import com.butterfly.spotter.service.SpotterService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import static com.google.common.base.Preconditions.checkState;

/**
 * @author : Nadim
 * @since : 1/4/14
 */
@Path("/logout")
public class LogoutResource {
    Logger logger = LoggerFactory.getLogger(this.getClass());

    private SpotterService spotterService;

    @Inject
    public LogoutResource(SpotterService spotterService) {
        this.spotterService = spotterService;
    }

    @POST
    @Produces(MediaType.TEXT_PLAIN)
    public String doLogout(@FormParam("callerId") String callerId) {

        ///TODO:  check authentication
        checkState(StringUtils.isBlank(callerId), "Caller Id is required");
        return spotterService.handleLogout(callerId);
    }
}
