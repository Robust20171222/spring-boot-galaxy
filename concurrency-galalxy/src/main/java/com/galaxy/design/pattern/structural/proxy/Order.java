package com.galaxy.design.pattern.structural.proxy;

/**
 * Created by wangpeng
 * Date: 2018/10/30
 * Time: 21:43
 */
public class Order {

    private Object orderInfo;
    private Integer userId;

    public Object getOrderInfo() {
        return orderInfo;
    }

    public void setOrderInfo(Object orderInfo) {
        this.orderInfo = orderInfo;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
