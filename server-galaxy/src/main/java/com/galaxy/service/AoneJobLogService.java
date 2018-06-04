package com.galaxy.service;

import com.galaxy.model.ReturnT;

import java.util.List;
import java.util.Map;

public interface AoneJobLogService {

    public Map<String, Object> pageList(int start, int length,
                                        Integer jobId, Integer logStatus, Integer jobGroup,
                                        String filterTime);

    public ReturnT<List<Map<String, Object>>> getLastLog(Map<String, String> params);

    public ReturnT<List<Map<String, Object>>> getLastLogNoFuture(Map<String, String> params);
}
