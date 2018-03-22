package com.galaxy.scala.config

import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.{Configuration, PropertySource}

/**
  * Created by wangpeng on 19/03/2018. 
  */

@Configuration
@PropertySource(value = Array("classpath:xxl.properties"))
class XxlConfig {

  @Value("${xxl.job.admin.addresses}")
  private var addresses: String = _

  @Value("${xxl.job.executor.appname}")
  private var appname: String = _

  @Value("${xxl.job.executor.host}")
  private var host: String = _

  @Value("${xxl.job.executor.logpath}")
  private var logpath: String = _

  @Value("${xxl.job.accessToken}")
  private var accessToken: String = _

  @Value("${xxl.job.executor.port}")
  private var port: Integer = _
}
