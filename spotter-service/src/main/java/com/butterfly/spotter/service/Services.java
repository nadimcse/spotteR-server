package com.butterfly.spotter.service;

import com.google.inject.name.Named;

import javax.inject.Inject;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author : Nadim
 * @since : 12/11/13
 */
public class Services {

    private Service databaseService;
    private Service httpRequestService;
/*
    private ExecutorService databasePool;
    private ExecutorService httpRequestPool;
*/

    @Inject
    public Services(@Named("databaseService") Service databaseService,
                    @Named("httpRequestService") Service httpRequestService) {
        this.databaseService = databaseService;
        this.httpRequestService = httpRequestService;
    }

    public void startAllServices() {
        databaseService.start();
        httpRequestService.start();
/*
        databasePool = Executors.newFixedThreadPool(3);
        httpRequestPool = Executors.newFixedThreadPool(10);
        databasePool.submit(databaseService);
        httpRequestPool.submit(httpRequestService);
*/
    }
}
