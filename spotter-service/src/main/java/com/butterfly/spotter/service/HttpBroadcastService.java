package com.butterfly.spotter.service;

import com.google.android.gcm.server.Message;
import com.google.android.gcm.server.MulticastResult;
import com.google.android.gcm.server.Sender;

import java.io.IOException;
import java.util.List;

/**
 * @author : Nadim
 * @since : 12/14/13
 */
public class HttpBroadcastService {
    private String browserLey = "AIzaSyCEMmHuCHru8qDzZpnTxmkXfl9AcZH3XgA";

    public void sendRequest(String jsonRequestObj, List<String> deviceKeys) {
        Sender sender = new Sender(browserLey);
        ////configure it
        Message message = new Message.Builder()
                .collapseKey("1")
                .timeToLive(3)
                .delayWhileIdle(true)
                .addData("message", jsonRequestObj)
                .build();

        MulticastResult result = null;
        try {
            result = sender.send(message, deviceKeys, 1);
            System.out.println(result.toString());

        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
