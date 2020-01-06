package com.galaxy.concurrency.concurrent.lock;

import java.util.concurrent.locks.ReentrantLock;

/**
 * ReentrantLock 测试
 * @author pengwang
 * @date 2020/01/06
 */
public class ReenterLock implements Runnable {

    public static ReentrantLock lock = new ReentrantLock();
    public static int i = 0;

    @Override
    public void run() {
        for (int j = 0; j < 10000000; j++) {
            lock.lock();
            lock.lock();
            try {
                i++;
            } catch (Exception e) {
                e.printStackTrace();
            }finally {
                lock.unlock();
                lock.unlock();
            }
        }
    }

    public static void main(String[] args) {
        ReenterLock reenterLock = new ReenterLock();
        Thread t1 = new Thread(reenterLock);
        Thread t2 = new Thread(reenterLock);
        t1.start();
        t2.start();
    }
}