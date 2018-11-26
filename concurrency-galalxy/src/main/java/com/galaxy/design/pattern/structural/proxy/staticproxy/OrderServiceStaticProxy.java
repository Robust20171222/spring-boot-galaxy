package com.galaxy.design.pattern.structural.proxy.staticproxy;

import com.galaxy.design.pattern.structural.proxy.IOrderService;
import com.galaxy.design.pattern.structural.proxy.Order;
import com.galaxy.design.pattern.structural.proxy.OrderServiceImpl;
import com.galaxy.design.pattern.structural.proxy.db.DataSourceContextHolder;

/**
 * Created by wangpeng
 * Date: 2018/10/30
 * Time: 21:49
 */
public class OrderServiceStaticProxy {

    private IOrderService iOrderService;

    public int saveOrder(Order order) {
        beforeMethod(order);
        iOrderService = new OrderServiceImpl();
        int result = iOrderService.saveOrder(order);
        afterMethod();
        return result;
    }

    private void beforeMethod(Order order) {
        int userId = order.getUserId();
        int dbRouter = userId % 2;

        System.out.println("静态代理分配到DB[" + dbRouter + "]处理数据");

        DataSourceContextHolder.setDBType(String.valueOf(dbRouter));
        System.out.println("静态代理beforeMethod");
    }

    private void afterMethod() {
        System.out.println("静态代理afterMethod");
    }
}
