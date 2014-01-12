package com.butterfly.spotter.processor;

import com.butterfly.spotter.model.AbstractHttpObject;
import com.butterfly.spotter.model.CallerDetailObject;
import com.butterfly.spotter.model.PeerConfirmationHttpObject;
import com.butterfly.spotter.service.HttpBroadcastService;
import com.google.common.cache.Cache;

import java.util.Arrays;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * @author: Nadim
 * @since: 1/11/14
 */
public class PeerConfirmationProcess extends AbstractProcessor<AbstractHttpObject> {
    private HttpBroadcastService httpBroadcastService;
    private Cache<Object, CallerDetailObject> callerInfoCache;

    public PeerConfirmationProcess(HttpBroadcastService httpBroadcastService, Cache<Object, CallerDetailObject> callerInfoCache) {
        this.httpBroadcastService = httpBroadcastService;
        this.callerInfoCache = callerInfoCache;
    }

    @Override
    public void process() {
        PeerConfirmationHttpObject peerConfirmObj = (PeerConfirmationHttpObject) getContent();

        CallerDetailObject callerObj = callerInfoCache.getIfPresent(peerConfirmObj.getReceiverId());
        checkNotNull(callerObj);

        String json = getJsonFromObject();
        httpBroadcastService.sendRequest(json, Arrays.asList(callerObj.getGcmKey()));
        System.out.println("REquest send succesfully");
    }
}
