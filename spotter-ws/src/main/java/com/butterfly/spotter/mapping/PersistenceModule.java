package com.butterfly.spotter.mapping;

import com.butterfly.spotter.dao.SpotterDao;
import com.google.inject.AbstractModule;
import com.google.inject.Scopes;

/**
 * @author : Nadim
 * @since : 12/12/13
 */
public class PersistenceModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(SpotterDao.class).in(Scopes.SINGLETON);
      //  bind(JpaDaoSupport.class).in(Scopes.SINGLETON);
    }
}
