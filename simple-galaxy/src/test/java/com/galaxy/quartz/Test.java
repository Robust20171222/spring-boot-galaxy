package com.galaxy.quartz;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.quartz.SchedulerException;
import org.quartz.simpl.SimpleInstanceIdGenerator;

import java.text.DateFormat;
import java.util.Date;
import java.util.TimeZone;

public class Test {
    public static void main(String[] args) {
        String time = DateFormatUtils.format(new Date(),DateFormatUtils.ISO_8601_EXTENDED_DATETIME_TIME_ZONE_FORMAT.getPattern(),TimeZone.getTimeZone("Asia/Shanghai"));
        System.out.println(time);
    }

    @org.junit.Test
    public void test() {
        SimpleInstanceIdGenerator simpleInstanceIdGenerator = new SimpleInstanceIdGenerator();
        try {
            System.out.println(simpleInstanceIdGenerator.generateInstanceId());
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }
}
