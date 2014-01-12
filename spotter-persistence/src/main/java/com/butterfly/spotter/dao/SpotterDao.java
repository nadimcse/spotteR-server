package com.butterfly.spotter.dao;

import com.butterfly.spotter.model.CallerDbObject;
import com.butterfly.spotter.model.MessageDbObject;
import com.butterfly.spotter.model.PendingPeersDbObject;
import com.google.inject.persist.Transactional;

import javax.inject.Inject;
import javax.inject.Provider;
import javax.persistence.EntityManager;
import java.util.List;

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

    public CallerDbObject getCallerObject(String callerId) {
        @SuppressWarnings("unchecked")
        List<CallerDbObject> list = emProvider.get()
                .createQuery("Select c from CallerDbObject c where c.callerId = :callerId")
                .setParameter("callerId", callerId)
                .getResultList();
        return list.get(0);
    }

    public PendingPeersDbObject getPendingPeer(String pendingKey) {
        @SuppressWarnings("unchecked")
        List<PendingPeersDbObject> list = emProvider.get()
                .createQuery("Select p from PendingPeersDbObject p where p.pendingId = :pendingId")
                .setParameter("pendingId", pendingKey)
                .getResultList();
        return list.get(0);
    }

    @Transactional
    public void saveCaller(CallerDbObject callerObj) {
        emProvider.get().persist(callerObj);
    }

    @Transactional
    public void deletePendingPeer(String pendingKey) {
        PendingPeersDbObject pendingPeer = getPendingPeer(pendingKey);
        emProvider.get().remove(pendingPeer);
    }
}
