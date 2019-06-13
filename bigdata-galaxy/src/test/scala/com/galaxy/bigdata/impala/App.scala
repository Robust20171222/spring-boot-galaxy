package com.galaxy.bigdata.impala

import java.sql.{Connection, DriverManager, PreparedStatement, ResultSet}

object App {

  val JDBC_DRIVER = "com.cloudera.impala.jdbc41.Driver"
  val CONNECTION_URL = "jdbc:impala://10.104.132.72:21050"
  val CONNECTION_URL2 = "jdbc:impala://10.104.132.72:21050/default;AuthMech=3;UID=hadoop;PWD=;UseSasl=0"

  def main(args: Array[String]): Unit = {
    var connection: Connection = null
    var ps: PreparedStatement = null
    var rs: ResultSet = null

    try {
      Class.forName(JDBC_DRIVER)
      connection = DriverManager.getConnection(CONNECTION_URL2)
      println(connection.getSchema)
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