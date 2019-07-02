package com.galaxy.bigdata.spark

import org.apache.spark.sql.SparkSession

/**
  *
  * @author pengwang
  * @date 2019/07/02
  */
trait SparkSessionWrapper {

  lazy val sparkSession: SparkSession = {
    SparkSession
      .builder()
      .master("local")
      .appName("scala-lab")
      .getOrCreate()
  }

  def stop() = sparkSession.stop()
}