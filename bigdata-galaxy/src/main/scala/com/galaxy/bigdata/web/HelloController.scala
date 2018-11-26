package com.galaxy.bigdata.web

import org.springframework.web.bind.annotation.{RequestMapping, RestController}

/**
  * Created by wangpeng
  * Date: 2018-11-26
  * Time: 17:49
  */
@RestController
@RequestMapping("/hello")
class HelloController {

  @RequestMapping("/")
  def hello: String = {
    "Hello, bigdata!"
  }

}