package com.galaxy.bigdata.hive;

import org.junit.Test;

import java.sql.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author pengwang
 * @date 2019/05/20
 */
public class HiveJdbcClientTest {

    private static final ExecutorService executorService = Executors.newFixedThreadPool(50);

    // 数据库连接信息
    private static final String driverName = "org.apache.hive.jdbc.HiveDriver";
    private static final String username = "cdapadmin";
    private static final String password = "Ucar_cdap_admin_2017";
    private static final String connection_url = "jdbc:hive2://10.104.108.87:5181,10.104.108.88:5181/default;serviceDiscoveryMode=zooKeeper;zooKeeperNamespace=hiveserver2-zk-ha;hive.server2.proxy.user=hadoop";


    /**
     * 测试Hive文件合并
     */
    @Test
    public void testConcatenate() {
        Connection con = null;
        Statement stmt = null;
        ResultSet res = null;

        try {
            Class.forName(driverName);
            con = DriverManager.getConnection(connection_url, username, password);
            stmt = con.createStatement();
            String tableName = "bi_ucar.t_mid_driver_rank_daily_stat_api";

            // 查询表分区
            String sql = "show partitions " + tableName;
            System.out.println("Running: " + sql);
            res = stmt.executeQuery(sql);

            // 循环取出表分区，执行合并操作
            while (res.next()) {
                String partition = res.getString(1);
                int index = partition.indexOf("=");
                String date = partition.substring(index + 1);
                executorService.submit(() -> {
                    Connection con1 = null;
                    PreparedStatement stmt1 = null;
                    try {
                        con1 = DriverManager.getConnection(connection_url, username, password);
                        String sql1 = "alter table " + tableName + " partition(dt=" + "\"" + date + "\"" + ") concatenate";
                        stmt1 = con1.prepareStatement(sql1);
//            sql = "alter table bi_ucar.bas_driver_history partition(dt=\"2019-06-28\") concatenate";
                        System.out.println(sql1);
                        stmt1.executeUpdate(sql1);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    } finally {
                        try {
                            if (stmt1 != null) {
                                stmt1.close();
                            }

                            if (con1 != null) {
                                con1.close();
                            }
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }

        } catch (SQLException | ClassNotFoundException e) {
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

        while (true){

        }
    }
}