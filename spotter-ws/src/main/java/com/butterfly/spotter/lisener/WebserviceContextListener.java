package com.butterfly.spotter.lisener;

import com.butterfly.spotter.mapping.*;
import com.butterfly.spotter.service.Services;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.persist.PersistService;
import com.google.inject.persist.jpa.JpaPersistModule;
import com.google.inject.servlet.GuiceServletContextListener;

/**
 * @author : Nadim
 * @since : 12/10/13
 */

public class WebserviceContextListener extends GuiceServletContextListener {
    private Injector injector;

    @Override
    protected Injector getInjector() {
        injector = Guice.createInjector(
                new JpaPersistModule("spotter-jpa"),
                new PersistenceModule(),
                new WebserviceJerseyServletModule(),
                new ServiceModule(),
                new ProcessorModule()
        );

        //manually start persistence service
        PersistService persistService = injector.getInstance(PersistService.class);
        persistService.start();

        Services services = injector.getInstance(Services.class);
        services.startAllServices();
        return injector;
    }
}
