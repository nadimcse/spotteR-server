package com.butterfly.spotter.resource;

import com.butterfly.spotter.StringUtils;
import com.butterfly.spotter.model.MapResponseObject;
import com.butterfly.spotter.service.SpotterService;
import com.google.gson.Gson;

import javax.inject.Inject;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import static com.google.common.base.Preconditions.checkState;


/**
 * @author: Nadim
 * @since: 1/4/14
 */
@Path("/map")
public class MapRequestResource {
    private SpotterService spotterService;

    @Inject
    public MapRequestResource(SpotterService spotterService) {
        this.spotterService = spotterService;
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public String processMapRequest(@FormParam("senderId") String senderId,
                                    @FormParam("coordinateX") String coordinateX,
                                    @FormParam("coordinateY") String coordinateY) {
        checkState(!StringUtils.isBlank(senderId), "Sender Id is empty");
        checkState(!StringUtils.isBlank(coordinateX), "CoordinateX is empty");
        checkState(!StringUtils.isBlank(coordinateY), "CoordinateY is empty");

        ///TODO: authentication
        spotterService.handleMapService(senderId, coordinateX, coordinateY);
        //update other peers location
        MapResponseObject responseObj = spotterService.getUpdatedPeersLocation(senderId);
        return new Gson().toJson(responseObj);
    }
}
