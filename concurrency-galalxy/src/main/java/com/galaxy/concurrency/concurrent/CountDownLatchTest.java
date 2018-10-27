package com.galaxy.concurrency.concurrent;

import java.util.concurrent.CountDownLatch;

/**
 * Created by wangpeng
 * Date: 2018/10/24
 * Time: 23:10
 */
public class CountDownLatchTest {

    private static class WorkThread extends Thread {
        private CountDownLatch cdl;
        private int sleepSecond;

        public WorkThread(String name, CountDownLatch cdl, int sleepSecond) {
            super(name);
            this.cdl = cdl;
            this.sleepSecond = sleepSecond;
        }

        public void run() {
            try {
                System.out.println(this.getName() + "启动了，时间为" + System.currentTimeMillis());
                Thread.sleep(sleepSecond * 1000);
                cdl.countDown();
                System.out.println(this.getName() + "执行完了，时间为" + System.currentTimeMillis());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private static class DoneThread extends Thread {
        private CountDownLatch cdl;

        public DoneThread(String name, CountDownLatch cdl) {
            super(name);
            this.cdl = cdl;
        }

        public void run() {
            try {
                System.out.println(this.getName() + "要等待了, 时间为" + System.currentTimeMillis());
                cdl.await();
                System.out.println(this.getName() + "等待完了, 时间为" + System.currentTimeMillis());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws Exception {
        CountDownLatch cdl = new CountDownLatch(1000);
        DoneThread dt0 = new DoneThread("DoneThread1", cdl);
        dt0.start();
        for (int i = 0; i < 1000; i++) {
            WorkThread wt0 = new WorkThread("WorkThread" + i, cdl, 2);
            wt0.start();
        }
    }
}
