package com.galaxy.bigdata.hive

import org.apache.hadoop.hive.conf.HiveConf
import org.apache.hadoop.hive.metastore.HiveMetaStoreClient
import org.junit.Test

/**
  * Created by wangpeng
  * Date: 2019-02-01
  * Time: 17:02
  */
class HiveTest {

  @Test
  def testMetaStore: Unit = {
    val hiveConf = new HiveConf()
    hiveConf.set("hive.metastore", "thrift")
    hiveConf.set("hive.metastore.uris", "thrift://namenodetest01.bi:9083")
    var client = new HiveMetaStoreClient(hiveConf)
  }
}
