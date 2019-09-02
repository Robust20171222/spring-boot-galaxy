package com.galaxy.bigdata.hadoop.hdfs

import java.text.SimpleDateFormat
import java.util.Date

import com.amazonaws.util.AwsHostNameUtils
import org.apache.hadoop.conf.Configuration
import org.apache.hadoop.fs.permission._
import org.apache.hadoop.fs.{FileSystem, Path}
import org.apache.hadoop.net.DNS
import org.junit.{After, Test}

import scala.collection.JavaConversions._
import scala.collection.mutable.ListBuffer

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
    * 测试获取目录下的文件状态
    */
  @Test
  def testListStatus: Unit = {
    val dateFormat = new SimpleDateFormat("yyyy-MM-dd")

    val status = this.hadoop.listStatus(new Path("/user"))
    status.foreach(x => {

      if (x.isDirectory){
        val modificationTime = x.getModificationTime
        val accessTime = x.getAccessTime
        println(x.getPath + "--" + accessTime)
        val modificationDate = new Date(modificationTime)
        val time = dateFormat.format(modificationDate)
        if (time.equals("2018-09-03")) {
        }
      }

    })
    //this.hadoop.delete(new Path("/user/zhupeng^M"))
  }

  /**
    * 测试获取文件状态
    */
  @Test
  def testGetFileStatus: Unit ={
    val dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:dd")
    val fileStatus = this.hadoop.getFileStatus(new Path("/user"))
    println(dateFormat.format(new Date(fileStatus.getAccessTime)))
  }

  /**
    * 测试YARN application时间戳
    */
  @Test
  def testYARNTimeStamp: Unit = {
    val timestamp = 1554105793499l
    val date = new Date(timestamp)
    val dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
    val time = dateFormat.format(date)
    println(time)
  }

  /**
    * 测试删除文件
    */
  @Test
  def testDelete: Unit = {
    val path = new Path("/test")
    println(this.hadoop.delete(path, true))
  }

  /**
    * 测试ACL
    */
  @Test
  def testGetACL: Unit = {
    val path = new Path("/user/hive/warehouse/kudu_test.db/my_first_table3")
    val aclStatus = this.hadoop.getAclStatus(path)
    println(aclStatus)
  }

  /**
    * 测试更改权限
    */
  @Test
  def testModifyAcl: Unit = {
    val aclEntryScope = AclEntryScope.ACCESS
    val builder = new AclEntry.Builder()
    builder.setType(AclEntryType.USER)
    builder.setName("impala")
    builder.setPermission(FsAction.NONE)
    builder.setScope(aclEntryScope)
    val aclEntry = builder.build()

    val aclEntries = new ListBuffer[AclEntry]
    aclEntries += aclEntry

    val path = new Path("/user/hive/warehouse")
    this.hadoop.modifyAclEntries(path, aclEntries)
  }

  /**
    * 测试FsPermission，创建目录并设置权限
    */
  @Test
  def testFsPermission: Unit = {
    val path = new Path("/test")
    if (this.hadoop.exists(path)) {
      println("There is already exist " + path)
    } else {
      val filePermission = new FsPermission(
        FsAction.ALL, //user action
        FsAction.ALL, //group action
        FsAction.NONE); //other action

      // 创建目录，不设置权限，默认为当前HDFS服务器启动用户
      //  val success = this.hadoop.mkdirs(path)
      val success = this.hadoop.mkdirs(path, filePermission)
      println(s"$path is success $success")
    }
  }

  /**
    * 关闭FileSystem，释放资源
    */
  @After
  def close: Unit = {
    if (this.hadoop != null) {
      this.hadoop.close()
    }
  }

  @Test
  def testIP: Unit ={
    println(DNS.getDefaultIP("en0"))
  }
}
