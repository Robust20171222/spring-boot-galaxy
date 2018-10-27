package com.galaxy.concurrency.aop;

/**
 * Created by wangpeng
 * Date: 2018/10/22
 * Time: 10:57
 */
public class TimeHandler {

    public void printTime() {
        System.out.println("CurrentTime:" + System.currentTimeMillis());
    }
}
