package com.butterfly.spotter.resource;

import com.butterfly.spotter.StringUtils;
import com.butterfly.spotter.service.SpotterService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;

import java.util.List;

import static com.google.common.base.Preconditions.checkState;
import static com.google.common.base.Preconditions.checkArgument;


/**
 * @author : Nadim
 * @since : 1/11/14
 */
@Path("/group")
public class GroupRequestResource {
    private SpotterService spotterService;

    @Inject
    public GroupRequestResource(SpotterService spotterService) {
        this.spotterService = spotterService;
    }

    @POST
    @Consumes("application/x-www-form-urlencoded")
    @Produces(MediaType.TEXT_PLAIN)
    public String processGroupRequest(MultivaluedMap<String, String> formParams) {
        String senderId = formParams.get("senderId").get(0);
        List<String> groupList = formParams.get("groupList");
        checkState(StringUtils.isBlank(senderId), "Sender id is required");
        checkState(groupList == null || groupList.size() == 0, "Group list id is required");
        checkArgument(groupList != null && groupList.contains(senderId), "Group List must contain senderId inside!");

        ///TODO: Authentication
        return spotterService.handleGroupRequest(senderId, groupList);
    }
}
