package com.butterfly.spotter.processor;

import com.butterfly.spotter.model.AbstractHttpObject;
import com.butterfly.spotter.model.PeerHttpObject;
import com.butterfly.spotter.service.HttpBroadcastService;

import javax.inject.Inject;
import java.util.Arrays;

/**
 * Created by IntelliJ IDEA.
 * User: Nadim
 * Date: 12/17/13
 * Time: 8:38 AM
 * To change this template use File | Settings | File Templates.
 */
public class PeerProcessor extends AbstractProcessor<AbstractHttpObject> {
    private HttpBroadcastService httpBroadcastService;

    @Inject
    private PeerProcessor(HttpBroadcastService httpBroadcastService) {
           this.httpBroadcastService = httpBroadcastService;
    }
    @Override
    public void process() {
        String json = getJsonFromObject();
        System.out.println("json....." + json);
        httpBroadcastService.sendRequest(json, Arrays.asList("APA91bGLbr5sIHm70LoWSxmVhrTpcBT_ns7EyeXEG0FMG7qngiz0swH78T-Tgvov64v3cJBoRD1PCE8OrathpPeLFz_5_UXp0mx5E8OYsXzWmEKcLhpKNdAnuPoKs9VCn_-j7gwtDEvzfni6dS3SDvnCNZtPM2y-ry5RPX_n4GnzQEgLLXQZoGA"));
        System.out.println("REquest send succesfully");
    }
}
