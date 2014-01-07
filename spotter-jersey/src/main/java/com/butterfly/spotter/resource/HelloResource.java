package com.butterfly.spotter.resource;

import com.butterfly.spotter.dao.SpotterDao;
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
    private SpotterDao spotterDao;

    @Inject
    public HelloResource(SpotterService spotterService,
                         SpotterDao spotterDao) {
        this.spotterService = spotterService;
        this.spotterDao = spotterDao;
    }

    @GET
    @Produces(MediaType.TEXT_HTML)
    public Response getReloadPage() {
        spotterDao.saveMessage(new MessageDbObject("0191", "hello"));
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
