package com.galaxy.bigdata.hive

import java.io.File

import org.apache.crunch.types.orc.OrcUtils
import org.apache.hadoop.conf.Configuration
import org.apache.hadoop.fs.Path
import org.apache.hadoop.hive.ql.exec.vector.{BytesColumnVector, LongColumnVector, MapColumnVector}
import org.apache.hadoop.hive.ql.io.orc.{OrcFile, OrcStruct}
import org.apache.hadoop.hive.serde2.io.{DoubleWritable, ShortWritable}
import org.apache.hadoop.hive.serde2.typeinfo.TypeInfoUtils
import org.apache.hadoop.io.{FloatWritable, IntWritable, LongWritable, Text}
import org.apache.orc.TypeDescription
import org.junit.Assert._
import org.junit.Test

/**
  * ORC 读写测试
  *
  * Created by wangpeng
  * Date: 2019-02-14
  * Time: 10:52
  */
class OrcTest {

  @Test
  def testOrcWrite: Unit = {
    val typeStr = "struct<string_value:string,short_value:smallint,integer_value:int,long_value:bigint,double_value:double,float_value:float>"
    val typeInfo = TypeInfoUtils.getTypeInfoFromTypeString(typeStr)
    val inspector = OrcStruct.createObjectInspector(typeInfo)

    val input = "my_text_string\t1\t2\t3\t123.4\t123.45"
    val inputTokens = input.split("\\t")

    val orcLine = OrcUtils.createOrcStruct(
      typeInfo,
      new Text(inputTokens(0)),
      new ShortWritable(inputTokens(1).toShort),
      new IntWritable(Integer.valueOf(inputTokens(2))),
      new LongWritable(inputTokens(3).toLong),
      new DoubleWritable(inputTokens(4).toDouble),
      new FloatWritable(inputTokens(5).toFloat))

    val conf = new Configuration()
    val tempPath = new Path("src/test/resources/test.orc")

    val writer = OrcFile.createWriter(tempPath, OrcFile.writerOptions(conf).inspector(inspector).stripeSize(100000).bufferSize(10000))
    writer.addRow(orcLine)
    writer.close()
  }

  /**
    * @example https://orc.apache.org/docs/core-java.html#simple-example
    */
  @Test
  def testSimpleWrite: Unit = {
    val conf = new Configuration()
    val schema = TypeDescription.createStruct()
    schema.addField("x", TypeDescription.createInt()).addField("y", TypeDescription.createInt())

    val path = "src/test/resources/my-file.orc"
    val file = new File(path)
    if (file.exists()) {
      file.delete()
    }

    val writer = OrcFile.createWriter(new Path(path), OrcFile.writerOptions(conf)
      .setSchema(schema))

    val batch = schema.createRowBatch()
    val x = batch.cols(0).asInstanceOf[LongColumnVector]
    val y = batch.cols(1).asInstanceOf[LongColumnVector]

    for (r <- 0 until 10000) {
      batch.size += 1
      val row = batch.size - 1
      x.vector(row) = r
      y.vector(row) = r * 3
      // If the batch is full, write it out and start over.
      if (batch.size == batch.getMaxSize) {
        writer.addRowBatch(batch)
        batch.reset()
      }
    }

    if (batch.size != 0) {
      writer.addRowBatch(batch)
      batch.reset()
    }

    writer.close()
  }

  /**
    * @example https://orc.apache.org/docs/core-java.html#advanced-example
    */
  @Test
  def testAdvancedExample: Unit = {
    val path = "src/test/resources/advanced-example.orc"

    val file = new File(path)
    if (file.exists()) {
      file.delete()
    }

    val testFilePath = new Path(path)
    val conf = new Configuration()

    val schema = TypeDescription.createStruct().addField("first", TypeDescription.createInt()).addField("second", TypeDescription.createInt()).addField("third", TypeDescription.createMap(TypeDescription.createString(), TypeDescription.createInt()))

    val writer = OrcFile.createWriter(testFilePath, OrcFile.writerOptions(conf).setSchema(schema))

    val batch = schema.createRowBatch()
    val first = batch.cols(0).asInstanceOf[LongColumnVector]
    val second = batch.cols(1).asInstanceOf[LongColumnVector]

    //Define map. You need also to cast the key and value vectors
    val map = batch.cols(2).asInstanceOf[MapColumnVector]
    val mapKey = map.keys.asInstanceOf[BytesColumnVector]
    val mapValue = map.values.asInstanceOf[LongColumnVector]

    // Each map has 5 elements
    val MAP_SIZE = 5
    val BATCH_SIZE = batch.getMaxSize

    // Ensure the map is big enough
    mapKey.ensureSize(BATCH_SIZE * MAP_SIZE, false)
    mapValue.ensureSize(BATCH_SIZE * MAP_SIZE, false)

    // add 1500 rows to file
    for (r <- 0 until 1500) {
      batch.size += 1
      val row = batch.size - 1
      val temp = batch.size

      first.vector(row) = r
      second.vector(row) = r * 3

      map.offsets(row) = map.childCount
      map.lengths(row) = MAP_SIZE
      map.childCount += MAP_SIZE

      for (mapElem <- map.offsets(row).toInt until (map.offsets(row) + MAP_SIZE).toInt) {
        val key = "row " + r + "." + (mapElem - map.offsets(row))
        import java.nio.charset.StandardCharsets
        mapKey.setVal(mapElem, key.getBytes(StandardCharsets.UTF_8))
        mapValue.vector(mapElem) = mapElem
      }

      if (temp == BATCH_SIZE - 1) {
        writer.addRowBatch(batch)
        batch.reset
      }
    }

    if (batch.size != 0) {
      writer.addRowBatch(batch)
      batch.reset
    }

    writer.close
  }

  /**
    * @example https://orc.apache.org/docs/core-java.html#reading-orc-files
    */
  @Test
  def testReaderOrc: Unit = {
    val path = "src/test/resources/my-file.orc"
    val reader = OrcFile.createReader(new Path(path),
      OrcFile.readerOptions(new Configuration()))

    val rows = reader.rows()
    val batch = reader.getSchema().createRowBatch()

    while (rows.nextBatch(batch)) {
      val x = batch.cols(0).asInstanceOf[LongColumnVector]
      val y = batch.cols(1).asInstanceOf[LongColumnVector]

      for (r <- 0 until batch.size) {
        val v1 = x.vector(r)
        val v2 = y.vector(r)

        println(v1 + "----" + v2)
      }
    }
    rows.close()
  }

  /**
    * @example https://github.com/apache/orc/blob/master/java/core/src/test/org/apache/orc/TestTypeDescription.java
    */
  @Test
  def testJson(): Unit = {
    val bin = TypeDescription.createBinary
    assertEquals("{\"category\": \"binary\", \"id\": 0, \"max\": 0}", bin.toJson)
    assertEquals("binary", bin.toString)
    var struct = TypeDescription.createStruct.addField("f1", TypeDescription.createInt).addField("f2", TypeDescription.createString).addField("f3", TypeDescription.createDecimal)
    assertEquals("struct<f1:int,f2:string,f3:decimal(38,10)>", struct.toString)
    assertEquals("{\"category\": \"struct\", \"id\": 0, \"max\": 3, \"fields\": [\n" + "  \"f1\": {\"category\": \"int\", \"id\": 1, \"max\": 1},\n" + "  \"f2\": {\"category\": \"string\", \"id\": 2, \"max\": 2},\n" + "  \"f3\": {\"category\": \"decimal\", \"id\": 3, \"max\": 3, \"precision\": 38, \"scale\": 10}]}", struct.toJson)
    struct = TypeDescription.createStruct.addField("f1", TypeDescription.createUnion.addUnionChild(TypeDescription.createByte).addUnionChild(TypeDescription.createDecimal.withPrecision(20).withScale(10))).addField("f2", TypeDescription.createStruct.addField("f3", TypeDescription.createDate).addField("f4", TypeDescription.createDouble).addField("f5", TypeDescription.createBoolean)).addField("f6", TypeDescription.createChar.withMaxLength(100))
    assertEquals("struct<f1:uniontype<tinyint,decimal(20,10)>,f2:struct<f3:date,f4:double,f5:boolean>,f6:char(100)>", struct.toString)
    assertEquals("{\"category\": \"struct\", \"id\": 0, \"max\": 8, \"fields\": [\n" + "  \"f1\": {\"category\": \"uniontype\", \"id\": 1, \"max\": 3, \"children\": [\n" + "    {\"category\": \"tinyint\", \"id\": 2, \"max\": 2},\n" + "    {\"category\": \"decimal\", \"id\": 3, \"max\": 3, \"precision\": 20, \"scale\": 10}]},\n" + "  \"f2\": {\"category\": \"struct\", \"id\": 4, \"max\": 7, \"fields\": [\n" + "    \"f3\": {\"category\": \"date\", \"id\": 5, \"max\": 5},\n" + "    \"f4\": {\"category\": \"double\", \"id\": 6, \"max\": 6},\n" + "    \"f5\": {\"category\": \"boolean\", \"id\": 7, \"max\": 7}]},\n" + "  \"f6\": {\"category\": \"char\", \"id\": 8, \"max\": 8, \"length\": 100}]}", struct.toJson)
  }
}
