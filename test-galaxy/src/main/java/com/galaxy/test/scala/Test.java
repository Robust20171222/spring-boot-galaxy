package com.galaxy.test.scala;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;

import java.text.ParseException;
import java.util.Date;

/**
 * Created by wangpeng on 28/03/2018.
 */
public class Test {

    public static void main(String[] args) {
        try {
            Date time = DateUtils.parseDate("2018-03-31T12:45:58.000Z", org.apache.commons.lang.time.DateFormatUtils.ISO_DATETIME_TIME_ZONE_FORMAT.getPattern());
            System.out.println(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }
}
