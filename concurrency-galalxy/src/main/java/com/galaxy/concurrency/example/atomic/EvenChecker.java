package com.galaxy.concurrency.example.atomic;

/**
 * Created by wangpeng
 * Date: 2018/10/14
 * Time: 11:13
 */
public class EvenChecker implements Runnable {

    private EvenGenerator generator;

    public EvenChecker(EvenGenerator generator) {
        this.generator = generator;
    }

    @Override
    public void run() {
        int nextValue;
        while (!generator.isCancled()) {
            nextValue = generator.next();
            System.out.println(Thread.currentThread().getId());
            if (nextValue % 2 != 0) {
                System.out.println(nextValue + "不是一个偶数!");
                generator.cancle();
            }
        }
    }

    public static void main(String[] args) {
        EvenGenerator generator = new EvenGenerator();
        Thread t1 = new Thread(new EvenChecker(generator));
        Thread t2 = new Thread(new EvenChecker(generator));

        t1.start();
        t2.start();
    }
}
