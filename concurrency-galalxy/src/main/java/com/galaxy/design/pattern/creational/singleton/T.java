package com.galaxy.design.pattern.creational.singleton;

/**
 * Created by wangpeng
 * Date: 2018/10/30
 * Time: 10:08
 */
public class T implements Runnable {

    @Override
    public void run() {
//        LazySingleton lazySingleton = LazySingleton.getInstance();
        //LazyDoubleCheckSingleton lazySingleton = LazyDoubleCheckSingleton.getInstance();
        StaticInnerClassSingleton lazySingleton = StaticInnerClassSingleton.getInstance();
        System.out.println(Thread.currentThread().getName() + " " + lazySingleton);
    }
}
