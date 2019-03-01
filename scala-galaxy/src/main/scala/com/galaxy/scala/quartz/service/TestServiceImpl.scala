package com.galaxy.scala.quartz.service


import java.util.concurrent.TimeUnit

import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

import scala.util.Random

/**
  * Created by wangpeng
  * Date: 2019-02-28
  * Time: 17:50
  */
@Service
class TestServiceImpl extends TestService {

  private val LOGGER = LoggerFactory.getLogger(classOf[TestServiceImpl])

  private val random = new Random

  override def run(id: String): Unit = {
    LOGGER.info(s"Running job on supervisor, job id: $id ")

    if (random.nextInt(3) == 1) {
      throw new Exception("Randomly generated test exception")
    }

    try {
      Thread.sleep(TimeUnit.MINUTES.toMillis(1))
    } catch {
      case e: InterruptedException =>
        LOGGER.error("Error", e)
    }

    LOGGER.info(s"Completed job on supervisor, job id: $id")
  }
}
