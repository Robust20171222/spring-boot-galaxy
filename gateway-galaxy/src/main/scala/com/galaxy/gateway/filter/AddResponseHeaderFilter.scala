package com.galaxy.gateway.filter

import java.util.UUID

import com.netflix.zuul.ZuulFilter
import com.netflix.zuul.context.RequestContext
import org.slf4j.LoggerFactory
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants

/**
  * Created by wangpeng
  * Date: 2018/9/12
  * Time: 22:43
  */
class AddResponseHeaderFilter extends ZuulFilter {

  private val logger = LoggerFactory.getLogger(classOf[AddResponseHeaderFilter])

  /**
    * 定义filter的类型，有pre、route、post、error四种
    *
    * @return
    */
  override def filterType(): String = FilterConstants.POST_TYPE //Post filters typically manipulate the response

  override def filterOrder(): Int = 10 //定义filter的顺序，数字越小表示顺序越高，越先执行

  override def shouldFilter(): Boolean = true //表示是否需要执行该filter，true表示执行，false表示不执行

  override def run(): AnyRef = {
    val context = RequestContext.getCurrentContext
    val servletResponse = context.getResponse
    servletResponse.addHeader("X-Sample", UUID.randomUUID.toString)
    null
  }
}
