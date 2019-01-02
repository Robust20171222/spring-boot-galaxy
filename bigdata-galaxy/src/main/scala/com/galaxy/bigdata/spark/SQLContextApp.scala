package com.galaxy.bigdata.spark

import java.io.File

import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.sql.SQLContext

/**
  * Created by wangpeng
  * Date: 2018-12-31
  * Time: 16:14
  */
object SQLContextApp {

  def main(args: Array[String]): Unit = {

    val conf = new SparkConf()
    conf.setAppName("SQLContextApp").setMaster("local[2]")

    val sc = new SparkContext(conf)
    val sqlContext = new SQLContext(sc)

    var path: String = null
    val file = new File("/Users/pengwang/Documents/TestProject/ucar/spark/people.json")
    if (file.exists()) {
      path = "file:///Users/pengwang/Documents/TestProject/ucar/spark/people.json"
    } else {
      path = "file:///home/demo/spark-2.1.1-bin-2.6.0-cdh5.13.0/examples/src/main/resources"
    }

    val people = sqlContext.read.format("json").load(path)
    people.printSchema()
    people.show()

    sc.stop()
  }
}
