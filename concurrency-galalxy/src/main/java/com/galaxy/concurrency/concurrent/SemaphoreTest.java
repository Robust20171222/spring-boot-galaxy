package com.galaxy.concurrency.concurrent;

import java.util.concurrent.Semaphore;

/**
 * Created by wangpeng
 * Date: 2018/10/24
 * Time: 23:25
 */
public class SemaphoreTest {

    public static void main(String[] args) {
        final Semaphore semaphore = new Semaphore(5);

        // 每次acquire返回成功后，Semaphore可用的信号量就会减少一个，如果没有可用的信号，acquire调用就会阻塞，等待有release调用释放信号后，acquire才会得到信号并返回
        Runnable runnable = new Runnable() {
            public void run() {
                try {
                    semaphore.acquire();
                    System.out.println(Thread.currentThread().getName() + "获得了信号量,时间为" + System.currentTimeMillis());
                    Thread.sleep(2000);
                    System.out.println(Thread.currentThread().getName() + "释放了信号量,时间为" + System.currentTimeMillis());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    semaphore.release();
                }
            }
        };

        Thread[] threads = new Thread[1000];
        for (int i = 0; i < threads.length; i++)
            threads[i] = new Thread(runnable);
        for (int i = 0; i < threads.length; i++)
            threads[i].start();
    }
}
