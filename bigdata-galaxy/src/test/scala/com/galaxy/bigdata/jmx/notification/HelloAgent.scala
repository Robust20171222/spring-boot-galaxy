package com.galaxy.bigdata.jmx.notification

import com.galaxy.bigdata.jmx.Hello

object HelloAgent {

  def main(args: Array[String]): Unit = {
    import java.lang.management.ManagementFactory
    val server = ManagementFactory.getPlatformMBeanServer

    import javax.management.ObjectName
    val helloName = new ObjectName("MyMBean:name=HelloWorld")
    val hello = new Hello
    server.registerMBean(hello, helloName)

    import javax.management.ObjectName
    val adapterName = new ObjectName("MyBean:name=htmladapter,port=8082")
    import com.sun.jdmk.comm.HtmlAdaptorServer
    val adapter = new HtmlAdaptorServer
    server.registerMBean(adapter, adapterName)

    val xs = new XiaoSi
    import javax.management.ObjectName
    server.registerMBean(xs, new ObjectName("MyMBean:name=xiaosi"))
    import com.test.jmx.notification.HelloListener
    xs.addNotificationListener(new HelloListener, null, hello)
    adapter.start
  }
}
