package com.galaxy.scala.config

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.scala.DefaultScalaModule
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.context.annotation.{Bean, ComponentScan, Configuration, Primary}

/**
  * Created by wangpeng
  * Date: 2019-03-01
  * Time: 11:18
  */
@Configuration
@EnableAutoConfiguration
@ComponentScan
class JsonConfig {

  @Bean
  @Primary
  def objectMapper(): ObjectMapper = {
    val objectMapper = new ObjectMapper
    objectMapper.registerModule(DefaultScalaModule)
    objectMapper
  }
}
