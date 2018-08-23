package com.galaxy.gateway

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.cloud.client.discovery.EnableDiscoveryClient
import org.springframework.cloud.netflix.zuul.EnableZuulProxy

/**
  * Created by wangpeng
  * Date: 2018/8/7
  * Time: 17:52
  */
@SpringBootApplication
@EnableZuulProxy
@EnableDiscoveryClient
class GatewayServiceZuulApplication {

}

object GatewayServiceZuulApplication {
  def main(args: Array[String]): Unit = {
    SpringApplication.run(classOf[GatewayServiceZuulApplication])
  }
}
