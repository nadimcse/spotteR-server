package com.butterfly.spotter.service;

import com.butterfly.spotter.dao.SpotterDao;
import com.butterfly.spotter.model.AbstractDbObject;
import com.butterfly.spotter.model.MessageDbObject;
import com.google.inject.persist.UnitOfWork;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;

/**
 * @author : Nadim
 * @since : 12/11/13
 */
public class DatabaseService implements Service,  Runnable {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private SpotterBlockingQueue<AbstractDbObject> databaseJobQueue;
    private SpotterDao spotterDao;
    private UnitOfWork unitOfWork;

    @Inject
    public DatabaseService(SpotterBlockingQueue<AbstractDbObject> databaseJobQueue,
                           SpotterDao spotterDao, UnitOfWork unitOfWork) {
        this.databaseJobQueue = databaseJobQueue;
        this.spotterDao = spotterDao;
        this.unitOfWork = unitOfWork;
    }

    @Override
    public void start() {
        Thread thread = new Thread(this);
        thread.setName("database_service");
        thread.start();
    }

    @Override
    public void run() {
        while (true) {
            try {
                System.out.println("executing....database");
                AbstractDbObject abstractDbObject = databaseJobQueue.remove();

                unitOfWork.begin();
                spotterDao.saveMessage((MessageDbObject) abstractDbObject.getContent());

                System.out.println("message database service");
                  logger.info("message {} --- {}", abstractDbObject);
                 System.out.println("message from database");
            } catch (Exception e) {
                logger.error("Thread: {} got interrupted", Thread.currentThread(), e);
                Thread.currentThread().interrupt();
                return;
            } finally {
                unitOfWork.end();
            }
        }
    }
}
