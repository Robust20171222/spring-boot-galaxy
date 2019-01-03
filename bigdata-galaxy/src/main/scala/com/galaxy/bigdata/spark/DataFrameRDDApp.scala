package com.galaxy.bigdata.spark

import org.apache.spark.sql.types.{IntegerType, StringType, StructField, StructType}
import org.apache.spark.sql.{Row, SparkSession}

/**
  * DataFrame RDD API基本操作
  *
  * Created by wangpeng
  * Date: 2019-01-03
  * Time: 20:23
  */
object DataFrameRDDApp {

  def main(args: Array[String]): Unit = {
    val spark = SparkSession.builder().appName("DataFrameRDDApp").master("local[2]").getOrCreate()

    // inferReflection(spark)

    program(spark)
  }

  /**
    * 编程方式
    *
    * @param spark
    */
  private def program(spark: SparkSession) = {
    val path = "file:///Users/pengwang/Documents/TestProject/ucar/spark/infos.txt"
    val rdd = spark.sparkContext.textFile(path)

    val infoRDD = rdd.map(_.split(",")).map(line => Row(line(0).toInt, line(1), line(2).toInt))
    val structType = StructType(Array(StructField("id", IntegerType, true), StructField("name", StringType, true), StructField("age", IntegerType, true)))

    val infoDF = spark.createDataFrame(infoRDD,structType)
    infoDF.printSchema()
    infoDF.show()

    // 通过df的api进行操作
    infoDF.filter(infoDF.col("age") > 30).show()

    // 通过sql的api进行操作
    infoDF.createOrReplaceTempView("infos")
    spark.sql("select * from infos where age > 30").show()

    spark.stop()
  }

  /**
    * 反射方式
    *
    * @param spark
    */
  private def inferReflection(spark: SparkSession) = {
    val path = "file:///Users/pengwang/Documents/TestProject/ucar/spark/infos.txt"
    val rdd = spark.sparkContext.textFile(path)

    import spark.implicits._
    val infoDF = rdd.map(_.split(",")).map(line => Info(line(0).toInt, line(1), line(2).toInt)).toDF()

    infoDF.show()

    infoDF.filter(infoDF.col("age") > 30).show()

    infoDF.createOrReplaceTempView("infos")
    spark.sql("select * from infos where age > 30").show()

    spark.stop()
  }

  case class Info(id: Int, name: String, age: Int)

}
