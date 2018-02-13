package com.nzb.rwlock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author M
 * @create 2018/2/13
 */
public class RwNumImpl implements IGoodsNum{
    private GoodsVo gooods;

    private final ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
    private final Lock r = lock.readLock();
    private final Lock w = lock.writeLock();

    public RwNumImpl(GoodsVo gooods) {
        this.gooods = gooods;
    }


    @Override
    public GoodsVo getGoodsNumber() {
        r.lock();
        try {
            try {
                Thread.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return this.gooods;
        } finally {
            r.unlock();
        }
    }

    @Override
    public void setGoodsNumber(int changeNumber) {
        w.lock();
        try {
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            this.gooods.setGoodsVoNumber(changeNumber);
        } finally {
            w.unlock();
        }
    }
}
