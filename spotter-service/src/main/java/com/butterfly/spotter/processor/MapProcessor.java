package com.butterfly.spotter.processor;

import com.butterfly.spotter.model.AbstractHttpObject;
import com.butterfly.spotter.model.CallerDetailObject;
import com.butterfly.spotter.model.MapHttpObject;
import com.butterfly.spotter.service.HttpBroadcastService;
import com.google.common.cache.Cache;

import javax.inject.Inject;
import java.util.Arrays;
import java.util.List;

/**
 * @author : Nadim
 * @since : 12/14/13
 */
public class MapProcessor extends AbstractProcessor<AbstractHttpObject> {
    private Cache<Object, List<String>> groupInfoCache;
    private Cache<Object, CallerDetailObject> callerInfoCache;
    private HttpBroadcastService httpBroadcastService;

    @Inject
    public MapProcessor(Cache<Object, List<String>> groupInfoCache, Cache<Object, CallerDetailObject> callerInfoCache, HttpBroadcastService httpBroadcastService) {
        this.groupInfoCache = groupInfoCache;
        this.callerInfoCache = callerInfoCache;
        this.httpBroadcastService = httpBroadcastService;
    }

    @Override
    public void process() {
        MapHttpObject mapObj = (MapHttpObject) getContent();
        String receiverGcmKey = callerInfoCache.getIfPresent(mapObj.getReceiverId()).getGcmKey();
        httpBroadcastService.sendRequest(getJsonFromObject(), Arrays.asList(receiverGcmKey));
    }
}