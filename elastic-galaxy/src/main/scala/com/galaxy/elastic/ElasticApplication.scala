package com.galaxy.elastic

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication
class ElasticApplication {

}


object ElasticApplication{
  def main(args: Array[String]): Unit = {
    SpringApplication.run(classOf[ElasticApplication])
  }
}
