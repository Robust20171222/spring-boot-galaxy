package com.galaxy.test;

import org.quartz.CronExpression;
import org.springframework.scheduling.support.CronSequenceGenerator;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.text.ParseException;

/**
 * Created by wangpeng on 19/03/2018.
 */
public class CronTest {

    public static void main(String[] args) {
        try {

            FileWriter fileWriter = new FileWriter("text.txt");
            PrintWriter printWriter = new PrintWriter(fileWriter);

            for (int i = 0; i < 10; i++) {
                printWriter.write("");
            }

            for (int i = 0; i < 10; i++) {
                printWriter.write("");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
