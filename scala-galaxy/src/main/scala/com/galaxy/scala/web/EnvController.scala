package com.galaxy.scala.web

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.core.env.Environment
import org.springframework.web.bind.annotation.{RequestMapping, RestController}

/**
  * Created by wangpeng
  * Date: 2018/5/27
  * Time: 10:43
  */
@RequestMapping(value = Array("/env"))
@RestController
class EnvController {

  @Autowired
  private var environment:Environment = _

  @RequestMapping(value = Array("/active_pro"))
  def env(): Array[String] ={
    val env:Array[String] = environment.getActiveProfiles
    env
  }
}
