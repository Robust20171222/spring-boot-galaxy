package com.debug.mooc.dubbo.two.server;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

import org.springframework.boot.web.servlet.support.SpringBootServletInitializer; //2.x版本

import org.springframework.context.annotation.ImportResource;

/**
 * @Author:debug (SteadyJack)
 * @Date: 2019/1/13 15:06
 **/
@SpringBootApplication
@ImportResource(value = {"classpath:spring/spring-jdbc.xml","classpath:spring/spring-dubbo.xml"})
@MapperScan(basePackages = "com.debug.mooc.dubbo.two.model.mapper")
public class DubboTwoServerApplication extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(DubboTwoServerApplication.class);
    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(DubboTwoServerApplication.class, args);
    }
}
