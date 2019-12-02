package com.galaxy.bigdata.hive

import org.apache.hadoop.hive.conf.HiveConf
import org.apache.hadoop.hive.metastore.api.MetaException
import org.apache.hadoop.hive.metastore.{IMetaStoreClient, RetryingMetaStoreClient}
import org.junit.Test

import scala.collection.JavaConversions._
import scala.collection.JavaConverters._
import scala.collection.mutable.ListBuffer

/**
  *
  * HiveMetaStoreClient 测试
  *
  * @author pengwang
  * @date 2019/12/02
  */
class HiveMetaStoreClientTest {

  val hiveConf = new HiveConf
  hiveConf.addResource("hive-site.xml")

  var hiveMetaStoreClient: IMetaStoreClient = _

  try {
    hiveMetaStoreClient = RetryingMetaStoreClient.getProxy(hiveConf)
  } catch {
    case e: MetaException => e.printStackTrace
  }

  @Test
  def testGetAllDataBases: Unit = {
    val dbName = "bi_ucar"
    val tableName = "ups_driver_order_h"

    println(this.hiveMetaStoreClient.getAllTables(dbName))
    val table = this.hiveMetaStoreClient.getTable(dbName, tableName)
    println(table.getSd.getLocation)

    var colNameList = new ListBuffer[String]
    table.getSd.getCols.foreach(f => colNameList += f.getName)
    println("列名-------" + colNameList)

    this.hiveMetaStoreClient.getTableColumnStatistics(dbName, tableName, colNameList).foreach(f => println("列统计信息----" + f.getColName))

    // 获取分区信息
    println(this.hiveMetaStoreClient.listPartitionNames(dbName,tableName,-1))

    println(table.getTableType)
    println(table.getPartitionKeys)
  }
}