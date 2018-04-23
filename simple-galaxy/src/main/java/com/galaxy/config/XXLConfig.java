package com.galaxy.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by wangpeng on 2018/4/3.
 */
@Configuration
@ConfigurationProperties(prefix="xxl.job")
public class XXLConfig {

    @Value("${xxl.job.db.driverClass}")
    private String DB_DRIVER;

    @Value("${xxl.job.db.url}")
    private String DB_URL;

    @Value("${xxl.job.db.user}")
    private String DB_USERNAME;

    @Value("${xxl.job.db.password}")
    private String DB_PASSWORD;

    @Value("${xxl.job.accessToken}")
    private String ACCESS_TOKEN;

    @Override
    public String toString() {
        return "XXLConfig{" +
                "DB_DRIVER='" + DB_DRIVER + '\'' +
                ", DB_URL='" + DB_URL + '\'' +
                ", DB_USERNAME='" + DB_USERNAME + '\'' +
                ", DB_PASSWORD='" + DB_PASSWORD + '\'' +
                ", ACCESS_TOKEN='" + ACCESS_TOKEN + '\'' +
                '}';
    }

    @Bean
    public XXLConfig init(){
        System.out.println(toString());
        return new XXLConfig();
    }
}
