package com.nzb.rwlock;

import java.util.Random;
import java.util.concurrent.CountDownLatch;

/**
 * @author M
 * @create 2018/2/13
 */
public class Test {

    static final int threadRatio =  10;
    static final int threadBaseCount = 3;
    static CountDownLatch countDownLatch = new CountDownLatch(1);

    private static class ReadThread implements Runnable {

        private IGoodsNum goodsNum;

        public ReadThread(IGoodsNum goodsNum) {
            this.goodsNum = goodsNum;
        }

        @Override
        public void run() {
            try {
                countDownLatch.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            long start = System.currentTimeMillis();
            for (int i = 0; i < 100; i++) {
                goodsNum.getGoodsNumber();
            }
            long duration = System.currentTimeMillis() - start;
            System.out.println(Thread.currentThread().getName() + " spend time: " + duration + "ms");
        }
    }

    private static class WriteThread implements Runnable {
        private IGoodsNum goodsNum;

        public WriteThread(IGoodsNum goodsNum) {
            this.goodsNum = goodsNum;
        }

        @Override
        public void run() {
            try {
                countDownLatch.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            long start = System.currentTimeMillis();
            Random r = new Random();
            for (int i = 0; i < 10; i++) {
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                goodsNum.setGoodsNumber(r.nextInt(10));
            }
            long duration = System.currentTimeMillis() - start;
            System.out.println(Thread.currentThread().getName() + " write to db cost time: " + duration + "ms");
        }
    }

    public static void main(String[] args) {
        GoodsVo goodsVo = new GoodsVo("goods001", 100000, 10000);
        RwNumImpl goodsNum = new RwNumImpl(goodsVo);
        for (int i = 0; i < threadBaseCount * threadRatio; i++) {
            Thread readT = new Thread(new ReadThread(goodsNum));
            readT.start();
        }
        for (int i = 0; i < threadBaseCount; i++) {
            Thread writeT = new Thread(new WriteThread(goodsNum));
            writeT.start();
        }
        countDownLatch.countDown();
    }
}

