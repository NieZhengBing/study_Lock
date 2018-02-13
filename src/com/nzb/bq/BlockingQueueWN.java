package com.nzb.bq;

import java.util.LinkedList;
import java.util.List;

/**
 * @author M
 * @create 2018/2/13
 */
public class BlockingQueueWN<T> {
    private List queue = new LinkedList<>();
    private final int limit;

    public BlockingQueueWN(int limit) {
        this.limit = limit;
    }

    public synchronized void enqueue(T item) throws InterruptedException {
        while (this.queue.size() == this.limit) {
            wait();
        }
        if (this.queue.size() == 0) {
            notifyAll();
        }
        this.queue.add(item);
    }

    public synchronized T dequeue() throws InterruptedException {
        while (this.queue.size() == 0) {
            wait();
        }
        if (this.queue.size() == this.limit) {
            notifyAll();
        }
        return (T) this.queue.remove(0);
    }

}
