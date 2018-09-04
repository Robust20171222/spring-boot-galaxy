package com.galaxy.sample;

import com.galaxy.autoconfigure.Hello;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Created by wangpeng
 * Date: 2018/9/4
 * Time: 17:58
 */
@SpringBootApplication
public class HelloSampleApplication implements CommandLineRunner {

    @Autowired
    private Hello hello;

    public static void main(String[] args) {
        SpringApplication.run(HelloSampleApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        String message = hello.greet();
        System.out.println(message);
    }
}
