package com.galaxy.bigdata.hbase

import java.sql.{DriverManager, PreparedStatement}

import org.junit.{After, Test}

/**
  * Phoenix测试
  */
class PhoenixJDBCTest {

  Class.forName("org.apache.phoenix.jdbc.PhoenixDriver")
  val connection = DriverManager.getConnection("jdbc:phoenix:quickstart.cloudera:2181")
  var statement: PreparedStatement = _

  @Test
  def testCreateTable: Unit = {
    statement = connection.prepareStatement("CREATE TABLE IF NOT EXISTS tbl_order (\n\tid BIGINT not null primary key,\n\torder_code char(20),\n\ttotal_amount decimal(10,2),\n\tcreate_time date,\n\tuser_id bigint\n)")
    val result = statement.executeUpdate()
    println(result)

    //    while(resultSet.next()){
    //        resultSet.getString("name")
    //    }
  }

  @Test
  def testInsert: Unit = {
    val sql = ""
    statement = connection.prepareStatement(sql)
    val result = statement.executeUpdate()
    println(result)

  }

  @After
  def close: Unit = {
    if (statement != null) {
      statement.close()
    }
    if (connection != null) {
      connection.close()
    }
  }
}
