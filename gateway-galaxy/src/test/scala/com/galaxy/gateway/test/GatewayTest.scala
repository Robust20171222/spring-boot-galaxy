package com.galaxy.gateway.test

import com.netflix.loadbalancer.{AbstractLoadBalancer, ServerListUpdater}
import org.junit.Test
import org.springframework.cloud.client.loadbalancer.{LoadBalancerClient, LoadBalancerInterceptor}
import org.springframework.cloud.netflix.zuul.web.ZuulHandlerMapping
import org.springframework.web.servlet.handler.AbstractUrlHandlerMapping

/**
  * Created by wangpeng
  * Date: 2018/8/23
  * Time: 11:07
  */
class GatewayTest {

  @Test
  def test: Unit = {
    val zuulHandlerMapping: ZuulHandlerMapping = null
    // org.springframework.web.servlet.handler.AbstractUrlHandlerMapping.registerHandler(AbstractUrlHandlerMapping.java:362) - Mapped URL path [/hotel/**] onto handler of type [class org.springframework.cloud.netflix.zuul.web.ZuulController]
    val abstractUrlHandlerMapping: AbstractUrlHandlerMapping = null

    val loadBalancerClient: LoadBalancerClient = null

    val loadBalancerInterceptor: LoadBalancerInterceptor = null

    val abstractLoadBalancer: AbstractLoadBalancer = null

    val serverListUpdater: ServerListUpdater = null
  }
}
