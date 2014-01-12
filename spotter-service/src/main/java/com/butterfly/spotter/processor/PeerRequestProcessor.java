package com.butterfly.spotter.processor;

import com.butterfly.spotter.model.AbstractHttpObject;
import com.butterfly.spotter.model.CallerDetailObject;
import com.butterfly.spotter.model.PendingPeerHttpObject;
import com.butterfly.spotter.service.HttpBroadcastService;
import com.google.common.cache.Cache;

import javax.inject.Inject;
import java.util.Arrays;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * @author: Nadim
 * @since: 12/17/13
 */
public class PeerRequestProcessor extends AbstractProcessor<AbstractHttpObject> {
    private HttpBroadcastService httpBroadcastService;
    private Cache<Object, CallerDetailObject> callerInfoCache;

    @Inject
    private PeerRequestProcessor(HttpBroadcastService httpBroadcastService,
                                 Cache<Object, CallerDetailObject> callerInfoCache) {
        this.httpBroadcastService = httpBroadcastService;
        this.callerInfoCache = callerInfoCache;
    }

    @Override
    public void process() {
        PendingPeerHttpObject pendingPeerObj = (PendingPeerHttpObject) getContent();
        CallerDetailObject callerObj = callerInfoCache.getIfPresent(pendingPeerObj.getReceiverId());
        checkNotNull(callerObj);

        String json = getJsonFromObject();
        httpBroadcastService.sendRequest(json, Arrays.asList(callerObj.getGcmKey()));
        System.out.println("REquest send succesfully");
    }
}
