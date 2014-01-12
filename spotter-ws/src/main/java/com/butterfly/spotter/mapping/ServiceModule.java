package com.butterfly.spotter.mapping;

import com.butterfly.spotter.model.AbstractDbObject;
import com.butterfly.spotter.model.AbstractHttpObject;
import com.butterfly.spotter.model.CallerDetailObject;
import com.butterfly.spotter.service.*;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.inject.*;
import com.google.inject.name.Names;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author : Nadim
 * @since : 12/11/13
 */
public class ServiceModule extends AbstractModule {
    @Override
    protected void configure() {
       bindServices();
       bindCaches();
       bindQueues();
    }

    public void bindServices() {
        bind(Service.class).annotatedWith(Names.named("databaseService")).to(DatabaseService.class);
        bind(Service.class).annotatedWith(Names.named("httpRequestService")).to(HttpRequestService.class);
        //bind(DatabaseService.class).in(Scopes.SINGLETON);
        //bind(HttpRequestService.class).in(Scopes.SINGLETON);

        bind(SpotterService.class).in(Scopes.SINGLETON);
        bind(Services.class).in(Scopes.SINGLETON);
        bind(HttpBroadcastService.class).in(Scopes.SINGLETON);
        bind(ValidationService.class).in(Scopes.SINGLETON);
    }

    public void bindCaches() {
        bind(new TypeLiteral<Cache<Object, CallerDetailObject>>() {
        }).toInstance(CacheBuilder
                .newBuilder()
                .maximumSize(1000)
                .expireAfterWrite(1, TimeUnit.HOURS)
                .concurrencyLevel(10)
                .<Object, CallerDetailObject> build());

        bind(new TypeLiteral<Cache<Object, List<String>>>() {
        }).toInstance(CacheBuilder
                .newBuilder()
                .maximumSize(1000)
                .expireAfterWrite(1, TimeUnit.HOURS)
                .concurrencyLevel(10)
                .<Object, List<String>>build());
    }

    public void bindQueues() {
        final TypeLiteral<SpotterBlockingQueue<AbstractDbObject>> databaseQueueType = new TypeLiteral<SpotterBlockingQueue<AbstractDbObject>>() {
        };
        final TypeLiteral<SpotterBlockingQueue<AbstractHttpObject>> httpQueueType = new TypeLiteral<SpotterBlockingQueue<AbstractHttpObject>>() {
        };
        install(new PrivateModule() {
            @Override
            protected void configure() {
                bind(databaseQueueType).in(Scopes.SINGLETON);
                expose(databaseQueueType);
            }
        });

        install(new PrivateModule() {
            @Override
            protected void configure() {
                bind(httpQueueType).in(Scopes.SINGLETON);
                expose(httpQueueType);
            }
        });
    }


}
