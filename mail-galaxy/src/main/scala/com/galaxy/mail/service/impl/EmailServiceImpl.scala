package com.galaxy.mail.service.impl

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Configuration
import org.springframework.mail.SimpleMailMessage
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.stereotype.Service

@Configuration
@Service
class EmailServiceImpl {

  @Autowired private var javaMailSender:JavaMailSender = _

  def sendSimpleMessage(to:String,subject:String,text:String): Unit ={
    val message = new SimpleMailMessage
    message.setTo(to)
    message.setSubject(subject)
    message.setText(text)

    try {
      this.javaMailSender.send(message)
    } catch {
      case e:Exception => e.printStackTrace()
    }
  }
}
