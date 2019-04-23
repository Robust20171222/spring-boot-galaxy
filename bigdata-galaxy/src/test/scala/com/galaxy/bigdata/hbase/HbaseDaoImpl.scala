package com.galaxy.bigdata.hbase

import java.io.IOException

import org.apache.hadoop.conf.Configuration
import org.apache.hadoop.hbase._
import org.apache.hadoop.hbase.client._

import scala.collection.JavaConversions._

class HbaseDaoImpl(poolSize: Int) extends HbaseDao {

  var conf: Configuration = _
  var hAdmin: HBaseAdmin = _
  var pool: HTablePool = _

  try {
    conf = HBaseConfiguration.create()
    conf.set("hbase.zookeeper.quorum", "hadoop21-test1-rgtj5-tj1:2181")

    hAdmin = new HBaseAdmin(conf)

    val defaultPoolSize = 5
    pool = new HTablePool(conf, if (poolSize <= 0) defaultPoolSize else poolSize)
  } catch {
    case e: MasterNotRunningException => e.printStackTrace()
    case e: ZooKeeperConnectionException => e.printStackTrace()
    case e: IOException => e.printStackTrace()
  }

  override def getHTableFromPool(tableName: String): HTableInterface = {
    pool.getTable(tableName)
  }

  override def isHTableExist(tableName: String): Boolean = {
    try {
      hAdmin.tableExists(tableName)
    } catch {
      case e: IOException => e.printStackTrace()
    }
    false
  }

  override def createHTable(tableName: String, columnFamilys: Array[String]): Unit = {
    if (!isHTableExist(tableName)) {
      val tableDescriptor = new HTableDescriptor(TableName.valueOf(tableName))
      // The Hbase suggested the number of column family should be less than 3.
      // Normally, there only have 1 column family.
      columnFamilys.foreach(cfName => {
        import org.apache.hadoop.hbase.HColumnDescriptor
        val hColumnDescriptor = new HColumnDescriptor(cfName)
        tableDescriptor.addFamily(hColumnDescriptor)
      })

      try {
        hAdmin.createTable(tableDescriptor)
      } catch {
        case e: IOException => e.printStackTrace()
      }
      println("The table [" + tableName + "]  is created.")
    } else {
      println("The table [" + tableName + "]  is existing already.")
    }
  }

  override def addRow(tableName: String, rowKey: String, columnFamily: String, column: String, value: String): Unit = {
    if (isHTableExist(tableName)) {
      val table = getHTableFromPool(tableName)
      val put = new Put(rowKey.getBytes())
      put.add(columnFamily.getBytes(), column.getBytes(), value.getBytes())
      try {
        table.put(put)
      } catch {
        case e: IOException => e.printStackTrace()
      }
      println("Insert into table [" + tableName + "], Rowkey=[" + rowKey + "], Column=[" + columnFamily + ":" + column + "], Vlaue=[" + value + "].")
      closeTable(table)
    } else {
      println("The table [" + tableName + "] does not exist.")
    }
  }

  override def getRow(tableName: String, rowKey: String): Unit = {
    if (isHTableExist(tableName)) {
      val table = getHTableFromPool(tableName)
      val get = new Get(rowKey.getBytes())

      try {
        val result = table.get(get)
        var columnName = ""
        var timeStamp = ""
        var columnFamily = ""
        var value = ""

        result.rawCells().foreach(cell => {
          import org.apache.hadoop.hbase.CellUtil
          timeStamp = String.valueOf(cell.getTimestamp)
          columnFamily = new String(CellUtil.cloneFamily(cell))
          columnName = new String(CellUtil.cloneQualifier(cell))
          value = new String(CellUtil.cloneValue(cell))

          println("Get from table [" + tableName + "], Rowkey=[" + rowKey + "], Column=[" + columnFamily + ":" + columnName + "], Timestamp=[" + timeStamp + "], Vlaue=[" + value + "].")
        })
      } catch {
        case e: IOException => e.printStackTrace()
      }
      closeTable(table)
    } else {
      println("The table [" + tableName + "] does not exist.")
    }
  }

  override def getAllRows(tableName: String): Unit = {
    if (isHTableExist(tableName)) {
      val scan = new Scan()
      scanHTable(tableName, scan)
    } else {
      println("The table [" + tableName + "] does not exist.");
    }
  }

  private def scanHTable(tableName: String, scan: Scan): Unit = {
    try {
      val table = getHTableFromPool(tableName)
      val results = table.getScanner(scan)

      results.foreach(result => {
        var rowKey = ""
        var columnName = ""
        var timeStamp = ""
        var columnFamily = ""
        var value = ""

        result.rawCells.foreach(cell => {
          import org.apache.hadoop.hbase.CellUtil
          rowKey = new String(CellUtil.cloneRow(cell))
          timeStamp = String.valueOf(cell.getTimestamp)
          columnFamily = new String(CellUtil.cloneFamily(cell))
          columnName = new String(CellUtil.cloneQualifier(cell))
          value = new String(CellUtil.cloneValue(cell))

          System.out.println("Get from table [" + tableName + "], Rowkey=[" + rowKey + "], Column=[" + columnFamily + ":" + columnName + "], Timestamp=[" + timeStamp + "], Vlaue=[" + value + "].")
        })
      })

      closeTable(table)
    } catch {
      case e: IOException => e.printStackTrace()
    }
  }

  override def getRowsByRange(tableName: String, startRowKey: String, endRowKey: String): Unit = {
    if (isHTableExist(tableName)) {
      val scan = new Scan
      scan.setStartRow(startRowKey.getBytes)
      // not equals Stop Row Key, it mean the result does not include the stop row record(exclusive).
      // the hbase version is 0.98.9
      scan.setStopRow(endRowKey.getBytes)
      scanHTable(tableName, scan)
    } else {
      System.out.println("The table [" + tableName + "] does not exist.")
    }
  }

  override def delRow(tableName: String, rowKey: String): Unit = {
    if (isHTableExist(tableName)) {
      val table = getHTableFromPool(tableName)
      deleteRow(table, rowKey)
    } else {
      println("The table [" + tableName + "] does not exist.")
    }
  }

  import java.io.IOException

  import org.apache.hadoop.hbase.client.HTableInterface

  private def deleteRow(table: HTableInterface, rowKey: String): Unit = {
    val del = new Delete(rowKey.getBytes)
    try {
      table.delete(del)
      println("Delete from table [" + new String(table.getTableName) + "], Rowkey=[" + rowKey + "].")
      closeTable(table)
    } catch {
      case e: IOException =>
        e.printStackTrace()
    }
  }

  override def delRowsByRowKeys(tableName: String, rowKeys: List[String]): Unit = {
    if (rowKeys != null && rowKeys.size > 0) {
      for (rowKey <- rowKeys) {
        delRow(tableName, rowKey)
      }
    }
  }

  import java.io.IOException

  override def deleteHTable(tableName: String): Unit = {
    if (isHTableExist(tableName)) try {
      hAdmin.disableTable(tableName.getBytes)
      hAdmin.deleteTable(tableName.getBytes)
      println("The table [" + tableName + "] is deleted.")
    } catch {
      case e: IOException =>
        e.printStackTrace()
    } else {
      println("The table [" + tableName + "] does not exist.")
    }
  }

  override def closeAutoFlush(table: HTableInterface): Unit = {
    table.setAutoFlush(false, false)
  }

  import java.io.IOException

  import org.apache.hadoop.hbase.client.HTableInterface

  override def closeTable(table: HTableInterface): Unit = {
    try
      table.close()
    catch {
      case e: IOException =>
        e.printStackTrace()
    }
  }

  import java.io.IOException

  override def closePoolConnection(): Unit = {
    try
      pool.close
    catch {
      case e: IOException =>
        e.printStackTrace()
    }
  }
}
