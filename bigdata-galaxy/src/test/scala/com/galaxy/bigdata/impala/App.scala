package com.galaxy.bigdata.impala

import java.sql.{Connection, DriverManager, PreparedStatement, ResultSet}

import org.junit.Test

class App {

  val JDBC_DRIVER = "com.cloudera.impala.jdbc41.Driver"
  val JDBC_DRIVER2 = "org.apache.hive.jdbc.HiveDriver"
  val CONNECTION_URL = "jdbc:impala://10.104.132.72:21050"
  val CONNECTION_URL2 = "jdbc:hive2://10.104.132.73:21050/default;auth=noSasl"
  Class.forName(JDBC_DRIVER2)

  @Test
  def testSelect: Unit ={
    var connection: Connection = null
    var ps: PreparedStatement = null
    var rs: ResultSet = null

    try {
      connection = DriverManager.getConnection(CONNECTION_URL2)
      ps = connection.prepareStatement("select * from kudu_test.my_first_table;")
      rs = ps.executeQuery()
      while (rs.next())
        System.out.println("s1=" + rs.getString(1) + ",  s2=" + rs.getString(2))
    } catch {
      case e: Exception => e.printStackTrace()
    } finally {
      if (rs != null) {
        rs.close()
      }

      if (ps != null) {
        ps.close()
      }

      if (connection != null) {
        connection.close()
      }
    }
  }
}