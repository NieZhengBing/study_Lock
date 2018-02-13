package com.nzb;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author M
 * @create 2018/2/13
 */
public class ConditionTemplate {
    Lock lock = new ReentrantLock();
    Condition condition = lock.newCondition();

    public void waitc() throws InterruptedException {
        try {
            condition.await();
        } finally {
            lock.unlock();
        }
    }

    public void waitnotify() throws InterruptedException {
        lock.lock();
        try {
            condition.signal();
        } finally {
            lock.unlock();
        }
    }



}
