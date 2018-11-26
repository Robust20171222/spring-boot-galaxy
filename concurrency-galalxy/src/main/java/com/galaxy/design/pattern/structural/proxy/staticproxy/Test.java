package com.galaxy.design.pattern.structural.proxy.staticproxy;

import com.galaxy.design.pattern.structural.proxy.Order;

/**
 * Created by wangpeng
 * Date: 2018/10/30
 * Time: 22:04
 */
public class Test {

    public static void main(String[] args) {
        Order order = new Order();
        order.setUserId(2);

        OrderServiceStaticProxy orderServiceStaticProxy = new OrderServiceStaticProxy();
        orderServiceStaticProxy.saveOrder(order);
    }
}
