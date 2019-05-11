package com.test.jmx.notification

import com.galaxy.bigdata.jmx.Hello
import javax.management.Notification
import javax.management.NotificationListener


class HelloListener extends NotificationListener {

  override def handleNotification(notification: Notification, handback: Any): Unit = {
    System.out.println("----------HelloListener-Begin------------")
    System.out.println("\ttype = " + notification.getType)
    System.out.println("\tsource = " + notification.getSource)
    System.out.println("\tseq = " + notification.getSequenceNumber)
    System.out.println("\tsend time = " + notification.getTimeStamp)
    System.out.println("\tmessage=" + notification.getMessage)
    System.out.println("----------HelloListener-End------------")

    if (handback != null)
      if (handback.isInstanceOf[Hello]) {
        val hello = handback.asInstanceOf[Hello]
        hello.printHello(notification.getMessage)
      }
  }
}
