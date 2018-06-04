package com.galaxy.domain;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;

import java.io.Serializable;
import java.util.Date;

/**
 * 封装前端请求参数
 * Created by wangpeng on 13/03/2018.
 */
public class Param implements Serializable {

    private Integer start = 0; // 查询起始位置
    private Integer length = 10; // 每次查询的最大条数
    private Integer jobGroup; // 任务组ID
    private String executorHandler; // 执行器handler
    private Integer jobId; // 任务ID
    private Integer logStatus = -1; // 执行状态：-1表示全部，1表示成功，2表示失败，3表示进行中
    private String filterTime; // 调度时间
    private Integer type;//日志清理类型

    public Integer getStart() {
        return start;
    }

    public void setStart(Integer start) {
        this.start = start;
    }

    public Integer getLength() {
        return length;
    }

    public void setLength(Integer length) {
        this.length = length;
    }

    public Integer getJobGroup() {
        return jobGroup;
    }

    public void setJobGroup(Integer jobGroup) {
        this.jobGroup = jobGroup;
    }

    public String getExecutorHandler() {
        return executorHandler;
    }

    public void setExecutorHandler(String executorHandler) {
        this.executorHandler = executorHandler;
    }

    public Integer getJobId() {
        return jobId;
    }

    public void setJobId(Integer jobId) {
        this.jobId = jobId;
    }

    public Integer getLogStatus() {
        return logStatus;
    }

    public void setLogStatus(Integer logStatus) {
        this.logStatus = logStatus;
    }

    public String getFilterTime() {
        return filterTime;
    }

    public void setFilterTime(String filterTime) {
        // 如果调度时间为空，则设置默认的调度时间
        if (StringUtils.isEmpty(filterTime)) {
            String nowTime = DateFormatUtils.format(new Date(), DateFormatUtils.ISO_DATE_FORMAT.getPattern());
            String startTime = nowTime + " 00:00:00";
            String endTime = nowTime + " 23:59:59";
            this.filterTime = startTime + "-" + endTime;
        } else {
            this.filterTime = filterTime;
        }
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Param{" +
                "start=" + start +
                ", length=" + length +
                ", jobGroup=" + jobGroup +
                ", executorHandler='" + executorHandler + '\'' +
                ", jobId=" + jobId +
                ", logStatus=" + logStatus +
                ", filterTime='" + filterTime + '\'' +
                ", type=" + type +
                '}';
    }
}
