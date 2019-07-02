package com.galaxy.bigdata.spark

import org.apache.spark.{SparkConf, SparkContext}
import org.junit.Test

/**
  * Spark测试程序
  *
  * @author pengwang
  * @date 2019/07/02
  */
class SparkTest {

  @Test
  def test1: Unit ={
    val conf = new SparkConf().setAppName("test").setMaster("local")
    val sc =new SparkContext(conf)
    val data = Array(1, 2, 3, 4, 5)
    val distData = sc.parallelize(data,10)
    println(distData)
  }
}