package com.galaxy.concurrency.example.atomic;

/**
 * Created by wangpeng
 * Date: 2018/10/14
 * Time: 11:13
 */
public class EvenGenerator {

    private int currentValue = 0;
    private boolean cancled = false;

    public synchronized int next() {
        ++currentValue;       //危险！
        ++currentValue;
        return currentValue;
    }

    public boolean isCancled() {
        return cancled;
    }

    public void cancle() {
        cancled = true;
    }
}
