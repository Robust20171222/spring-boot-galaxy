package com.galaxy.scala

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.scheduling.annotation.EnableAsync

/**
  * Created by wangpeng on 16/03/2018. 
  */
@EnableAsync
@SpringBootApplication
class ScalaGalaxyApplication

object ScalaGalaxyApplication {
  def main(args: Array[String]): Unit = {
    SpringApplication.run(classOf[ScalaGalaxyApplication], args: _*)
  }
}
