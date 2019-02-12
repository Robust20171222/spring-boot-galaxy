package com.test.hive

import org.apache.hadoop.conf.Configuration
import org.apache.hadoop.fs.Path
import org.apache.hadoop.hive.conf.HiveConf
import org.apache.hadoop.hive.metastore.HiveMetaStoreClient
import org.apache.orc.{CompressionKind, OrcFile, TypeDescription}
import org.junit.Test

/**
  * Created by wangpeng
  * Date: 2019-02-01
  * Time: 17:02
  */
class HiveTest {

  @Test
  def test: Unit = {
    for (i <- 0 until 10) {
      println(i)
    }

    println("测试for循环")

    for (i <- 0 to 10) {
      println(i)
    }
  }

  @Test
  def testMetaStore: Unit = {
    val hiveConf = new HiveConf()
    hiveConf.set("hive.metastore", "thrift")
    hiveConf.set("hive.metastore.uris", "thrift://namenodetest01.bi:9083")
    var client = new HiveMetaStoreClient(hiveConf)
  }

  def testOrcWrite: Unit = {
    val conf = new Configuration()
    val schema = TypeDescription.createStruct()

    // 设置写ORC文件时的配置
    val options = OrcFile.writerOptions(null, conf).setSchema(schema)
    options.compress(CompressionKind.SNAPPY)
    options.encodingStrategy(OrcFile.EncodingStrategy.COMPRESSION)
    options.blockPadding(false)
    options.version(OrcFile.Version.V_0_12)

    // 创建Writer对象
    val writer = OrcFile.createWriter(new Path(""), options)

    val batch = schema.createRowBatch

    import org.apache.hadoop.hive.ql.exec.vector.LongColumnVector
    val x = batch.cols(0).asInstanceOf[LongColumnVector]
    val y = batch.cols(1).asInstanceOf[LongColumnVector]

    for (i <- 0 until 10000) {
      val row = (batch.size += 1).asInstanceOf[Int]
      x.vector(row) = i
      y.vector(row) = i * 3

      // If the batch is full, write it out and start over.// If the batch is full, write it out and start over.

      if (batch.size == batch.getMaxSize) {
        writer.addRowBatch(batch)
        batch.reset
      }
    }

    if (batch.size != 0) {
      writer.addRowBatch(batch)
      batch.reset
    }

    writer.close()
  }
}
