package com.galaxy.bigdata

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

/**
  * Created by wangpeng
  * Date: 2018-11-26
  * Time: 17:07
  */
@SpringBootApplication
class BigDataApplication {

}

object BigDataApplication {
  def main(args: Array[String]): Unit = {
    SpringApplication.run(classOf[BigDataApplication])
  }
}
