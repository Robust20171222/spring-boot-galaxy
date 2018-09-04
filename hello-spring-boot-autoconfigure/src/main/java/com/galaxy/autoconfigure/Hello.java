package com.galaxy.autoconfigure;

import java.time.LocalDateTime;

/**
 * Created by wangpeng
 * Date: 2018/9/4
 * Time: 17:46
 */
public class Hello {

    public static final String USER_NAME = "user.name";
    public static final String MORNING_MESSAGE = "morning.message";
    public static final String AFTERNOON_MESSAGE = "afternoon.message";
    public static final String EVENING_MESSAGE = "evening.message";
    public static final String NIGHT_MESSAGE = "night.message";

    private HelloConfig helloConfig;

    public Hello(HelloConfig helloConfig) {
        this.helloConfig = helloConfig;
    }

    public String greet(LocalDateTime localDateTime) {

        String name = helloConfig.getProperty(USER_NAME);
        int hourOfDay = localDateTime.getHour();

        if (hourOfDay >= 5 && hourOfDay < 12) {
            return String.format("Hello %s, %s", name, helloConfig.get(MORNING_MESSAGE));
        } else if (hourOfDay >= 12 && hourOfDay < 17) {
            return String.format("Hello %s, %s", name, helloConfig.get(AFTERNOON_MESSAGE));
        } else if (hourOfDay >= 17 && hourOfDay < 20) {
            return String.format("Hello %s, %s", name, helloConfig.get(EVENING_MESSAGE));
        } else {
            return String.format("Hello %s, %s", name, helloConfig.get(NIGHT_MESSAGE));
        }
    }

    public String greet() {
        return greet(LocalDateTime.now());
    }
}
