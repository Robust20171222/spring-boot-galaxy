package com.galaxy.gateway.filter

import java.io.{ByteArrayInputStream, IOException, InputStream}

import com.netflix.hystrix.exception.HystrixTimeoutException
import org.springframework.cloud.netflix.zuul.filters.route.FallbackProvider
import org.springframework.http.client.ClientHttpResponse
import org.springframework.http.{HttpHeaders, HttpStatus, MediaType}
import org.springframework.stereotype.Component

/**
  * 路由熔断
  *
  * Created by wangpeng
  * Date: 2018/9/12
  * Time: 19:48
  */
@Component
class ProducerFallback extends FallbackProvider {

  /**
    * If you would like to provide a default fallback for all routes,
    * you can create a bean of type FallbackProvider and have the getRoute method return * or null
    *
    * @return
    */
  override def getRoute = "*"

  private def response(status: HttpStatus): ClientHttpResponse = new ClientHttpResponse {
    override def getStatusCode: HttpStatus = status

    override def getRawStatusCode: Int = status.value

    override def getStatusText: String = status.getReasonPhrase

    override def close(): Unit = {
    }

    @throws[IOException]
    override def getBody: InputStream = new ByteArrayInputStream("fallback".getBytes)

    override def getHeaders: HttpHeaders = {
      val headers: HttpHeaders = new HttpHeaders
      headers.setContentType(MediaType.APPLICATION_JSON)
      headers
    }
  }

  override def fallbackResponse(route: String, cause: Throwable): ClientHttpResponse = {
    if (cause.isInstanceOf[HystrixTimeoutException]) {
      response(HttpStatus.GATEWAY_TIMEOUT)
    } else {
      response(HttpStatus.INTERNAL_SERVER_ERROR)
    }
  }

}
