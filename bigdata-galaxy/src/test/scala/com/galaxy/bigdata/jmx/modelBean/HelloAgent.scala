package com.galaxy.bigdata.jmx.modelBean

import java.lang.management.ManagementFactory

import com.sun.jdmk.comm.HtmlAdaptorServer
import javax.management.ObjectName

/**
  * @link https://blog.csdn.net/u013256816/article/details/52817247
  */
object HelloAgent {

  def main(args: Array[String]): Unit = {
    val server = ManagementFactory.getPlatformMBeanServer();
    val helloName = new ObjectName("MyMBean:name=HelloWorld")
    //Hello hello = new Hello();
    val hello = ModelMBeanUtils.createModelerMBean
    server.registerMBean(hello, helloName)

    val adapterName = new ObjectName("MyMBean:name=htmladapter,port=8082")
    val adapter = new HtmlAdaptorServer()
    server.registerMBean(adapter, adapterName)

    adapter.start()
  }

}
