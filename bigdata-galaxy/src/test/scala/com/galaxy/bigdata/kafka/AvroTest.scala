package com.galaxy.bigdata.kafka

import java.util.Properties

import org.apache.avro.Schema
import org.apache.avro.generic.GenericData
import org.apache.kafka.clients.producer.{KafkaProducer, ProducerConfig, ProducerRecord}
import org.junit.Test

/**
  * Created by wangpeng
  * Date: 2019-02-12
  * Time: 20:53
  */
class AvroTest {

  /**
    * 测试kafka使用avro方式生产数据
    * 参考 https://docs.confluent.io/current/schema-registry/docs/serializer-formatter.html
    */
  @Test
  def testProducer: Unit = {
    // 设置kafka broker地址、序列化方式、schema-registry组件的地址
    val props = new Properties()
    props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092")
    props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, classOf[io.confluent.kafka.serializers.KafkaAvroSerializer])
    props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, classOf[io.confluent.kafka.serializers.KafkaAvroSerializer])
    props.put("citySchema.registry.url", "http://localhost:8081")

    // 设置schema
    val citySchema = "{\"type\":\"record\",\"name\":\"myrecord\",\"fields\":[{\"name\":\"city\",\"type\":\"string\"},{\"name\":\"state\",\"type\":\"string\"},{\"name\":\"population\",\"type\":\"int\"},{\"name\":\"country\",\"type\":\"string\"}]}"
    val parser = new Schema.Parser()
    val schema = parser.parse(citySchema)

    // 构造测试数据
    val avroRecord1 = new GenericData.Record(schema)
    avroRecord1.put("city", "Philadelphia")
    avroRecord1.put("state", "PA")
    avroRecord1.put("population", 1568000)
    avroRecord1.put("country", "USA")

    val avroRecord2 = new GenericData.Record(schema)
    avroRecord2.put("city", "Chicago")
    avroRecord2.put("state", "IL")
    avroRecord2.put("population", 2705000)
    avroRecord2.put("country", "USA")

    val avroRecord3 = new GenericData.Record(schema)
    avroRecord3.put("city", "New York")
    avroRecord3.put("state", "NY")
    avroRecord3.put("population", 8538000)
    avroRecord3.put("country", "USA")

    // 生产数据
    val producer = new KafkaProducer[String, GenericData.Record](props)
    try {
      val recordList = List(avroRecord1, avroRecord2, avroRecord3)
      val key = "key1"

      for (elem <- recordList) {
        val record = new ProducerRecord("hive_sink_orc", key, elem)
        for (i <- 0 to 100) {
          val ack = producer.send(record).get()
          println(s"${ack.toString} written to partition ${ack.partition.toString}")
        }
      }
    } catch {
      case e: Throwable => e.printStackTrace()
    } finally {
      // When you're finished producing records, you can flush the producer to ensure it has all been written to Kafka and
      // then close the producer to free its resources.
      // 调用flush方法确保所有数据都被写入到Kafka
      producer.flush()
      // 调用close方法释放资源
      producer.close()
    }
  }
}
