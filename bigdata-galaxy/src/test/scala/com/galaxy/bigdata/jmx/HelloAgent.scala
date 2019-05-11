package com.galaxy.bigdata.jmx

import java.lang.management.ManagementFactory
import java.rmi.registry.LocateRegistry

import com.sun.jdmk.comm.HtmlAdaptorServer
import javax.management.remote.{JMXConnectorServerFactory, JMXServiceURL}
import javax.management.{MBeanServerFactory, ObjectName}

object HelloAgent {

  def main(args: Array[String]): Unit = {
    // 下面这种方式不能在JConsole中使用
    // val server = MBeanServerFactory.createMBeanServer()

    // 首先建立一个MBeanServer，MBeanServer用来管理我们的MBean，通常是通过MBeanServer来获取我们MBean的信息，间接
    // 调用MBean的方法，然后生产我们的资源的一个对象。
    val mBeanServer = ManagementFactory.getPlatformMBeanServer()

    val domainName = "MyMBean"

    // 为MBean（下面的new Hello()）创建ObjectName实例
    val helloName = new ObjectName(domainName + ":name=HelloWorld")
    // 将new Hello()这个对象注册到MBeanServer上去
    mBeanServer.registerMBean(new Hello(), helloName)

    // Distributed Layer, 提供了一个HtmlAdaptor。支持Http访问协议，并且有一个不错的HTML界面，这里的Hello就是用这个作为远端管理的界面
    // 事实上HtmlAdaptor是一个简单的HttpServer，它将Http请求转换为JMX Agent的请求
    val adapterName = new ObjectName(domainName + ":name=htmladapter,port=8082")
    val adapter = new HtmlAdaptorServer()
    adapter.start()
    mBeanServer.registerMBean(adapter, adapterName)

    val rmiPort = 1099
    val registry = LocateRegistry.createRegistry(rmiPort)

    val url = new JMXServiceURL(s"service:jmx:rmi:///jndi/rmi:localhost:$rmiPort/$domainName")
    val jmxConnector = JMXConnectorServerFactory.newJMXConnectorServer(url, null, mBeanServer)
    jmxConnector.start()
  }
}
