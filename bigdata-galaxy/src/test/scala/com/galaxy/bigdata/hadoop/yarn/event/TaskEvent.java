package com.galaxy.bigdata.hadoop.yarn.event;

import org.apache.hadoop.mapreduce.v2.app.job.event.TaskEventType;
import org.apache.hadoop.yarn.event.AbstractEvent;

/**
 * @author pengwang
 * @date 2020/01/04
 */
public class TaskEvent extends AbstractEvent<TaskEventType> {

    private String taskID;

    public TaskEvent(String taskID, TaskEventType taskEventType) {
        super(taskEventType);
        this.taskID = taskID;
    }

    public String getTaskID() {
        return taskID;
    }
}