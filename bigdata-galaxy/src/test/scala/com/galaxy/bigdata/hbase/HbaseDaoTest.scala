package com.galaxy.bigdata.hbase

import org.junit.Test

class HbaseDaoTest {

  val dao = new HbaseDaoImpl(4)
  val tableName = "t_test"
  val columnFamilyName = "cf1"
  val CFs = Array(columnFamilyName)

  val COLUMN_NAME_NAME = "name"
  val COLUMN_NAME_AGE = "age"

  @Test
  def main(): Unit = {
    createTable
  }

  def createTable(): Unit = {
    println("=== create table ====")
    dao.createHTable(tableName, CFs)
  }
}
