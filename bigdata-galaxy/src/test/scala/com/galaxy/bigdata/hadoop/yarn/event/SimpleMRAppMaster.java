package com.galaxy.bigdata.hadoop.yarn.event;

import com.sun.javafx.tk.Toolkit;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.mapreduce.v2.app.job.event.JobEventType;
import org.apache.hadoop.mapreduce.v2.app.job.event.TaskEventType;
import org.apache.hadoop.service.CompositeService;
import org.apache.hadoop.service.Service;
import org.apache.hadoop.yarn.conf.YarnConfiguration;
import org.apache.hadoop.yarn.event.AsyncDispatcher;
import org.apache.hadoop.yarn.event.Dispatcher;
import org.apache.hadoop.yarn.event.EventHandler;

import java.util.UUID;

/**
 * @author pengwang
 * @date 2020/01/04
 */
public class SimpleMRAppMaster extends CompositeService {

    private Dispatcher dispatcher; //中央异步调度器
    private String jobID;
    private int taskNumber; //该作业包含的任务数目
    private String[] taskIDs; //该作业内部包含的所有任务

    public SimpleMRAppMaster(String name, String jobID, int taskNumber) {
        super(name);
        this.jobID = jobID;
        this.taskNumber = taskNumber;
        this.taskIDs = new String[taskNumber];
        for (int i = 0; i < taskNumber; i++) {
            taskIDs[i] = jobID + "_task_" + i;
        }
    }

    @Override
    protected void serviceInit(Configuration conf) throws Exception {
        this.dispatcher = new AsyncDispatcher();
        // 分别注册Job和Task事件处理器
        dispatcher.register(JobEventType.class, new JobEventDispatcher());
        dispatcher.register(TaskEventType.class, new TaskEventDispatcher());
        addService((Service) dispatcher);
        super.serviceInit(conf);
    }

    public Dispatcher getDispatcher() {
        return dispatcher;
    }

    private class JobEventDispatcher implements EventHandler<JobEvent> {
        @Override
        public void handle(JobEvent jobEvent) {
            if (jobEvent.getType() == JobEventType.JOB_KILL) {
                System.out.println("Receive JOB_KILL event, killing all the tasks");
                for (int i = 0; i < taskNumber; i++) {
                    dispatcher.getEventHandler().handle(new TaskEvent(taskIDs[i], TaskEventType.T_KILL));
                }
            } else if (jobEvent.getType() == JobEventType.JOB_INIT) {
                System.out.println("Receive JOB_INIT event, scheduling all the tasks");
                for (int i = 0; i < taskNumber; i++) {
                    dispatcher.getEventHandler().handle(new TaskEvent(taskIDs[i], TaskEventType.T_SCHEDULE));
                }
            }
        }
    }

    private class TaskEventDispatcher implements EventHandler<TaskEvent> {
        @Override
        public void handle(TaskEvent taskEvent) {
            if (taskEvent.getType() == TaskEventType.T_KILL) {
                System.out.println("Receive T_KILL event of task " + taskEvent.getTaskID());
            } else if (taskEvent.getType() == TaskEventType.T_SCHEDULE) {
                System.out.println("Receive T_SCHEDULE event of task " + taskEvent.getTaskID());
            }
        }
    }

    public static void main(String[] args) throws Exception {
        String jobID = UUID.randomUUID().toString();
        SimpleMRAppMaster simpleMRAppMaster = new SimpleMRAppMaster("Simple MRAppMaster", jobID, 5);
        YarnConfiguration yarnConfiguration = new YarnConfiguration(new Configuration());
        simpleMRAppMaster.serviceInit(yarnConfiguration);
        simpleMRAppMaster.serviceStart();
        simpleMRAppMaster.getDispatcher().getEventHandler().handle(new JobEvent(jobID, JobEventType.JOB_KILL));
        simpleMRAppMaster.getDispatcher().getEventHandler().handle(new JobEvent(jobID, JobEventType.JOB_INIT));
    }
}