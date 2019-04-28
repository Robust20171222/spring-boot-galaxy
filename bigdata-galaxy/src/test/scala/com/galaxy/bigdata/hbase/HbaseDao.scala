package com.galaxy.bigdata.hbase

import org.apache.hadoop.hbase.client.HTableInterface

import scala.collection.mutable.ListBuffer

trait HbaseDao {

  /**
    * initial table
    *
    * @param tableName
    * @return
    */
  def getHTableFromPool(tableName: String): HTableInterface

  /**
    * check if the table is exist
    *
    * @param tableName
    * @return
    */
  def isHTableExist(tableName: String): Boolean

  /**
    * create table
    *
    * @param tableName
    * @param columnFamilys
    */
  def createHTable(tableName: String, columnFamilys: Array[String]): Unit

  /**
    * insert new row
    *
    * @param tableName
    * @param rowKey
    * @param columnFamily
    * @param column
    * @param value
    */
  def addRow(tableName: String, rowKey: String, columnFamily: String, column: String, value: String): Unit

  /**
    * get row by row key
    *
    * @param tableName
    * @param rowKey
    */
  def getRow(tableName: String, rowKey: String): Unit

  def getAllRows(tableName: String): Unit

  /**
    * get rows by giving range
    *
    * @param tableName
    * @param startRowKey
    * @param endRowKey
    */
  def getRowsByRange(tableName: String, startRowKey: String, endRowKey: String): Unit

  /**
    * delete row
    *
    * @param tableName
    * @param rowKey
    */
  def delRow(tableName: String, rowKey: String): Unit

  /**
    * delete rows by row keys
    *
    * @param tableName
    * @param rowKeys
    */
  def delRowsByRowKeys(tableName: String, rowKeys: ListBuffer[String]): Unit

  import org.apache.hadoop.hbase.client.HTableInterface

  /**
    * auto flush data when close
    *
    * @param table
    */
  def closeAutoFlush(table: HTableInterface): Unit

  import org.apache.hadoop.hbase.client.HTableInterface

  /**
    * close table
    *
    * @param table
    */
  def closeTable(table: HTableInterface): Unit

  /**
    * close pool connection
    */
  def closePoolConnection(): Unit

  /**
    * delete table
    *
    * @param tableName
    */
  def deleteHTable(tableName: String): Unit
}
