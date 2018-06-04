package com.galaxy.web;

import com.galaxy.dao.XxlJobLogDao;
import com.galaxy.model.ReturnT;
import com.galaxy.service.AoneJobLogService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/scheduler/log")
public class AoneJobLogController {

    private static final Logger log = LogManager.getLogger(AoneJobLogController.class);

    @Autowired
    private AoneJobLogService aoneJobLogService;

    @Autowired
    private XxlJobLogDao xxlJobLogDao;

    /**
     * 查询最近一次的调度信息：有并发处理
     *
     * @return
     * @see /scheduler/log/lastLog
     */
    @PostMapping(value = "/lastLog")
    @ResponseBody
    public ReturnT<List<Map<String, Object>>> getLastLog(@RequestBody Map<String, String> params) {
        return this.aoneJobLogService.getLastLog(params);
    }

    /**
     * 查询最近一次的调度信息：无并发处理
     * @return
     * @see /scheduler/log/lastLogNoFuture
     */
    @PostMapping(value = "/lastLogNoFuture")
    @ResponseBody
    public ReturnT<List<Map<String, Object>>> getLastLogNoFuture(@RequestBody Map<String, String> params) {
        return this.aoneJobLogService.getLastLogNoFuture(params);
    }
}
