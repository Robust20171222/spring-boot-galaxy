package com.galaxy.bigdata.hbase.mapper

import com.galaxy.bigdata.hbase.model.Order
import org.apache.ibatis.annotations.Param

trait OrderMapper {

  def getOrders: java.util.List[Order]

  def updateOrder(@Param("id") id: Long, @Param("totalAmount") totalAmount: java.math.BigDecimal)
}
