package com.galaxy.bigdata.jmx

import scala.beans.BeanProperty

trait HelloMBean {

  @BeanProperty
  var name: String = _

  def printHello

  def printHello(whoName: String)
}
