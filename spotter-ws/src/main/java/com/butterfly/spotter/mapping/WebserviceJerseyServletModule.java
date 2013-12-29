package com.butterfly.spotter.mapping;

import com.butterfly.spotter.resource.HelloResource;
import com.butterfly.spotter.resource.LoginResource;
import com.butterfly.spotter.resource.MessageResource;
import com.google.inject.Scopes;
import com.sun.jersey.guice.JerseyServletModule;
import com.sun.jersey.guice.spi.container.servlet.GuiceContainer;
import com.sun.jersey.spi.container.servlet.ServletContainer;

import java.util.HashMap;
import java.util.Map;

/**
 * @author : Nadim
 * @since : 12/10/13
 */

public class WebserviceJerseyServletModule extends JerseyServletModule {

    @Override
    protected void configureServlets() {

        bind(HelloResource.class).in(Scopes.SINGLETON);
        bind(LoginResource.class).in(Scopes.SINGLETON);
        bind(MessageResource.class).in(Scopes.SINGLETON);

        Map<String, String> params = new HashMap<String, String>();
        params.put(ServletContainer.JSP_TEMPLATES_BASE_PATH, "/WEB-INF/jsp");
        filter("/*").through(GuiceContainer.class, params);
    }
}
