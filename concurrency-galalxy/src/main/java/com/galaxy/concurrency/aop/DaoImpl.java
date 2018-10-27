package com.galaxy.concurrency.aop;

/**
 * Created by wangpeng
 * Date: 2018/10/22
 * Time: 10:54
 */
public class DaoImpl implements Dao {

    @Override
    public void select() {
        System.out.println("Enter DaoImpl.select()");
    }

    @Override
    public void insert() {
        System.out.println("Enter DaoImpl.insert()");
    }
}
