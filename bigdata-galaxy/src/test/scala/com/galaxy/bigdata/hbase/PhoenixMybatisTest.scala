package com.galaxy.bigdata.hbase

import com.galaxy.bigdata.hbase.mapper.OrderMapper
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner

import scala.collection.JavaConversions._

@RunWith(classOf[SpringRunner])
@SpringBootTest
class PhoenixMybatisTest {

  @Autowired
  private var orderMapper: OrderMapper = _

  @Test
  def testGetOrders: Unit = {
    val orders = orderMapper.getOrders
    orders.foreach(order => println(order))
  }

  @Test
  def testUpdateOrder: Unit = {
    orderMapper.updateOrder(2L, java.math.BigDecimal.valueOf(88.8))
  }
}
