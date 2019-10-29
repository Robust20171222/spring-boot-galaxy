package com.galaxy.bigdata.hive;

import java.sql.*;

/**
 * @author pengwang
 * @date 2019/10/24
 */
public class HiveJdbcClient {

    // 数据库连接信息
    private static final String driverName = "org.apache.hive.jdbc.HiveDriver";
    private static final String username = "cdapadmin";
    private static final String password = "Ucar_cdap_admin_2017";
    // private static final String connection_url = "jdbc:hive2://10.104.108.87:5181,10.104.108.88:5181/default;serviceDiscoveryMode=zooKeeper;zooKeeperNamespace=hiveserver2-zk-ha;hive.server2.proxy.user=hadoop";
    private static final String connection_url = "jdbc:hive2://10.204.245.43:5181,10.204.245.44:5181,10.204.245.45:5181/default;serviceDiscoveryMode=zooKeeper;zooKeeperNamespace=hiveserver2-zk-ha;hive.server2.proxy.user=hadoop";

    public static void main(String[] args) {
//        String tableName = "bi_test.t_mid_driver_rank_daily_stat_api";
//        String partitionPrefix = "dt";

        String tableName = "bi_test.topic_order_stat_hive_concatenate";
        String partitionPrefix = "date_id";
        concatenate(tableName, partitionPrefix);
    }

    /**
     * 测试Hive文件合并
     */
    public static void concatenate(String tableName, String partitionPrefix) {

        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet res = null;

        try {
            Class.forName(driverName);
            con = DriverManager.getConnection(connection_url, username, password);

            // 查询表分区
            String sql = "show partitions " + tableName;
            System.out.println("Running: " + sql);
            stmt = con.prepareStatement(sql);
            res = stmt.executeQuery(sql);

            // 循环取出表分区，执行合并操作
            while (res.next()) {
                try {
                    String partition = res.getString(1);
                    int index = partition.indexOf("=");
                    String date = partition.substring(index + 1);
                    String sql1 = "alter table " + tableName + " partition(" + partitionPrefix + "=" + "\"" + date + "\"" + ") concatenate";
                    stmt = con.prepareStatement(sql1);
//            sql = "alter table bi_ucar.bas_driver_history partition(dt=\"2019-06-28\") concatenate";
                    System.out.println(sql1);
                    stmt.executeUpdate(sql1);
                } catch (SQLException e) {
                    e.printStackTrace();
                } finally {
                    try {
                        if (stmt != null) {
                            stmt.close();
                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
        } catch (SQLException |
                ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            try {
                if (res != null) {
                    res.close();
                }

                if (stmt != null) {
                    stmt.close();
                }

                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        while (true) {

        }
    }
}