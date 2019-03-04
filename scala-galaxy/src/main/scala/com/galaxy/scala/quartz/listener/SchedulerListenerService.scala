package com.galaxy.scala.quartz.listener

import org.quartz._
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

/**
  * Created by wangpeng
  * Date: 2019-03-01
  * Time: 17:00
  */
@Service
class SchedulerListenerService extends SchedulerListener {

  private val LOGGER = LoggerFactory.getLogger(classOf[SchedulerListenerService])

  override def jobScheduled(trigger: Trigger): Unit = LOGGER.info("任务被部署时被执行")

  override def jobUnscheduled(triggerKey: TriggerKey): Unit = LOGGER.info("任务被卸载时被执行")

  override def triggerFinalized(trigger: Trigger): Unit = LOGGER.info("任务完成了它的使命，光荣退休时被执行")

  override def triggerPaused(triggerKey: TriggerKey): Unit = LOGGER.info(triggerKey + "（一个触发器）被暂停时被执行")

  override def triggersPaused(triggerGroup: String): Unit = LOGGER.info(triggerGroup + "所在组的全部触发器被停止时被执行")

  override def triggerResumed(triggerKey: TriggerKey): Unit = LOGGER.info(triggerKey + "（一个触发器）被恢复时被执行")

  override def triggersResumed(triggerGroup: String): Unit = LOGGER.info(triggerGroup + "所在组的全部触发器被回复时被执行")

  override def jobAdded(jobDetail: JobDetail): Unit = LOGGER.info("一个JobDetail被动态添加进来")

  override def jobDeleted(jobKey: JobKey): Unit = LOGGER.info(jobKey + "被删除时被执行")

  override def jobPaused(jobKey: JobKey): Unit = LOGGER.info(jobKey + "被暂停时被执行")

  override def jobsPaused(jobGroup: String): Unit = LOGGER.info(jobGroup + "(一组任务）被暂停时被执行")

  override def jobResumed(jobKey: JobKey): Unit = LOGGER.info(jobKey + "被恢复时被执行")

  override def jobsResumed(jobGroup: String): Unit = LOGGER.info(jobGroup + "(一组任务）被回复时被执行")

  override def schedulerError(msg: String, cause: SchedulerException): Unit = {
    LOGGER.info("出现异常" + msg + "时被执行")
    cause.printStackTrace()
  }

  override def schedulerInStandbyMode(): Unit = LOGGER.info("scheduler被设为standBy等候模式时被执行")

  override def schedulerStarted(): Unit = LOGGER.info("scheduler启动时被执行")

  override def schedulerStarting(): Unit = LOGGER.info("scheduler正在启动时被执行")

  override def schedulerShutdown(): Unit = LOGGER.info("scheduler关闭时被执行")

  override def schedulerShuttingdown(): Unit = LOGGER.info("scheduler正在关闭时被执行")

  override def schedulingDataCleared(): Unit = LOGGER.info("scheduler中所有数据包括jobs, triggers和calendars都被清空时被执行")
}
