package com.galaxy.docker.web

import org.springframework.web.bind.annotation.{GetMapping, RequestMapping, RestController}
import java.net.{InetAddress, UnknownHostException}

/**
  * Created by wangpeng
  * Date: 2018/5/31
  * Time: 22:17
  */
@RequestMapping(value = Array("/docker"))
@RestController
class HolaController {

  @GetMapping(value = Array("/hola"))
  @throws[UnknownHostException]
  def hola: String = {
    "Hola! Puedes encontrarme en " + InetAddress.getLocalHost.getHostName
  }
}
