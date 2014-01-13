package com.butterfly.spotter.resource;

import com.butterfly.spotter.StringUtils;
import com.butterfly.spotter.service.SpotterService;

import javax.inject.Inject;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import static com.google.common.base.Preconditions.checkState;

/**
 * @author : Nadim
 * @since : 12/19/13
 */
@Path("/message")
public class MessageResource {
    private SpotterService spotterService;

    @Inject
    public MessageResource(SpotterService spotterService) {
        this.spotterService = spotterService;
    }

    @POST
    @Produces(MediaType.TEXT_PLAIN)
    public String getMessageRequest(@FormParam("message") String message,
                                     @FormParam("groupId") String groupId,
                                     @FormParam("authKey") String authKey) {

        checkState(!StringUtils.isBlank(message), "Message is empty");
        checkState(!StringUtils.isBlank(groupId), "Group id is empty");
        checkState(!StringUtils.isBlank(authKey), "authKey is empty");

        ///authenticate using authkey
        System.out.println("message@................" + message);
        spotterService.handleMessage(message, groupId);
        return "sent";

    }
}
