package com.galaxy.bigdata.spark

import org.apache.spark.sql.SparkSession

/**
  * Parquet文件操作
  *
  * Created by wangpeng
  * Date: 2019-01-04
  * Time: 11:38
  */
object ParquetApp {

  def main(args: Array[String]): Unit = {
    val spark = SparkSession.builder().appName("ParquetApp").master("local[2]").getOrCreate()

    val userDF = spark.read.format("parquet").load("file:///Users/pengwang/Documents/TestProject/ucar/spark/users.parquet")
    userDF.printSchema()
    userDF.show()

    userDF.select("name", "favorite_color").show()
    // 通过json方式写出
    // userDF.select("name", "favorite_color").write.format("json").save("file:///Users/pengwang/Documents/TestProject/ucar/spark/jsonout")

    spark.read.load("file:///Users/pengwang/Documents/TestProject/ucar/spark/users.parquet").show()

    // 报错，因为默认读取格式就是parquet
    // spark.read.load("file:///Users/pengwang/Documents/TestProject/ucar/spark/people.json").show()

    spark.read.format("parquet").option("path", "file:///Users/pengwang/Documents/TestProject/ucar/spark/users.parquet").load().show()

    spark.stop()
  }
}
