package com.galaxy;

import org.apache.logging.log4j.core.appender.rolling.TimeBasedTriggeringPolicy;

import java.net.UnknownHostException;

/**
 * Created by wangpeng
 * Date: 2018/5/29
 * Time: 15:30
 */
public class Test {

    public static void main(String[] args) {
        try {
            System.out.println(HostNameUtil.getLocalHostAddress());
            System.out.println(HostNameUtil.getLocalHostLANAddress().getHostAddress());
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }
}
