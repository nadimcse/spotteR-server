package com.butterfly.spotter.mapping;

import com.butterfly.spotter.model.AbstractHttpObject;
import com.butterfly.spotter.model.StatusCode;
import com.butterfly.spotter.processor.*;
import com.google.inject.AbstractModule;
import com.google.inject.TypeLiteral;
import com.google.inject.multibindings.MapBinder;

/**
 * @author : Nadim
 * @since : 12/14/13
 */
public class ProcessorModule extends AbstractModule {
    @Override
    protected void configure() {
        TypeLiteral<String> keyType = new TypeLiteral<String>() {
        };
        TypeLiteral<AbstractProcessor<AbstractHttpObject>> valueType =
                new TypeLiteral<AbstractProcessor<AbstractHttpObject>>() {
        };

        MapBinder<String, AbstractProcessor<AbstractHttpObject>> mapBinder =
                MapBinder.newMapBinder(binder(), keyType, valueType);

        mapBinder.addBinding(StatusCode.SEND_MESSAGE_REQUEST.name()).to(MessageProcessor.class);
        mapBinder.addBinding(StatusCode.SEND_MAP_REQUEST.name()).to(MapProcessor.class);
        mapBinder.addBinding(StatusCode.PEER_CONFORMATION_REQUEST.name()).to(PeerConfirmationProcess.class);
        mapBinder.addBinding(StatusCode.GROUP_REQUEST.name()).to(GroupRequestProcessor.class);
        mapBinder.addBinding(StatusCode.PEER_REQUEST.name()).to(PeerRequestProcessor.class);
    }
}
