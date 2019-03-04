package com.galaxy.scala.quartz.listener

import org.quartz.{JobExecutionContext, JobExecutionException, JobListener}
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

/**
  * listen events raised by local execution of jobs
  *
  * Created by wangpeng
  * Date: 2019-02-28
  * Time: 11:30
  */
@Service
class JobsListenerService extends JobListener {

  private val LOGGER = LoggerFactory.getLogger(classOf[JobsListenerService])

  /**
    * 监听器名称
    *
    * @return
    */
  override def getName: String = {
    "Main Listener"
  }

  /**
    * Scheduler 在 JobDetail 将要被执行时调用这个方法
    *
    * @param jobExecutionContext
    */
  override def jobToBeExecuted(jobExecutionContext: JobExecutionContext): Unit = {
    LOGGER.info("Job to be executed " + jobExecutionContext.getJobDetail.getKey.getName)
  }

  /**
    * Scheduler 在 JobDetail 即将被执行，但又被 TriggerListener 否决了时调用这个方法
    *
    * @param jobExecutionContext
    */
  override def jobExecutionVetoed(jobExecutionContext: JobExecutionContext): Unit = {
    LOGGER.info("Job execution vetoed " + jobExecutionContext.getJobDetail().getKey().getName())
  }

  /**
    * Scheduler 在 JobDetail 被执行之后调用这个方法
    *
    * @param jobExecutionContext
    * @param jobException
    */
  override def jobWasExecuted(jobExecutionContext: JobExecutionContext, jobException: JobExecutionException): Unit = {
    LOGGER.info(
      "Job was executed " +
        jobExecutionContext.getJobDetail().getKey().getName() + (if (jobException != null) ", with error" else "")
    )
  }
}

