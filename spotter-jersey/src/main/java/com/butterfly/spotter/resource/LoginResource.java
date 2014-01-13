package com.butterfly.spotter.resource;

import com.butterfly.spotter.StringUtils;
import com.butterfly.spotter.model.LoginObject;
import com.butterfly.spotter.service.SpotterService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
@Path("/login")
public class LoginResource {

    Logger logger = LoggerFactory.getLogger(this.getClass());
    private SpotterService spotterService;

    @Inject
    public LoginResource(SpotterService spotterService) {
        this.spotterService = spotterService;
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public String getLoginRequest(@FormParam("gcmKey") String gcmKey,
                                  @FormParam("callerId") String senderId,
                                  @FormParam("password") String password) {

        checkState(!StringUtils.isBlank(gcmKey), "Google Cloud Service key is required");
        checkState(!StringUtils.isBlank(senderId), "CallerId is required");
        checkState(!StringUtils.isBlank(password), "Password is required");

        return spotterService.handleLogin(new LoginObject(gcmKey, senderId, password));
    }
}
