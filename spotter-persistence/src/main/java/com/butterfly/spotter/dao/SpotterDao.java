package com.butterfly.spotter.dao;

import com.butterfly.spotter.model.MessageDbObject;
import com.google.inject.persist.Transactional;

import javax.inject.Inject;
import javax.inject.Provider;
import javax.persistence.EntityManager;

/**
 * @author : Nadim
 * @since : 12/12/13
 */
public class SpotterDao {
    private Provider<EntityManager> emProvider;

    @Inject
    public SpotterDao(Provider<EntityManager> emProvider) {
        this.emProvider = emProvider;
    }

    @Transactional
    public void saveMessage(MessageDbObject message) {
        emProvider.get().persist(message);

    }
}
