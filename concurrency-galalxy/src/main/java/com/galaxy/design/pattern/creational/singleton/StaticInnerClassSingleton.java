package com.galaxy.design.pattern.creational.singleton;

/**
 * 静态内部类单例
 * <p>
 * Created by wangpeng
 * Date: 2018/10/30
 * Time: 10:39
 */
public class StaticInnerClassSingleton {

    private StaticInnerClassSingleton() {
        if (InnerClass.INSTANCE == null) {
            throw new RuntimeException("单例构造器禁止反射调用");
        }
    }

    private static class InnerClass {
        private static StaticInnerClassSingleton INSTANCE = new StaticInnerClassSingleton();
    }

    public static StaticInnerClassSingleton getInstance() {
        return InnerClass.INSTANCE;
    }
}
