package com.galaxy.concurrency.lifecycle;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

/**
 * Created by wangpeng
 * Date: 2018/10/22
 * Time: 10:18
 */
@Component
public class CustomBeanPostProcessor implements BeanPostProcessor {

    @Nullable
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if (bean.getClass() == User.class) {
            System.out.println("调用postProcessBeforeInitialization...");
        }
        return bean;
    }

    @Nullable
    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (bean.getClass() == User.class) {
            System.out.println("调用postProcessAfterInitialization...");
        }
        return bean;
    }
}