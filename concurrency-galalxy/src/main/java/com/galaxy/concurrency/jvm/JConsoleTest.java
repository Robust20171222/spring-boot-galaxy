package com.galaxy.concurrency.jvm;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * JConsole监视代码
 * <p>
 * Created by wangpeng
 * Date: 2018/10/15
 * Time: 11:15
 */
public class JConsoleTest {

    /**
     * 内存占位符对象，一个 OOMObject 大约占 64 KB
     */
    static class OOMObject {
        public byte[] placeholder = new byte[64 * 1024];
    }

    /**
     * 死锁代码演示
     */
    static class SynAddRunnable implements Runnable {

        int a, b;

        public SynAddRunnable(int a, int b) {
            this.a = a;
            this.b = b;
        }

        @Override
        public void run() {
            synchronized (Integer.valueOf(a)) {
                synchronized (Integer.valueOf(b)) {
                    System.out.println(a + b);
                }
            }
        }
    }

    public static void fillHeap(int num) throws InterruptedException {
        List<OOMObject> list = new ArrayList<>();
        for (int i = 0; i < num; i++) {
            // 稍作延时，令监视曲线的变化更加明显
            Thread.sleep(50);
            list.add(new OOMObject());
        }
        System.gc();
    }

    /**
     * 线程死循环演示
     */
    public static void createBusyThread() {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) ;
            }
        }, "testBusyThread");
        thread.start();
    }

    /**
     * 线程锁等待演示
     *
     * @param lock
     */
    public static void createLockThread(final Object lock) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (lock) {
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, "testLockThread");
        thread.start();
    }

    public static void main(String[] args) throws Exception {
        //fillHeap(1000);

        /**
         * 线程等待演示代码
         */
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        br.readLine();
        createBusyThread();
//        br.readLine();
//        Object object = new Object();
//        createLockThread(object);

        /**
         * 死锁代码演示
         */
//        for (int i = 0; i < 1000; i++) {
//            new Thread(new SynAddRunnable(1, 2)).start();
//            new Thread(new SynAddRunnable(2, 1)).start();
//        }
    }
}


