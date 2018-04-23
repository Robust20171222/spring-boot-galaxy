package com.galaxy.quartz;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobKey;

import java.util.Date;

/**
 * Created by wangpeng on 26/03/2018.
 */
public class PrintDateJob implements Job{

    @Override
    public void execute(JobExecutionContext context) {
        JobKey jobKey = context.getJobDetail().getKey();
        System.out.println(jobKey+": "+ new Date());
    }
}
