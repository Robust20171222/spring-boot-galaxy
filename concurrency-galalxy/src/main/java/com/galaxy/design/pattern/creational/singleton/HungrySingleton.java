package com.galaxy.design.pattern.creational.singleton;

import java.io.Serializable;

/**
 * 饿汉式单例
 * Created by wangpeng
 * Date: 2018/10/30
 * Time: 10:48
 */
public class HungrySingleton implements Serializable {

    private final static HungrySingleton HUNGRY_SINGLETON = new HungrySingleton();

    private HungrySingleton() {
        if (HUNGRY_SINGLETON == null) {
            throw new RuntimeException("单例构造器禁止反射调用");
        }
    }

    public static HungrySingleton getInstance() {
        return HUNGRY_SINGLETON;
    }

    private Object readResolve() {
        return HUNGRY_SINGLETON;
    }
}
