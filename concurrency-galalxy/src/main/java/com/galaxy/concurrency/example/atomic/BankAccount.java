package com.galaxy.concurrency.example.atomic;

/**
 * 银行账户类
 * Created by wangpeng
 * Date: 2018/10/14
 * Time: 11:20
 */
public class BankAccount {

    private volatile int total = 0;

    public synchronized void add(int n) {
        total += n;
    }

    public int getTotal() {
        return total;
    }
}
