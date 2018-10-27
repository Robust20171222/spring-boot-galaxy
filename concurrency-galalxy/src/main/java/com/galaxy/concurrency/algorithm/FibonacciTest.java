package com.galaxy.concurrency.algorithm;

import org.junit.Test;

/**
 * 斐波那契数列
 * <p>
 * Created by wangpeng
 * Date: 2018/10/27
 * Time: 20:24
 */
public class FibonacciTest {

    /**
     * 斐波那契数列查找
     */
    @Test
    public void testFibonacciSearch(){
        System.out.println(fibonacciSearch(4));
    }

    /*
     * 查找斐波纳契数列中第 N 个数。
     *
     * @param : an integer
     * @return: an ineger f(n)
     */
    public int fibonacciSearch(int n) {
        // write your code here
        if (n == 1) {
            return 0;
        }

        if (n == 2) {
            return 1;
        }

        return fibonacciSearch(n - 1) + fibonacciSearch(n - 2);
    }
}
