package com.galaxy.gateway.filter

import com.netflix.zuul.ZuulFilter
import com.netflix.zuul.context.RequestContext
import org.apache.commons.lang.StringUtils
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component

/**
  * Created by wangpeng
  * Date: 2018/9/12
  * Time: 19:28
  */
@Component
class TokenFilter extends ZuulFilter {

  private val logger = LoggerFactory.getLogger(classOf[TokenFilter])

  /**
    * 定义filter的类型，有pre、route、post、error四种
    *
    * @return
    */
  override def filterType(): String = "pre" //可以在请求被路由之前调用

  override def filterOrder(): Int = 10 //定义filter的顺序，数字越小表示顺序越高，越先执行

  override def shouldFilter(): Boolean = true //表示是否需要执行该filter，true表示执行，false表示不执行

  /**
    * filter需要执行的具体操作
    *
    * @return
    */
  override def run(): AnyRef = {
    val ctx = RequestContext.getCurrentContext
    val request = ctx.getRequest
    logger.info(s"--->>> TokenFilter ${request.getMethod},${request.getRequestURL.toString}")

    val token = request.getParameter("token") // 获取请求的参数
    if (StringUtils.isNotBlank(token)) {
      ctx.setSendZuulResponse(true) //对请求进行路由
      ctx.setResponseStatusCode(200)
      ctx.set("isSuccess", true)
      null
    }
    else {
      ctx.setSendZuulResponse(false) //不对其进行路由

      ctx.setResponseStatusCode(400)
      ctx.setResponseBody("token is empty")
      ctx.set("isSuccess", false)
      null
    }
  }
}
