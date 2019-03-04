package com.galaxy.scala.quartz.job

import com.galaxy.scala.quartz.service.TestService
import org.quartz.{DisallowConcurrentExecution, Job, JobExecutionContext, JobExecutionException}
import org.springframework.beans.factory.annotation.Autowired

/**
  * Created by wangpeng
  * Date: 2019-02-28
  * Time: 11:38
  */
@DisallowConcurrentExecution
class TestJob extends Job {

  @Autowired
  private var testService: TestService = _

  @throws[JobExecutionException]
  override def execute(jobExecutionContext: JobExecutionContext): Unit = {
    try {
      val id = jobExecutionContext.getJobDetail().getKey().getName()
      testService.run(id)
    } catch {
      case e: Exception =>
        throw new JobExecutionException(e)
    }
  }
}
