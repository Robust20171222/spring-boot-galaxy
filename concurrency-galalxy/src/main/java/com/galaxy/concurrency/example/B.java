package com.galaxy.concurrency.example;

/**
 * Created by wangpeng
 * Date: 2018/10/27
 * Time: 09:57
 */
public class B extends A {

    public void test() {
        System.out.println("这是子类的方法");
    }

    public static void main(String[] args) {
        A a = new B();
        a.test();
    }
}
