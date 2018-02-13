package com.nzb.bq;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author M
 * @create 2018/2/13
 */
public class BlockingQueueLC<T> {
    private List queue = new ArrayList<>();
    private final int limit;
    Lock lock = new ReentrantLock();
    private Condition needNotEmpty = lock.newCondition();
    private Condition netNotFull = lock.newCondition();

    public BlockingQueueLC(int limit) {
        this.limit = limit;
    }

    public void enqueue(T item) throws InterruptedException {
        lock.lock();
        try {
            while (this.queue.size() == this.limit) {
                needNotEmpty.await();
            }
            this.queue.add(item);
            needNotEmpty.signal();
        } finally {
            lock.unlock();
        }
    }

    public T dequeue() throws InterruptedException {
        lock.lock();
        try {
            while (this.queue.size() == 0) {
                needNotEmpty.await();
            }
            netNotFull.signal();
            return (T) this.queue.remove(0);
        } finally {
            lock.unlock();
        }

    }
}
