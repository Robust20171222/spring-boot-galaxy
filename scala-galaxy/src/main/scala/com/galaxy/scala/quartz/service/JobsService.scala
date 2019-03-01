package com.galaxy.scala.quartz.service

import java.util.UUID

import com.galaxy.scala.quartz.controllers.JobStatus
import com.galaxy.scala.quartz.job.TestJob1
import javax.annotation.PostConstruct
import org.quartz._
import org.quartz.impl.matchers.GroupMatcher
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.scheduling.quartz.SchedulerFactoryBean
import org.springframework.stereotype.Service

import scala.collection.JavaConversions._
import scala.collection.mutable.ListBuffer

/**
  * 提供Quartz Job信息业务逻辑操作
  *
  * Created by wangpeng
  * Date: 2019-02-28
  * Time: 14:53
  */
@Service
class JobsService {

  private val groupName = "normal-group"
  private var scheduler: Scheduler = _

  @Autowired
  private var schedulerFactoryBean: SchedulerFactoryBean = _

  @PostConstruct
  def init: Unit = {
    this.scheduler = schedulerFactoryBean.getScheduler
  }

  @throws[SchedulerException]
  def addNewJobs(jobs: Int): ListBuffer[String] = {
    val list = ListBuffer[String]()
    for (i <- 0 until jobs) {
      list += addNewJob
      Thread.sleep(1000)
    }
    list
  }

  @throws[SchedulerException]
  def addNewJob: String = {
    val id = UUID.randomUUID().toString
    val job = JobBuilder.newJob(classOf[TestJob1]).withIdentity(id, groupName).requestRecovery(true).build()
    val trigger = TriggerBuilder.newTrigger().withIdentity(id + "-trigger", groupName).startNow().withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(30)).build()

    scheduler.scheduleJob(job, trigger)
    id
  }

  /**
    * 删除任务
    *
    * @param id
    * @throws
    * @return
    */
  @throws[SchedulerException]
  def deleteJob(id: String): Boolean = {
    val jobKey = new JobKey(id, groupName)
    scheduler.deleteJob(jobKey)
  }

  /**
    * 获取任务名称
    *
    * @throws
    * @return
    */
  @throws[SchedulerException]
  def getJobs: List[String] = {
    this.scheduler.getJobKeys(GroupMatcher.jobGroupEquals(groupName)).map(key => key.getName).toList
  }


  /**
    * 获取任务的状态
    *
    * @throws
    * @return
    */
  @throws[SchedulerException]
  def getJobsStatuses: ListBuffer[JobStatus] = {
    val list = ListBuffer[JobStatus]()
    for (jobKey <- this.scheduler.getJobKeys(GroupMatcher.jobGroupEquals(groupName))) {
      val jobDetail = this.scheduler.getJobDetail(jobKey)
      val triggers = this.scheduler.getTriggersOfJob(jobDetail.getKey)

      for (trigger <- triggers) {
        val triggerState = this.scheduler.getTriggerState(trigger.getKey)

        if (Trigger.TriggerState.COMPLETE.equals(triggerState)) {
          list += JobStatus(jobKey.getName, true)
        } else {
          list += JobStatus(jobKey.getName, false)
        }
      }
    }
    list
  }
}
