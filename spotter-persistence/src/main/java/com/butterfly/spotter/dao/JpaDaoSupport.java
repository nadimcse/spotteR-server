package com.butterfly.spotter.dao;

import javax.inject.Inject;
import javax.inject.Provider;
import javax.persistence.EntityManager;

/**
 * @author : Nadim
 * @since : 12/12/13
 */
public class JpaDaoSupport {
    private Provider<EntityManager> emProvider;

    @Inject
    public JpaDaoSupport(Provider<EntityManager> emProvider) {
        this.emProvider = emProvider;
    }

    public EntityManager getEntityManager() {
        return emProvider.get();
    }
}
