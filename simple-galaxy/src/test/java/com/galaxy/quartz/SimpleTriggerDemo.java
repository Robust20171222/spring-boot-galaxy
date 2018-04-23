package com.galaxy.quartz;

import org.quartz.JobDetail;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.SimpleTrigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.JobDetailImpl;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by wangpeng on 26/03/2018.
 */
public class SimpleTriggerDemo {

    public static void main(String[] args) {
        SimpleTrigger trigger = TriggerBuilder.newTrigger().withIdentity("greattrigger", "mygroup")
                .startAt(new Date(Calendar.getInstance().getTimeInMillis()+ 3000))
                .withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(2)
                        .withRepeatCount(5)).build();
    }
}
