package com.galaxy.docker

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

/**
  * Created by wangpeng
  * Date: 2018/5/31
  * Time: 22:15
  */
@SpringBootApplication
class DockerApplication {
}

object DockerApplication {
  def main(args: Array[String]): Unit = {
    SpringApplication.run(classOf[DockerApplication], args: _*)
  }
}