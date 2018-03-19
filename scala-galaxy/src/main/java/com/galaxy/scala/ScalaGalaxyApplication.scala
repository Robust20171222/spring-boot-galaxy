package com.galaxy.scala

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.ComponentScan

/**
  * Created by wangpeng on 16/03/2018. 
  */
@ComponentScan(basePackages = Array("com.galaxy.scala.*"))
@SpringBootApplication
class ScalaGalaxyApplication

object ScalaGalaxyApplication {

  def main(args: Array[String]): Unit = {
    SpringApplication.run(classOf[ScalaGalaxyApplication])
  }
}
