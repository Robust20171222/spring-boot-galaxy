package com.galaxy.mail.controller

import com.galaxy.mail.service.impl.EmailServiceImpl
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.{GetMapping, RequestMapping, RestController}

@RequestMapping(value = Array("/mail"))
@RestController
class MailController {

  @Autowired
  private var emailServiceImpl:EmailServiceImpl = _

  @GetMapping(value = Array("/send"))
  def send: Unit ={
    val to = "wangpeng@aone.ai"
    val subject = "测试邮件"
    val text = "测试一下邮件是否正常"

    this.emailServiceImpl.sendSimpleMessage(to,subject,text)
  }
}
