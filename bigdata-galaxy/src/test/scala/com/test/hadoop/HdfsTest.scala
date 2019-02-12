package com.test.hadoop

import org.apache.hadoop.conf.Configuration
import org.apache.hadoop.fs.FileSystem
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
  def testConnNamenode: Unit ={
    val conf = new Configuration()
    conf.set("fs.defaultFS", "hdfs://namenodetest01.bi:9001")
    val fs: FileSystem = FileSystem.get(conf)
    print(fs.getCanonicalServiceName)
  }
}
