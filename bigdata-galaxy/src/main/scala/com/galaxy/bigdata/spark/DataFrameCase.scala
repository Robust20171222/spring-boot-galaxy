package com.galaxy.bigdata.spark

import org.apache.spark.sql.SparkSession

/**
  * DataFrame 中的其他操作
  *
  * Created by wangpeng
  * Date: 2019-01-03
  * Time: 21:19
  */
object DataFrameCase {

  def main(args: Array[String]): Unit = {

    val spark = SparkSession.builder().appName("DataFrameRDDApp").master("local[2]").getOrCreate()

  }

  case class Student(id: Int, name: String, phone: String, email: String)

}
