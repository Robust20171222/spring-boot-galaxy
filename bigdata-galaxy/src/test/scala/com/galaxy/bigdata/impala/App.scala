package com.galaxy.bigdata.impala

import java.sql.{Connection, DriverManager, PreparedStatement, ResultSet}

object App {

  val JDBC_DRIVER = "com.cloudera.impala.jdbc41.Driver"
  val CONNECTION_URL = "jdbc:impala://10.104.132.73:21051"

  def main(args: Array[String]): Unit = {
    var connection: Connection = null
    var ps: PreparedStatement = null
    var rs: ResultSet = null

    try {
      Class.forName(JDBC_DRIVER)
      connection = DriverManager.getConnection(CONNECTION_URL)
      ps = connection.prepareStatement("select * from kudu_test.my_first_table2;")
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