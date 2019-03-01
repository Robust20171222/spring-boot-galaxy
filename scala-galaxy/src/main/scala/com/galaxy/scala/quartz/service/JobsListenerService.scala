package com.galaxy.scala.quartz.service

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

  val LOGGER = LoggerFactory.getLogger(classOf[JobsListenerService])

  /**
    * 监听器名称
    * @return
    */
  override def getName: String = {
    "Main Listener"
  }

  override def jobToBeExecuted(jobExecutionContext: JobExecutionContext): Unit = {
    LOGGER.info("Job to be executed " + jobExecutionContext.getJobDetail.getKey.getName)
  }

  override def jobExecutionVetoed(jobExecutionContext: JobExecutionContext): Unit = {
    LOGGER.info("Job execution vetoed " + jobExecutionContext.getJobDetail().getKey().getName())
  }

  override def jobWasExecuted(jobExecutionContext: JobExecutionContext, jobException: JobExecutionException): Unit = {
    LOGGER.info(
      "Job was executed " +
        jobExecutionContext.getJobDetail().getKey().getName() + (if (jobException != null) ", with error" else "")
    )
  }
}

