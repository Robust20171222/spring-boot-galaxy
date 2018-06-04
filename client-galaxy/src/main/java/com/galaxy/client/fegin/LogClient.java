package com.galaxy.client.fegin;

import com.galaxy.client.model.ReturnT;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

/**
 * Created by wangpeng
 * Date: 2018/6/3
 * Time: 10:44
 */
@FeignClient(name = "server-galaxy",path = "/scheduler/log/")
public interface LogClient {

    /**
     * 查询最近一次的调度信息：有并发处理
     *
     * @return
     * @see /scheduler/log/lastLog
     */
    @PostMapping(value = "/lastLog")
    @ResponseBody
    public ReturnT<List<Map<String, Object>>> getLastLog(@RequestBody Map<String, String> params);

    /**
     * 查询最近一次的调度信息：无并发处理
     * @return
     * @see /scheduler/log/lastLogNoFuture
     */
    @PostMapping(value = "/lastLogNoFuture")
    @ResponseBody
    public ReturnT<List<Map<String, Object>>> getLastLogNoFuture(@RequestBody Map<String, String> params);
}
