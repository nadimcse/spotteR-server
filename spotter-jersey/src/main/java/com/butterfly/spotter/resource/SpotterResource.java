package com.butterfly.spotter.resource;

import javax.ws.rs.FormParam;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;

/**
 * @author : Nadim
 * @since : 12/10/13
 */
@Path("/")
public class SpotterResource {

    //post request
    public String getSingInRequest(@FormParam("password") String password,
                                   @FormParam("uniqueId") String uniqueId,
                                   @FormParam("gcmKey") String gcmKey) {
        return "";
    }

    //get request
    public String getMessage(@QueryParam("message") String message,
                             @QueryParam("groupId") String groupId,
                             @QueryParam("senderId") String senderId) {
       return "";
    }

    //get request
    public String getLocation(@QueryParam("coordinate") String coordinate,
                              @QueryParam("groupId") String groupId,
                              @QueryParam("uniqueId") String uniqueId) {
      return "";
    }

    //get request
    public String formGroup(@QueryParam("requestedPeers") String requestedPeers,
                            @QueryParam("uniqueId") String uniqueId) {
      return "";
    }


}
