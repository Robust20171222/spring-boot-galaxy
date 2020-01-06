package com.galaxy.bigdata.hadoop.yarn.event;

import org.apache.hadoop.mapreduce.v2.app.job.event.JobEventType;
import org.apache.hadoop.yarn.event.AbstractEvent;

/**
 * @author pengwang
 * @date 2020/01/04
 */
public class JobEvent extends AbstractEvent<JobEventType> {

    private String jobID;

    public JobEvent(String jobID, JobEventType jobEventType) {
        super(jobEventType);
        this.jobID = jobID;
    }

    public String getJobID() {
        return jobID;
    }
}