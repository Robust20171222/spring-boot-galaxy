package com.galaxy.concurrency;

import com.galaxy.concurrency.lifecycle.User;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/**
 * Created by wangpeng
 * Date: 2018/10/11
 * Time: 10:58
 */
@SpringBootApplication
public class ConcurrencyApplication {

    public static void main(String[] args) {
        SpringApplication.run(ConcurrencyApplication.class, args);
    }

    @Bean(initMethod = "initMethod",destroyMethod = "destroyMethod")
    public User user(){
        return new User();
    }
}
