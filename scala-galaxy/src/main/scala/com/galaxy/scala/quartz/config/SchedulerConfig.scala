package com.galaxy.scala.quartz.config

import java.util.Properties

import com.galaxy.scala.quartz.listener.{JobsListenerService, SchedulerListenerService, TriggerListenerService}
import javax.sql.DataSource
import org.quartz.spi.{JobFactory, TriggerFiredBundle}
import org.springframework.beans.factory.config.{AutowireCapableBeanFactory, PropertiesFactoryBean}
import org.springframework.context.{ApplicationContext, ApplicationContextAware}
import org.springframework.context.annotation.{Bean, Configuration}
import org.springframework.core.io.ClassPathResource
import org.springframework.scheduling.annotation.{EnableAsync, EnableScheduling}
import org.springframework.scheduling.quartz.{SchedulerFactoryBean, SpringBeanJobFactory}

/**
  * Quartz调度配置
  *
  * Created by wangpeng
  * Date: 2019-02-28
  * Time: 11:16
  */
@Configuration
@EnableAsync
@EnableScheduling
class SchedulerConfig {

  @Bean
  def jobFactory(applicationContext: ApplicationContext): JobFactory = {
    val jobFactory = new AutowiringSpringBeanJobFactory()
    jobFactory.setApplicationContext(applicationContext)
    jobFactory
  }

  @throws[Exception]
  @Bean
  def schedulerFactoryBean(dataSource: DataSource, jobFactory: JobFactory, jobsListenerService: JobsListenerService, triggerListenerService: TriggerListenerService, schedulerListenerService: SchedulerListenerService): SchedulerFactoryBean = {
    val factory = new SchedulerFactoryBean()
    factory.setDataSource(dataSource)
    factory.setJobFactory(jobFactory)
    factory.setQuartzProperties(quartzProperties)
    factory.setGlobalJobListeners(jobsListenerService) // 注册全局Job监听器
    factory.setGlobalTriggerListeners(triggerListenerService) // 注册全局Trigger监听器
    factory.setSchedulerListeners(schedulerListenerService) // 注册全局Scheduler监听器
    // https://medium.com/@rudra.ramesh/use-following-code-in-supervisor-app-while-creating-schedulerfactorybean-object-now-supervisor-fd2f95365350
    // If you need to disable launching of jobs on supervisor use this:
    //factory.setAutoStartup(false);
    factory
  }

  @Bean
  def quartzProperties: Properties = {
    val propertiesFactoryBean = new PropertiesFactoryBean()
    propertiesFactoryBean.setLocation(new ClassPathResource("/quartz.properties"))
    propertiesFactoryBean.afterPropertiesSet()
    propertiesFactoryBean.getObject
  }
}

class AutowiringSpringBeanJobFactory extends SpringBeanJobFactory with ApplicationContextAware {
  private var beanFactory: AutowireCapableBeanFactory = _

  override def setApplicationContext(context: ApplicationContext): Unit = {
    beanFactory = context.getAutowireCapableBeanFactory
  }

  @throws[Exception]
  override protected def createJobInstance(bundle: TriggerFiredBundle): Object = {
    val job = super.createJobInstance(bundle)
    beanFactory.autowireBean(job)
    job
  }
}
