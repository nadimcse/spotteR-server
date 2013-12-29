package com.butterfly.spotter.resource;

import com.butterfly.spotter.model.MessageDbObject;
import com.butterfly.spotter.model.MessageHttpObject;
import com.butterfly.spotter.model.PeerHttpObject;
import com.butterfly.spotter.model.StatusCode;
import com.butterfly.spotter.service.SpotterService;
import com.sun.jersey.api.view.Viewable;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.HashMap;
import java.util.Map;

@Path("/hello")
public class HelloResource {
    private SpotterService spotterService;

    @Inject
    public HelloResource(SpotterService spotterService) {
        this.spotterService = spotterService;
    }

    @GET
    @Produces(MediaType.TEXT_HTML)
    public Response getReloadPage() {
//        spotterService.handleMessage(new MessageHttpObject("111",
//                StatusCode.SEND_MESSAGE_REQUEST.name(), "01911306668", "helo!!!"),
//                new MessageDbObject("0191", "jhello"));
        spotterService.handlePeerRequest(new PeerHttpObject("111",
                StatusCode.PEER_CONFORMATION_REQUEST.name(),
                "111", "22"));
        Map<String, Object> model = new HashMap<>();
        model.put("message", "hello!!!!!!!!!!!!!!!!");
        return Response.ok(new Viewable("/hello", model)).build();
    }

    @POST
    @Produces(MediaType.TEXT_PLAIN)
    public String postRequest(@FormParam("gcmKey") String gcmKey,
                              @FormParam("groupId") String groupId,
                              @FormParam("password") String password) {

        System.out.println("key..........." + gcmKey);
        return "return hello####";

    }
}
