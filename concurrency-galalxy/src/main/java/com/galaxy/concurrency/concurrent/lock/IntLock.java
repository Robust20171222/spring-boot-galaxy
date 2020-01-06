package com.galaxy.concurrency.concurrent.lock;

import java.util.concurrent.locks.ReentrantLock;

/**
 * ReentrantLock 中断响应测试
 * 对于synchronized来说，如果一个线程在等待锁，那么结果只有两种情况，要么它获得这把锁继续执行，要么它就保持等待。
 * 而使用重入锁，则提供另外一种可能，那就是线程可以被中断。也就是在等待锁的过程中，程序可以根据需要取消对锁的请求。
 * 如果一个线程正在等待锁，那么它依然可以收到一个通知，被告知无须再等待，可以停止工作了。这种情况对于处理死锁是有一定帮助的。
 * @author pengwang
 * @date 2020/01/06
 */
public class IntLock implements Runnable {

    public static ReentrantLock lock1 = new ReentrantLock();
    public static ReentrantLock lock2 = new ReentrantLock();

    int lock;

    /**
     * 控制加锁顺序，方便构造死锁
     * @param lock
     */
    public IntLock(int lock) {
        this.lock = lock;
    }

    @Override
    public void run() {
        try {
            if (lock == 1) {
                lock1.lockInterruptibly();
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                lock2.lockInterruptibly();
            }else{
                lock2.lockInterruptibly();
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                lock1.lockInterruptibly();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            if (lock1.isHeldByCurrentThread()){
                lock1.unlock();
            }
            if (lock2.isHeldByCurrentThread()){
                lock2.unlock();
            }
            System.out.println(Thread.currentThread().getId() + ":线程退出");
        }
    }

    public static void main(String[] args) {
        IntLock r1 = new IntLock(1);
        IntLock r2 = new IntLock(2);
        Thread t1 = new Thread(r1);
        Thread t2 = new Thread(r2);
        t1.start();
        t2.start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        t2.interrupt();
    }
}