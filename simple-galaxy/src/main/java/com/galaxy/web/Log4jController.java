package com.galaxy.web;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 测试log4j2日志切割策略
 * <p>
 * Created by wangpeng
 * Date: 2018/5/29
 * Time: 22:02
 */
@RestController
@RequestMapping("/log")
public class Log4jController {

    private static final Logger LOGGER = LogManager.getLogger(Log4jController.class);

    @GetMapping(value = "/size")
    public String testSizePolicy() {
        for (int i = 0; i < 100; i++) {
            LOGGER.info("Rolling file appender example...");
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return "Rolling file appender example";
    }
}
