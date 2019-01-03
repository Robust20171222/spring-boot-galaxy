package com.galaxy.bigdata.spark

import java.io.File

import org.apache.spark.sql.SparkSession

/**
  * DataFrame API基本操作
  *
  * Created by wangpeng
  * Date: 2019-01-03
  * Time: 20:23
  */
object DataFrameApp {

  def main(args: Array[String]): Unit = {
    val spark = SparkSession.builder().appName("DataFrameApp").master("local[2]").getOrCreate()

    var path: String = null
    val file = new File("/Users/pengwang/Documents/TestProject/ucar/spark/people.json")
    if (file.exists()) {
      path = "file:///Users/pengwang/Documents/TestProject/ucar/spark/people.json"
    } else {
      path = "file:///home/demo/spark-2.1.1-bin-2.6.0-cdh5.13.0/examples/src/main/resources"
    }

    val peopleDF = spark.read.format("json").load(path)
    peopleDF.printSchema()

    // 输出一条记录
    peopleDF.show(1)

    // 查询某一列
    peopleDF.select("name").show()

    // 查询某几列所有的数据，并对列进行计算
    peopleDF.select(peopleDF.col("name"),(peopleDF.col("age") + 10).as("age")).show()

    // 过滤
    peopleDF.filter(peopleDF.col("age") > 19).show()

    // 分组、聚合
    peopleDF.groupBy("age").count().show()

    spark.stop()
  }
}
