package com.galaxy.quartz;

import org.junit.Test;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import static org.quartz.SimpleScheduleBuilder.simpleSchedule;
import static org.quartz.TriggerBuilder.newTrigger;

/**
 * Created by wangpeng on 26/03/2018.
 *
 * @see http://www.quartz-scheduler.org/documentation/quartz-2.x/tutorials/tutorial-lesson-05.html
 */
public class SimpleTriggerExample {

    /**
     * 测试CronTrigger的周期调度
     */
    @Test
    public void test3() {
        try {
            SchedulerFactory schedulerFactory = new StdSchedulerFactory();
            Scheduler scheduler = schedulerFactory.getScheduler();
            JobDetail jobDetail = JobBuilder.newJob(PrintDateJob.class).withIdentity("goodjob", "mygroup").build();

            String cronExpression = "0/10 * * * * ?";
            DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

            Date startDate = format.parse("2018-03-27 16:20:00");
            Date endDate = format.parse("2018-03-27 16:23:00");

            CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder.cronSchedule(cronExpression).withMisfireHandlingInstructionDoNothing();

            CronTrigger cronTrigger = TriggerBuilder.newTrigger().withIdentity("trigger3", "group1").withSchedule(cronScheduleBuilder).startAt(startDate).endAt(endDate).build();

            scheduler.scheduleJob(jobDetail, cronTrigger);
            scheduler.start();

        } catch (SchedulerException|ParseException e) {
            e.printStackTrace();
        }

        while (true){

        }
    }

    /**
     * 测试SimpleTrigger的周期调度
     */
    @Test
    public void test2() {
        try {
            SchedulerFactory schedulerFactory = new StdSchedulerFactory();
            Scheduler scheduler = schedulerFactory.getScheduler();
            JobDetail jobDetail = JobBuilder.newJob(PrintDateJob.class).withIdentity("goodjob", "mygroup").build();

            SimpleTrigger trigger = newTrigger()
                    .withIdentity("trigger3", "group1")
                    .startAt(new Date())  // if a start time is not given (if this line were omitted), "now" is implied
                    .withSchedule(simpleSchedule()
                            .withIntervalInSeconds(10)
                            .withRepeatCount(1).repeatForever()) // note that 10 repeats will give a total of 11 firings
                    .build();

            scheduler.scheduleJob(jobDetail, trigger);
            scheduler.start();

        } catch (SchedulerException e) {
            e.printStackTrace();
        }

        while (true){

        }
    }

    @Test
    public void test1() {
        try {
            SchedulerFactory schedulerFactory = new StdSchedulerFactory();
            Scheduler scheduler = schedulerFactory.getScheduler();
            JobDetail jobDetail = JobBuilder.newJob(PrintDateJob.class).withIdentity("goodjob", "mygroup").build();

            SimpleTrigger trigger = newTrigger().withIdentity("greattrigger", "mygroup")
                    .startAt(new Date(Calendar.getInstance().getTimeInMillis()+ 3000))
                    .withSchedule(simpleSchedule().withIntervalInSeconds(2)
                            .withRepeatCount(5)).build();

            scheduler.scheduleJob(jobDetail, trigger);
            scheduler.start();

            try {
                //wait for 20 seconds to finish the job
                Thread.sleep(20000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            //shutdown scheduler gracefully
            scheduler.shutdown(true);
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }
}
