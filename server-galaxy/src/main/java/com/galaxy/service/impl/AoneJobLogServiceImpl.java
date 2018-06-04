package com.galaxy.service.impl;

import com.galaxy.dao.XxlJobLogDao;
import com.galaxy.model.ReturnT;
import com.galaxy.model.XxlJobLog;
import com.galaxy.service.AoneJobLogService;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.ParseException;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

@Service
public class AoneJobLogServiceImpl implements AoneJobLogService {

    private static final Logger log = LogManager.getLogger(AoneJobLogServiceImpl.class);

    private static final ExecutorService EXECUTOR = Executors.newCachedThreadPool();

    @Resource
    private XxlJobLogDao xxlJobLogDao;

    public Map<String, Object> pageList(int start, int length, Integer jobId, Integer logStatus, Integer jobGroup, String filterTime) {
        Map<String, Object> maps = new HashMap<String, Object>();
        if (jobGroup == null) {
            maps.put("code", "1");
            maps.put("msg", "jobGroup is null");                    // 分页列表
            return maps;
        }
        if (jobId == null) {
            maps.put("code", "1");
            maps.put("msg", "jobId is null");                    // 分页列表
            return maps;
        }
        int status = 1;
        if (logStatus != null) {
            status = logStatus;
        }
        // parse param
        Date triggerTimeStart = null;
        Date triggerTimeEnd = null;
        if (StringUtils.isNotBlank(filterTime)) {
            String[] temp = filterTime.split(" - ");
            if (temp != null && temp.length == 2) {
                try {
                    triggerTimeStart = DateUtils.parseDate(temp[0], new String[]{"yyyy-MM-dd HH:mm:ss"});
                    triggerTimeEnd = DateUtils.parseDate(temp[1], new String[]{"yyyy-MM-dd HH:mm:ss"});
                } catch (ParseException e) {
                }
            }
        }

        // page query
        List<XxlJobLog> list = xxlJobLogDao.pageList(start, length, jobGroup, jobId, triggerTimeStart, triggerTimeEnd, status);
        int list_count = xxlJobLogDao.pageListCount(start, length, jobGroup, jobId, triggerTimeStart, triggerTimeEnd, status);

        // package result
        maps.put("code", "0");
        maps.put("recordsTotal", list_count);        // 总记录数
        maps.put("recordsFiltered", list_count);    // 过滤后的总记录数
        maps.put("data", list);                    // 分页列表
        return maps;

    }

    /**
     * 获取最近一次的日志信息：并发处理版本
     *
     * @param params 封装jobID、jobGroup的Map对象
     * @return 最近一次的调度时间、调度总次数
     */
    public ReturnT<List<Map<String, Object>>> getLastLog(Map<String, String> params) {
        log.info(this.getClass().getSimpleName() + "->getLastLog method param value is: " + params);
        List<Map<String, Object>> resultList = new ArrayList<>();

        try {
            List<Future<Map<String, Object>>> futureList = new ArrayList<>();
            for (Map.Entry<String, String> entry : params.entrySet()) {
                Future<Map<String, Object>> future = EXECUTOR.submit(() -> {
                    Integer jobID = Integer.parseInt(entry.getKey());
                    Integer jobGroup = Integer.parseInt(entry.getValue());

                    Map<String, Object> resultMap = new HashMap<>();
                    Map<String, Object> map = this.pageList(0, 1, jobID, -1, jobGroup, null);
                    List<XxlJobLog> logList = (List<XxlJobLog>) map.get("data");
                    String triggerTime = "";
                    if (logList != null && logList.size() > 0) {
                        XxlJobLog log = logList.get(0);
                        triggerTime = DateFormatUtils.format(log.getTriggerTime(), "yyyy-MM-dd HH:mm:ss");
                    }
                    resultMap.put("triggerTime", triggerTime);
                    resultMap.put("count", map.get("recordsTotal"));
                    resultMap.put("jobID", jobID);
                    resultMap.put("jobGroup", jobGroup);
                    return resultMap;
                });
                futureList.add(future);
            }

            // 取出多线程执行的结果
            for (Future<Map<String, Object>> future : futureList) {
                resultList.add(future.get());
            }
        } catch (Exception e) {
            log.error(this.getClass().getSimpleName() + "->getLastLog method exception:" + e.getMessage() + ", params: " + params);
        }
        return new ReturnT<>(resultList);
    }

    /**
     * 获取最近一次的日志信息：没有并发处理版本
     *
     * @param params 封装jobID、jobGroup的Map对象
     * @return 最近一次的调度时间、调度总次数
     */
    @Override
    public ReturnT<List<Map<String, Object>>> getLastLogNoFuture(Map<String, String> params) {
        log.info(this.getClass().getSimpleName() + "->getLastLog method param value is: " + params);
        List<Map<String, Object>> resultList = new ArrayList<>();

        try {
            for (Map.Entry<String, String> entry : params.entrySet()) {
                // 取出任务ID和任务分类ID
                Integer jobID = Integer.parseInt(entry.getKey());
                Integer jobGroup = Integer.parseInt(entry.getValue());

                Map<String, Object> resultMap = new HashMap<>();
                Map<String, Object> map = this.pageList(0, 1, jobID, -1, jobGroup, null);
                List<XxlJobLog> logList = (List<XxlJobLog>) map.get("data");

                // 获取调度时间
                String triggerTime = "";
                if (logList != null && logList.size() > 0) {
                    XxlJobLog log = logList.get(0);
                    triggerTime = DateFormatUtils.format(log.getTriggerTime(), "yyyy-MM-dd HH:mm:ss");
                }
                resultMap.put("triggerTime", triggerTime);

                // 获取调度次数
                resultMap.put("count", map.get("recordsTotal"));
                resultMap.put("jobID", jobID);
                resultMap.put("jobGroup", jobGroup);

                resultList.add(resultMap);
            }
        } catch (Exception e) {
            log.error(this.getClass().getSimpleName() + "->getLastLog method exception:" + e.getMessage() + ", params: " + params);
        }
        return new ReturnT<>(resultList);
    }
}
