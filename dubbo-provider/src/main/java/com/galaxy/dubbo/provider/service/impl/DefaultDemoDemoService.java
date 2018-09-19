package com.galaxy.dubbo.provider.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.galalxy.dubbo.api.DemoService;

/**
 * 服务提供类
 * <p>
 * Created by wangpeng
 * Date: 2018/9/19
 * Time: 16:46
 */
@Service
public class DefaultDemoDemoService implements DemoService {

    @Override
    public String sayHello(String name) {
        return "Hello, " + name + " (from Spring Boot)";
    }
}
