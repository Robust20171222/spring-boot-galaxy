package com.galaxy.bigdata.kudu

import java.util

import org.apache.kudu.client.{CreateTableOptions, KuduClient}
import org.apache.kudu.{ColumnSchema, Schema, Type}
import org.junit.Test

import scala.collection.JavaConversions._
import scala.collection.mutable.ListBuffer

/**
  * 测试Kudu java client常用API
  *
  * Created by wangpeng
  * Date: 2019-02-18
  * Time: 10:56
  *
  * @example http://harshj.com/writing-a-simple-kudu-java-api-program/
  */
class KuduTest {

  lazy val KUDU_MASTERS = System.getProperty("kuduMasters", "quickstart.cloudera:7051")
  lazy val kuduClient = new KuduClient.KuduClientBuilder(KUDU_MASTERS).build
  var tableName = "java_example"

  /**
    * 测试Kudu创建表
    */
  @Test
  def testCreateTable: Unit = {
    var columns = new ListBuffer[ColumnSchema]
    columns += new ColumnSchema.ColumnSchemaBuilder("username", Type.STRING).key(true).build()
    columns += new ColumnSchema.ColumnSchemaBuilder("age", Type.INT32).build()
    val schema = new Schema(columns)

    // 创建hash分区
    val cto: CreateTableOptions = new CreateTableOptions
    val hashKeys: util.List[String] = new util.ArrayList[String](1)
    hashKeys.add("username")
    val numBuckets: Int = 8
    cto.addHashPartitions(hashKeys, numBuckets)

    // Create the table. 执行创建表操作
    if (!kuduClient.tableExists(tableName)) {
      kuduClient.createTable(tableName, schema, cto)
      System.out.println("Created table " + tableName)
    }
  }

  /**
    * 测试插入数据
    */
  @Test
  def testInsert: Unit = {
    val session = kuduClient.newSession()
    val table = kuduClient.openTable(tableName)

    val insert = table.newInsert()
    insert.getRow.addString("username", "harshj-3")
    insert.getRow.addInt("age", 25)

    session.apply(insert)
  }

  /**
    * 测试更新数据
    */
  @Test
  def testUpdate: Unit = {
    val session = kuduClient.newSession()
    val table = kuduClient.openTable(tableName)
    val update = table.newUpdate()
    update.getRow.addString("username", "harshj")
    // Change from 25 previously written
    update.getRow.addInt("age", 26)
    session.apply(update)
  }

  /**
    * 测试查询数据
    */
  @Test
  def testRead: Unit = {
    val columnNames = new ListBuffer[String]
    columnNames.add("username")
    val table = kuduClient.openTable(tableName)
    val scanner = kuduClient.newScannerBuilder(table).setProjectedColumnNames(columnNames).build

    while (scanner.hasMoreRows) {
      scanner.nextRows().iterator().foreach(f => println(f.getString("username")))
    }
  }

  /**
    * 测试根据主键删除数据
    */
  @Test
  def testDelByPK: Unit = {
    val session = kuduClient.newSession()
    val table = kuduClient.openTable(tableName)
    val delete = table.newDelete
    delete.getRow.addString("username", "harshj")
    session.apply(delete)
  }

  /**
    * 测试删除Kudu表
    */
  @Test
  def testDelTable: Unit = {
    this.kuduClient.deleteTable(tableName)
  }
}
