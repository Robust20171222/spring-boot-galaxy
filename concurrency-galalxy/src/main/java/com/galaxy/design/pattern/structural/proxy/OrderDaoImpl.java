package com.galaxy.design.pattern.structural.proxy;

/**
 * Created by wangpeng
 * Date: 2018/10/30
 * Time: 21:45
 */
public class OrderDaoImpl implements IOrderDao {

    @Override
    public int insert(Order order) {
        System.out.println("Dao层插入Order成功");
        return 1;
    }
}
