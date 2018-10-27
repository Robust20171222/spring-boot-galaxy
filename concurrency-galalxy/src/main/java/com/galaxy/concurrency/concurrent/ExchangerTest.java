package com.galaxy.concurrency.concurrent;

import java.util.concurrent.Exchanger;

/**
 * Created by wangpeng
 * Date: 2018/10/24
 * Time: 23:32
 */
public class ExchangerTest {

    public static class ExchangerThread extends Thread {
        private String str;
        private Exchanger<String> exchanger;
        private int sleepSecond;

        public ExchangerThread(String str, Exchanger<String> exchanger, int sleepSecond) {
            this.str = str;
            this.exchanger = exchanger;
            this.sleepSecond = sleepSecond;
        }

        public void run() {
            try {
                System.out.println(this.getName() + "启动, 原数据为" + str + ", 时间为" + System.currentTimeMillis());
                Thread.sleep(sleepSecond * 1000);
                str = exchanger.exchange(str);
                System.out.println(this.getName() + "交换了数据, 交换后的数据为" + str + ", 时间为" + System.currentTimeMillis());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        Exchanger<String> exchanger = new Exchanger<String>();
        ExchangerThread et0 = new ExchangerThread("111", exchanger, 3);
        ExchangerThread et1 = new ExchangerThread("222", exchanger, 2);
        et0.start();
        et1.start();
    }
}
