package com.galaxy.concurrency.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by wangpeng
 * Date: 2018/10/11
 * Time: 11:05
 */
@Controller
public class TestController {

    @RequestMapping(value = "/test")
    @ResponseBody
    public String test() {
        return "hello test";
    }

    @RequestMapping(value = "/docker")
    @ResponseBody
    public String hello_docker(){
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) ;
            }
        }, "testBusyThread");
        thread.start();
        return "Hello docker！";
    }
}
