package com.butterfly.spotter.processor;

import com.butterfly.spotter.model.AbstractHttpObject;
import com.butterfly.spotter.model.CallerDetailObject;
import com.butterfly.spotter.model.GroupHttpObject;
import com.butterfly.spotter.service.HttpBroadcastService;
import com.google.common.cache.Cache;

import javax.inject.Inject;
import java.util.Arrays;

/**
 * @author: Nadim
 * @since: 1/12/14
 */
public class GroupRequestProcessor extends AbstractProcessor<AbstractHttpObject> {
    private Cache<Object, CallerDetailObject> callerInfoCache;
    private HttpBroadcastService httpBroadcastService;

    @Inject
    public GroupRequestProcessor(Cache<Object, CallerDetailObject> callerInfoCache,
                                 HttpBroadcastService httpBroadcastService) {
        this.callerInfoCache = callerInfoCache;
        this.httpBroadcastService = httpBroadcastService;
    }

    @Override
    public void process() {
        GroupHttpObject groupObj = (GroupHttpObject) getContent();
        String receiverGcmKey = callerInfoCache.getIfPresent(groupObj.getReceiverId()).getGcmKey();
        httpBroadcastService.sendRequest(getJsonFromObject(), Arrays.asList(receiverGcmKey));
    }
}
