package com.test.hadoop

import org.apache.hadoop.conf.Configuration
import org.apache.hadoop.fs.{FileSystem, Path}
import org.junit.Test

/**
  * Created by wangpeng
  * Date: 2019-02-01
  * Time: 20:56
  */
class HdfsTest {

  /**
    * 测试连接NameNode
    */
  @Test
  def testFileSystem: Unit = {
    val conf = new Configuration()
    conf.set("fs.defaultFS", "hdfs://quickstart.cloudera:8020")
    val fs: FileSystem = FileSystem.get(conf)
    val path = new Path("hdfs://quickstart.cloudera:8020/user/hive/warehouse/hive_connect.db/cities_orc/.streamreactor_hive_sink_orc_0")
    print(fs.delete(new Path("/user/wangpeng"),true))
  }
}
