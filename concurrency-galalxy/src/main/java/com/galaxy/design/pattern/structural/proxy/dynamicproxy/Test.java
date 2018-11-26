package com.galaxy.design.pattern.structural.proxy.dynamicproxy;

import com.galaxy.design.pattern.structural.proxy.IOrderService;
import com.galaxy.design.pattern.structural.proxy.Order;
import com.galaxy.design.pattern.structural.proxy.OrderServiceImpl;

/**
 * Created by wangpeng
 * Date: 2018/10/30
 * Time: 22:29
 */
public class Test {

    public static void main(String[] args) {
        Order order = new Order();
        order.setUserId(1);

        IOrderService orderService = (IOrderService)new OrderServiceDynamicProxy(new OrderServiceImpl()).bind();
        orderService.saveOrder(order);
    }
}
