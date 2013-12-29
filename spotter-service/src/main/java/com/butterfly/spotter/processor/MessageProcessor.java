package com.butterfly.spotter.processor;

import com.butterfly.spotter.model.AbstractHttpObject;
import com.butterfly.spotter.model.CallerDetailObject;
import com.butterfly.spotter.model.MessageHttpObject;
import com.butterfly.spotter.service.HttpBroadcastService;
import com.google.common.cache.Cache;
import com.google.gson.Gson;

import javax.inject.Inject;
import java.util.Arrays;
import java.util.List;

/**
 * @author : Nadim
 * @since : 12/14/13
 */
public class MessageProcessor extends AbstractProcessor<AbstractHttpObject> {
    private Cache<Object, List<String>> groupInfoCache;
    private Cache<Object, CallerDetailObject> callerInfoCache;
    private HttpBroadcastService httpBroadcastService;

    //httpresoucehandler
    @Inject
    public MessageProcessor(Cache<Object, List<String>> groupInfoCache,
                            Cache<Object, CallerDetailObject> callerInfoCache,
                            HttpBroadcastService httpBroadcastService) {
        this.groupInfoCache = groupInfoCache;
        this.callerInfoCache = callerInfoCache;
        this.httpBroadcastService = httpBroadcastService;
    }

    @Override
    public void process() {
        MessageHttpObject msgObj = (MessageHttpObject) getContent();
        String json = new Gson().toJson(msgObj);
        System.out.println("json....." + json);
        String receiverGcmKey = callerInfoCache.getIfPresent(msgObj.getReceiverId()).getGcmKey();
        httpBroadcastService.sendRequest(json, Arrays.asList(receiverGcmKey));
        System.out.println("get message obj in processor" + msgObj.getMessage() + "--" + receiverGcmKey);
        //MessageHttpObject messageObj = (MessageHttpObject) getContent();
        //get peerlist
        //getJsonFromObject();
    }
}
