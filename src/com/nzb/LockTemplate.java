package com.nzb;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author M
 * @create 2018/2/13
 */
public class LockTemplate {
    public static void main(String[] args) {
        Lock lock = new ReentrantLock();
        lock.lock();

        try {
//            do some logic
        } finally {
            lock.unlock();
        }
    }
}
