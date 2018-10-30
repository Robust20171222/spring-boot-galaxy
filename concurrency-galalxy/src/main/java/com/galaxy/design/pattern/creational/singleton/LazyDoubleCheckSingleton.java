package com.galaxy.design.pattern.creational.singleton;

/**
 * 懒加载单例：双重检查
 * <p>
 * Created by wangpeng
 * Date: 2018/10/30
 * Time: 10:05
 */
public class LazyDoubleCheckSingleton {

    private volatile static LazyDoubleCheckSingleton INSTANCE = null;

    private LazyDoubleCheckSingleton() {
    }

    public static LazyDoubleCheckSingleton getInstance() {
        if (INSTANCE == null) { // 减少进入synchronized的开销
            synchronized (LazyDoubleCheckSingleton.class) {
                if (INSTANCE == null) { // 保证内存一致性、禁止指令重排
                    /**
                     * 1、分配内存 2、初始化对象 3、设置INSTANCE指向刚分配的内存地址
                     * 2、3可能重排序，导致其他线程获取到的对象不完整
                     */
                    INSTANCE = new LazyDoubleCheckSingleton();
                }
            }
        }

        return INSTANCE;
    }
}
