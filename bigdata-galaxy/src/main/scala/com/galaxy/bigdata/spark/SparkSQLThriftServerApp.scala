package com.galaxy.bigdata.spark

import java.sql.DriverManager

/**
  * 通过JDBC方式访问
  * Created by wangpeng
  * Date: 2019-01-01
  * Time: 10:05
  */
object SparkSQLThriftServerApp {

  def main(args: Array[String]): Unit = {
    Class.forName("org.apache.hive.jdbc.HiveDriver")

    val conn = DriverManager.getConnection("jdbc:hive2://quickstart.cloudera:10005","demo","demo")
    val pstmt = conn.prepareStatement("select * from hive_test.t1")
    val rs = pstmt.executeQuery()
    while (rs.next()){
      println(rs.getString(1))
    }
  }
}
