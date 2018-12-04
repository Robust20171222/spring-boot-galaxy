package com.galaxy.bigdata.web

import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.{RequestMapping, RestController}

/**
  * Created by wangpeng
  * Date: 2018-11-26
  * Time: 17:49
  */
@RestController
@RequestMapping(value = Array("/hello"), produces = Array(MediaType.APPLICATION_JSON_UTF8_VALUE))
class HelloController {

  @RequestMapping(value = Array("/hello"))
  def hello: String = {
    "Hello, bigdata!"
  }

}