package com.galaxy.bigdata.jmx.modeler

import com.galaxy.bigdata.jmx.Hello
import com.sun.jdmk.comm.HtmlAdaptorServer
import javax.management.ObjectName
import org.apache.commons.modeler.Registry

/**
  * Apache Commons Modeler 实现JMX测试
  *
  * @author pengwang
  * @date 2019/05/11
  * @link https://blog.csdn.net/u013256816/article/details/52840067
  */
object HelloAgent {

  def main(args: Array[String]): Unit = {
    // 需要将xml信息读入到Registry对象中
    val registry = Registry.getRegistry(null, null)
    val stream = this.getClass.getResourceAsStream("mbeans-descriptors.xml")
    registry.loadMetadata(stream)
    val server = registry.getMBeanServer

    // 之前是：MBeanServer server = ManagementFactory.getPlatformMBeanServer();
    val managed = registry.findManagedBean("Hello")
    val helloName = new ObjectName(managed.getDomain() + ":name=HelloWorld")

    // 以前是Hello hello = new Hello(); 为什么要多个createMBean？因为现在的写法没有写MBean,所以才要动态生成一个，以前就直接
    // 把new hello()注册到MBeanServer就可以了，server会自动找到它的HelloMBean接口文件。
    val hello = managed.createMBean(new Hello)
    server.registerMBean(hello, helloName)

    val adapterName = new ObjectName(managed.getDomain()+":name = htmladapter,port=8082")
    val adapter = new HtmlAdaptorServer()
    server.registerMBean(adapter, adapterName)
    adapter.start()
  }
}