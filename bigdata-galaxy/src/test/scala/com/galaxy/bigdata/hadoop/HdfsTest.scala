package com.galaxy.bigdata.hadoop

import java.text.SimpleDateFormat
import java.util.Date

import org.apache.hadoop.conf.Configuration
import org.apache.hadoop.fs.{FileSystem, Path}
import org.junit.Test


/**
  * Created by wangpeng
  * Date: 2019-02-01
  * Time: 20:56
  */
class HdfsTest {

  val DEFAULT_FS_URI = "hdfs://namenodetest02.bi.10101111.com:9001"

  val hadoop: FileSystem = {
    System.setProperty("HADOOP_USER_NAME", "hadoop")
    val conf = new Configuration()
    conf.set("fs.defaultFS", this.DEFAULT_FS_URI)
    FileSystem.get(conf)
  }

  /**
    * 测试连接获取文件列表
    */
  @Test
  def testListFiles: Unit = {
    val path = new Path("/user/")
    val files = this.hadoop.listFiles(path, false)
    while (files.hasNext) {
      val file = files.next()
      val pathStr = file.getPath().toString()
      println(pathStr)
    }
  }

  /**
    * 测试获取文件状态
    */
  @Test
  def testListStatus: Unit = {
    val dateFormat = new SimpleDateFormat("yyyy-MM-dd")

    val status = this.hadoop.listStatus(new Path("/user"))
    status.foreach(x => {
      val modificationTime = x.getModificationTime
      val modificationDate = new Date(modificationTime)
      val time = dateFormat.format(modificationDate)
      if (time.equals("2018-09-03")) {
      }
    })
    this.hadoop.delete(new Path("/user/zhupeng^M"))
  }

  @Test
  def testYARNTimeStamp: Unit ={
    val timestamp = 1554105793499l
    val date = new Date(timestamp)
    val dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
    val time = dateFormat.format(date)
    println(time)
  }
}
