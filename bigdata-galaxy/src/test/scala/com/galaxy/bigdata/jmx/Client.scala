package com.galaxy.bigdata.jmx

import javax.management.remote.{JMXConnectorFactory, JMXServiceURL}
import javax.management.{Attribute, ObjectName}

import scala.collection.JavaConversions._

object Client {

  def main(args: Array[String]): Unit = {
    val domainName = "MyMBean"
    val rmiPort = 1099

    val url = new JMXServiceURL(s"service:jmx:rmi:///jndi/rmi:localhost:$rmiPort/$domainName")

    val jmxc = JMXConnectorFactory.connect(url)
    val mBeanServerConnection = jmxc.getMBeanServerConnection

    //print domains
    println("Domains:------------------")
    val domains = mBeanServerConnection.getDomains

    for (i <- 0 until domains.length) {
      println("\tDomain[" + i + "] = " + domains(i))
    }

    println("MBean count = " + mBeanServerConnection.getMBeanCount())

    val mBeanName = new ObjectName(domainName + ":name=HelloWorld")
    mBeanServerConnection.setAttribute(mBeanName, new Attribute("Name", "zzh")); //注意这里是Name而不是name
    println("Name = " + mBeanServerConnection.getAttribute(mBeanName, "Name"))

    //接下去是执行Hello中的printHello方法，分别通过代理和rmi的方式执行
    //via proxy
    import javax.management.MBeanServerInvocationHandler
    val proxy = MBeanServerInvocationHandler.newProxyInstance(mBeanServerConnection, mBeanName, classOf[HelloMBean], false)
    proxy.printHello
    proxy.printHello("jizhi boy")

    //via rmi
    mBeanServerConnection.invoke(mBeanName, "printHello", null, null)
    mBeanServerConnection.invoke(mBeanName, "printHello", Array("jizhi gril"), Array(classOf[String].getName))

    //get mbean information
    val info = mBeanServerConnection.getMBeanInfo(mBeanName)
    println("Hello Class: " + info.getClassName())

    info.getAttributes.foreach(attribute => println(s"Hello Attribute：${attribute.getName}"))
    info.getOperations.foreach(operation => println(s"Hello Operation：${operation.getName}"))

    //ObjectName of MBean
    println("all ObjectName:--------------")
    val mBeanSet = mBeanServerConnection.queryMBeans(null, null)
    mBeanSet.foreach(mBean => println(s"\t ${mBean.getObjectName}"))

    jmxc.close()
  }
}
