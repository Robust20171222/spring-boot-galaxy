package com.galaxy.bigdata.hbase.model

import java.util.Date

import scala.beans.BeanProperty

class Order {

  @BeanProperty
  var id: Long = _

  @BeanProperty
  var orderCode: String = _

  @BeanProperty
  var totalAmount: java.math.BigDecimal = _

  @BeanProperty
  var createTime: Date = _

  @BeanProperty
  var userId: Long = _

  override def toString = s"Order(id=$id, orderCode=$orderCode, totalAmount=$totalAmount, createTime=$createTime, userId=$userId)"
}
