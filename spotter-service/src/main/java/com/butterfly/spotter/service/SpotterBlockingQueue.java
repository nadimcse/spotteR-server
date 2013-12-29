package com.butterfly.spotter.service;

import com.google.common.base.Predicate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * This file is copied from GUAVA library.
 */

public class SpotterBlockingQueue<E> {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * The queued items
     */
    private final Queue<E> items;

    /**
     * Main lock guarding all access
     */
    final ReentrantLock lock;
    /**
     * Condition for waiting takes
     */
    private final Condition notEmpty;

    public SpotterBlockingQueue() {
        this.items = new LinkedList<>();
        /*
         * if {@code true} then queue accesses for threads blocked on insertion or removal, are processed in FIFO order;
         * if {@code false} the access order is unspecified.
         */

        lock = new ReentrantLock(false);
        notEmpty = lock.newCondition();

    }

    /**
     * Inserts element at current put position, advances, and signals. Call only when holding lock.
     */
    private void insert(E x) {
        items.add(x);
        //writeFile();
        notEmpty.signal();
    }

    /**
     * Extracts element at current take position, advances, and signals. Call only when holding lock.
     */
    private E extract() {
        E x = items.poll();
        //writeFile();
        return x;
    }

    public void put(E e) throws InterruptedException {
        checkNotNull(e);
        final ReentrantLock lock = this.lock;
        lock.lockInterruptibly();
        try {
            insert(e);
        } finally {
            lock.unlock();
        }
    }

    public E remove() throws InterruptedException {
        final ReentrantLock lock = this.lock;
        lock.lockInterruptibly();
        try {
            while (items.isEmpty()) {
                notEmpty.await();
            }
            return extract();
        } finally {
            lock.unlock();
        }
    }

    public void remove(Predicate<E> condition) throws InterruptedException {
        List<E> removableObjects = new ArrayList<>();

        final ReentrantLock lock = this.lock;
        lock.lockInterruptibly();
        try {
            for (E item : items) {
                if (condition.apply(item)) {
                    removableObjects.add(item);
                }
            }

            items.removeAll(removableObjects);
        } finally {
            lock.unlock();
        }
    }
}
