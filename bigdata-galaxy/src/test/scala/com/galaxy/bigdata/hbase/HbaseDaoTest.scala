package com.galaxy.bigdata.hbase

import org.junit.Test

import scala.collection.mutable.ListBuffer

class HbaseDaoTest {

  val dao = new HbaseDaoImpl(4)
  val tableName = "t_test"
  val columnFamilyName = "cf1"
  val CFs = Array(columnFamilyName)

  val COLUMN_NAME_NAME = "name"
  val COLUMN_NAME_AGE = "age"

  @Test
  def createTable(): Unit = {
    println("=== create table ====")
    dao.createHTable(tableName, CFs)
  }

  @Test
  def addRow: Unit = {
    println("=== insert record ====")
    dao.addRow(tableName, "12345566", columnFamilyName, COLUMN_NAME_NAME, "Hongten")
    dao.addRow(tableName, "12345566", columnFamilyName, COLUMN_NAME_AGE, "22")

    dao.addRow(tableName, "12345567", columnFamilyName, COLUMN_NAME_NAME, "Tom")
    dao.addRow(tableName, "12345567", columnFamilyName, COLUMN_NAME_AGE, "25")

    dao.addRow(tableName, "12345568", columnFamilyName, COLUMN_NAME_NAME, "Jone")
    dao.addRow(tableName, "12345568", columnFamilyName, COLUMN_NAME_AGE, "30")

    dao.addRow(tableName, "12345569", columnFamilyName, COLUMN_NAME_NAME, "Jobs")
    dao.addRow(tableName, "12345569", columnFamilyName, COLUMN_NAME_AGE, "24")
  }

  @Test
  def getRow(): Unit = {
    println("=== get record ====")
    dao.getRow(tableName, "12345566")
  }

  @Test
  def getAllRows(): Unit = {
    println("=== scan table ====")
    dao.getAllRows(tableName)
  }

  @Test
  def getRowsByRange(): Unit = {
    System.out.println("=== scan record by giving range ====")
    // it will return the '12345567' and '12345568' rows.
    dao.getRowsByRange(tableName, "12345567", "12345569")
  }

  @Test
  def delRow(): Unit = {
    System.out.println("=== delete record ====")
    dao.delRow(tableName, "12345568")
    // only '12345567' row.
    getRowsByRange
  }

  @Test
  def delRowsByRowKeys(): Unit = {
    System.out.println("=== delete batch records ====")
    val rowKeys = new ListBuffer[String]
    rowKeys += "12345566"
    rowKeys += "12345569"
    dao.delRowsByRowKeys(tableName, rowKeys)
    // can not find the '12345566' and '12345569'
    getAllRows
  }

  @Test
  def deleteHTable(): Unit = {
    System.out.println("=== delete table ====")
    dao.deleteHTable(tableName)
  }
}
