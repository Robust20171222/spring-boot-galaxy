package com.galaxy.concurrency.aop;

import org.junit.Test;
import org.springframework.aop.aspectj.autoproxy.AspectJAwareAdvisorAutoProxyCreator;
import org.springframework.aop.framework.autoproxy.AbstractAutoProxyCreator;
import org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory;
import org.springframework.beans.factory.xml.DefaultBeanDefinitionDocumentReader;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.transaction.annotation.SpringTransactionAnnotationParser;
import org.springframework.transaction.config.TxNamespaceHandler;
import org.springframework.transaction.interceptor.TransactionInterceptor;

/**
 * Created by wangpeng
 * Date: 2018/10/22
 * Time: 11:00
 */
public class TestAop {

    @Test
    public void testAop() {
        ApplicationContext ac = new ClassPathXmlApplicationContext("spring/aop.xml");

        Dao dao = (Dao)ac.getBean("daoImpl");
        dao.select();

        // 读取配置文件，获取BeanDefinition对象
        DefaultBeanDefinitionDocumentReader defaultBeanDefinitionDocumentReader = null;

        AspectJAwareAdvisorAutoProxyCreator aspectJAwareAdvisorAutoProxyCreator = null;
        AbstractAutoProxyCreator abstractAutoProxyCreator = null;

        AbstractAutowireCapableBeanFactory abstractAutowireCapableBeanFactory = null;
    }

    @Test
    public void testTransaction() {
        TxNamespaceHandler txNamespaceHandler;

        SpringTransactionAnnotationParser springTransactionAnnotationParser;

        // 事务拦截器
        TransactionInterceptor transactionInterceptor;
    }
}
