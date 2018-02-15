package com.nzb.bq;
/**
 * @author M
 * @create 2018/2/13
 */
public class BqTest {

    public static void main(String[] args) {
        BlockingQueueLC<Integer> bq = new BlockingQueueLC<>(10);
        ThreadPush threadA = new ThreadPush(bq);
        threadA.setName("push");
        ThreadPop threadB = new ThreadPop(bq);
        threadB.setName("pop");
        threadB.start();
        threadA.start();
    }
    private  static class ThreadPush extends Thread {
        BlockingQueueLC<Integer> bq;

        public ThreadPush(BlockingQueueLC<Integer> bq) {
            this.bq = bq;
        }
        @Override
        public void run() {
            String threadName = Thread.currentThread().getName();
            int i = 20;
            while (i > 0) {
                try {
                    Thread.sleep(500);
                    System.out.println(" i = " + i + " will push");
                    bq.enqueue(i--);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private static class ThreadPop extends Thread {
        BlockingQueueLC<Integer> bq;

        public ThreadPop(BlockingQueueLC<Integer> bq) {
            this.bq = bq;
        }

        @Override
        public void run() {
            try {
                Thread.sleep(1000);
                System.out.println(Thread.currentThread().getName() + " will pop......");
                Integer i = bq.dequeue();
                System.out.println(" i = " +  i.intValue() + " already pop");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
