package com.galaxy.bigdata.jmx.notification

import javax.management.{Notification, NotificationBroadcasterSupport}

class XiaoSi extends NotificationBroadcasterSupport with XiaoSiMBean {

  var seq: Int = 0

  /**
    * 必须继承NotificationBroadcasterSupport，此类只有一个hi方法，方法只有两句：创建一个Notification消息包，然后将包发出去
    * 如果你还要在消息包上附加其他数据，Notification还有一个setUserData方法可供使用
    */
  override def hi: Unit = {
    seq += 1
    val notification = new Notification(
      "xiaosi.hi", //给这个Notification起个名称
      this, //由谁发出的Notification
      seq, //一系列通知中的序列号，可以设置任意数值
      System.currentTimeMillis(), //发出时间
      "Xiaosi" //发出信息的消息文本
    )
    sendNotification(notification)
  }
}
