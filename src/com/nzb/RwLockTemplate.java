package com.nzb;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author M
 * @create 2018/2/13
 */
public class RwLockTemplate {
    static final Map<String, String> map = new HashMap<String, String>();
    static ReentrantReadWriteLock reentrantReadWriteLock = new ReentrantReadWriteLock();
    static Lock r = reentrantReadWriteLock.readLock();
    static Lock w = reentrantReadWriteLock.writeLock();

    public void put() {
        w.lock();
        try {

        } finally {
            w.unlock();
        }
    }

    public void get() {
        r.lock();
        try {

        } finally {
            r.unlock();
        }
    }

}
