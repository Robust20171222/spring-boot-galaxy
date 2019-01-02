package com.galaxy.bigdata.spark

import java.io.File

import org.apache.spark.sql.SparkSession

/**
  * Created by wangpeng
  * Date: 2019-01-01
  * Time: 09:00
  */
object SparkSessionApp {

  def main(args: Array[String]): Unit = {
    val spark = SparkSession.builder().appName("SparkSessionApp").master("local[2]").getOrCreate()

    var path: String = null
    val file = new File("/Users/pengwang/Documents/TestProject/ucar/spark/people.json")
    if (file.exists()) {
      path = "file:///Users/pengwang/Documents/TestProject/ucar/spark/people.json"
    } else {
      path = "file:///home/demo/spark-2.1.1-bin-2.6.0-cdh5.13.0/examples/src/main/resources"
    }

    val people = spark.read.json(path)
    people.show()

    spark.stop()
  }
}

