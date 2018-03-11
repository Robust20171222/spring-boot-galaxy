package com.galaxy;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Created by wangpeng on 11/03/2018.
 */
@SpringBootApplication
@EnableTransactionManagement // 启注解事务管理，等同于xml配置方式的 <tx:annotation-driven />
@MapperScan("com.galaxy.mapper")
public class SimpleGalaxyApplication {
    public static void main(String[] args) {
        SpringApplication.run(SimpleGalaxyApplication.class, args);
    }
}
