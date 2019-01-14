package com.galaxy.bigdata.hdfs

import java.net.URI

import com.kumkee.userAgent.UserAgentParser
import org.apache.hadoop.conf.Configuration
import org.apache.hadoop.fs.{FileSystem, Path}
import org.apache.hadoop.io.IOUtils
import org.junit.{After, Before, Test}

/**
  * Hadoop api test
  *
  * Created by wangpeng
  * Date: 2019-01-14
  * Time: 14:14
  */
class HDFSApp {

  val HDFS_PATH = "hdfs://hadoop22-test1-rgtj5-tj1:8020"
  var fileSystem: FileSystem = _
  var configuration: Configuration = _

  @Before
  def setUp: Unit = {
    println("HDFSApp.setUp")
    this.configuration = new Configuration()
    this.fileSystem = FileSystem.get(new URI(HDFS_PATH), configuration, "root")
  }

  /**
    * 创建目录
    */
  @Test
  def mkdir: Unit = {
    fileSystem.mkdirs(new Path("/hdfsapi/test"))
  }

  /**
    * 写入文件
    */
  @Test
  def create: Unit = {
    val out = fileSystem.create(new Path("/hdfsapi/test/a.txt"))
    out.write("This is test".getBytes)
    out.flush()
    out.close()
  }

  /**
    * 查看文件内容
    */
  @Test
  def cat: Unit = {
    val in = fileSystem.open(new Path("/hdfsapi/test/a.txt"))
    IOUtils.copyBytes(in, System.out, 1024)
  }

  @Test
  def userAgentTest: Unit ={
    val source = "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.71 Safari/537.36"
    val userAgentParser = new UserAgentParser
    val userAgent = userAgentParser.parse(source)
    println(userAgent.getBrowser)
  }

  @After
  def tearDown: Unit = {
    this.configuration = null
    this.fileSystem = null
    println("HDFSApp.tearDown")
  }

}
