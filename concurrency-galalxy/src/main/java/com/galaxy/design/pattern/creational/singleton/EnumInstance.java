package com.galaxy.design.pattern.creational.singleton;

/**
 * 枚举单例
 *
 * Created by wangpeng
 * Date: 2018/10/30
 * Time: 20:43
 */
public enum EnumInstance {

    INSTANCE;

    private Object data;

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public static EnumInstance getInstance() {
        return INSTANCE;
    }
}
