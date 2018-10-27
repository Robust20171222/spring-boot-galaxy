package com.galaxy.concurrency.algorithm;

import org.junit.Test;

/**
 * Created by wangpeng
 * Date: 2018/10/25
 * Time: 07:21
 */
public class StringTest {

    @Test
    public void testNegative() {
        int[] numbers = new int[]{9, 1, 3, 8, -10, 7, -9, -2, -8, 2};
        int count = 0;
        for (int i = 0; i < numbers.length; i++) {
            for (int j = (i + 1); j < numbers.length; j++) {
                if (numbers[i] + numbers[j] == 0) {
                    count++;
                    break;
                }
            }
        }
        System.out.println(count);
    }

    @Test
    public void splitNumber() {
        String number = "1234";
        int digit = number.length();
        for (int i = 1; i <= digit; i++) {
            for (int j = 0; j < digit - i; j++) {
                String subNumber = number.substring(j, j + i);
                System.out.println(subNumber);
            }
        }
    }
}
