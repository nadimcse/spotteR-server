package com.butterfly.spotter.resource;

import com.butterfly.spotter.model.LoginObject;
import com.butterfly.spotter.service.SpotterService;
import org.eclipse.persistence.sessions.Login;

import javax.inject.Inject;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * @author : Nadim
 * @since : 12/19/13
 */
@Path("/login")
public class LoginResource {
    private SpotterService spotterService;

    @Inject
    public LoginResource(SpotterService spotterService) {
        this.spotterService = spotterService;
    }

    @POST
    @Produces(MediaType.TEXT_PLAIN)
    public String getLoginRequest(@FormParam("gcmKey") String gcmKey,
                              @FormParam("callerId") String senderId,
                              @FormParam("password") String password) {
        System.out.println("got it!!!!!!!!!!!!!!!"+  senderId);
        //handle auth logic
        spotterService.handleLogin(new LoginObject(gcmKey, senderId, password));
        return "ok";
    }
}
