package com.galaxy.concurrency.example.atomic;

/**
 * 买家类
 * <p>
 * Created by wangpeng
 * Date: 2018/10/14
 * Time: 11:21
 */
public class Customer implements Runnable {

    private BankAccount account;
    private int cash;

    public Customer(int cash, BankAccount account) {
        this.cash = cash;
        this.account = account;
    }

    public void cost(int n) {
        cash -= n;
        account.add(n);
    }

    @Override
    public void run() {
        while (cash > 0) {  //直至将钱用光
            cost(1);
        }
        System.out.println("total: " + account.getTotal());   //打印出银行账户的总计金额
    }

    public static void main(String[] args) {
        BankAccount account = new BankAccount();
        for (int i = 0; i < 100; i++) {
            new Thread(new Customer(100000, account)).start();
        }
    }
}
