package com.galaxy.mail

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication
class MailApplication

object MailApplication {

  def main(args: Array[String]): Unit = {
    SpringApplication.run(classOf[MailApplication])
  }
}