package com.galaxy.bigdata.jmx

class Hello extends HelloMBean {

  override def printHello: Unit = println("Hello world, " + name)

  override def printHello(whoName: String): Unit = println("Hello, " + whoName)
}
