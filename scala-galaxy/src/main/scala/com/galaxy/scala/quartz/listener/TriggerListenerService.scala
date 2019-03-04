package com.galaxy.scala.quartz.listener

import org.quartz.{JobExecutionContext, Trigger, TriggerListener}
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

/**
  * Created by wangpeng
  * Date: 2019-03-01
  * Time: 16:35
  */
@Service
class TriggerListenerService extends TriggerListener {

  private val LOGGER = LoggerFactory.getLogger(classOf[TriggerListenerService])

  override def getName: String = "myTriggerListener"

  override def triggerFired(trigger: Trigger, context: JobExecutionContext): Unit = LOGGER.info(" Trigger 被触发了，此时Job 上的 execute() 方法将要被执行")

  override def vetoJobExecution(trigger: Trigger, context: JobExecutionContext): Boolean = {
    LOGGER.info("发现此次Job的相关资源准备存在问题，不便展开任务，返回true表示否决此次任务执行")
    true
  }

  override def triggerMisfired(trigger: Trigger): Unit = LOGGER.info("当前Trigger触发错过了")

  override def triggerComplete(trigger: Trigger, context: JobExecutionContext, triggerInstructionCode: Trigger.CompletedExecutionInstruction): Unit = LOGGER.info("Trigger 被触发并且完成了 Job 的执行,此方法被调用")
}
