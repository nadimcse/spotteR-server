package com.butterfly.spotter.service;

import com.butterfly.spotter.model.AbstractHttpObject;
import com.butterfly.spotter.processor.AbstractProcessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import java.util.Map;

/**
 * @author : Nadim
 * @since : 12/11/13
 */

public class HttpRequestService implements Service, Runnable {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private SpotterBlockingQueue<AbstractHttpObject> jobQueue;
    private final Map<String, AbstractProcessor<AbstractHttpObject>> map;

    @Inject
    public HttpRequestService(SpotterBlockingQueue<AbstractHttpObject> jobQueue,
                              Map<String, AbstractProcessor<AbstractHttpObject>> map) {
        this.jobQueue = jobQueue;
        this.map = map;
    }

    @Override
    public void start() {
        Thread thread = new Thread(this);
        thread.setName("request_broadcast_service");
        thread.start();
    }

    @Override
    public void run() {
        while (true) {
            try {
                //apply logic
                AbstractHttpObject httpObject = jobQueue.remove();
                System.out.println("object by current thread...." + Thread.currentThread().getId() + "----" + Thread.currentThread().getName());
                //httpObject.getStatus();
                AbstractProcessor<AbstractHttpObject> processor = map.get(httpObject.getStatus());
                processor.setContent(httpObject);
                processor.process();
                logger.info("message {} --- {}", httpObject.getStatus());
                System.out.println("message from broadcast" + httpObject.getStatus());
            } catch (InterruptedException e) {
                logger.error("Thread: {} got interrupted", Thread.currentThread(), e);
                Thread.currentThread().interrupt();
                return;
            }
        }
    }
}
