package com.galaxy.autoconfigure;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static com.galaxy.autoconfigure.Hello.*;

/**
 * Created by wangpeng
 * Date: 2018/9/4
 * Time: 17:46
 */
@Configuration
@ConditionalOnClass(Hello.class)
@EnableConfigurationProperties(HelloProperties.class)
public class HelloAutoConfiguration {

    @Autowired
    private HelloProperties helloProperties;

    @Bean
    @ConditionalOnMissingBean
    public HelloConfig greeterConfig() {

        String userName = helloProperties.getUserName() == null ? System.getProperty("user.name") : helloProperties.getUserName();
        String morningMessage = helloProperties.getMorningMessage() == null ? "Good Morning" : helloProperties.getMorningMessage();
        String afternoonMessage = helloProperties.getAfternoonMessage() == null ? "Good Afternoon" : helloProperties.getAfternoonMessage();
        String eveningMessage = helloProperties.getEveningMessage() == null ? "Good Evening" : helloProperties.getEveningMessage();
        String nightMessage = helloProperties.getNightMessage() == null ? "Good Night" : helloProperties.getNightMessage();

        HelloConfig helloConfig = new HelloConfig();
        helloConfig.put(USER_NAME, userName);
        helloConfig.put(MORNING_MESSAGE, morningMessage);
        helloConfig.put(AFTERNOON_MESSAGE, afternoonMessage);
        helloConfig.put(EVENING_MESSAGE, eveningMessage);
        helloConfig.put(NIGHT_MESSAGE, nightMessage);
        return helloConfig;
    }

    @Bean
    @ConditionalOnMissingBean
    public Hello hello(HelloConfig helloConfig) {
        return new Hello(helloConfig);
    }
}
