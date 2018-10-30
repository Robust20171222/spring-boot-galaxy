package com.galaxy.design.pattern.creational.singleton;

/**
 * 懒加载单例
 * <p>
 * Created by wangpeng
 * Date: 2018/10/30
 * Time: 10:05
 */
public class LazySingleton {

    private static LazySingleton lazySingleton = null;

    private LazySingleton() {
        if (lazySingleton == null) {
            throw new RuntimeException("单例构造器禁止反射调用");
        }
    }

    public static LazySingleton getInstance() {
        synchronized (LazySingleton.class) {
            if (lazySingleton == null) {
                lazySingleton = new LazySingleton();
            }
        }
        return lazySingleton;
    }
}
