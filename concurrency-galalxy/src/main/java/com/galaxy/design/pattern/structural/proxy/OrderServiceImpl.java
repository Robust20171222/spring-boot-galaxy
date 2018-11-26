package com.galaxy.design.pattern.structural.proxy;

/**
 * Created by wangpeng
 * Date: 2018/10/30
 * Time: 21:46
 */
public class OrderServiceImpl implements IOrderService {

    private IOrderDao iOrderDao;

    @Override
    public int saveOrder(Order order) {
        iOrderDao = new OrderDaoImpl();
        System.out.println("Service层调用Dao层添加Order");
        return iOrderDao.insert(order);
    }
}
