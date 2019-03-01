package com.galaxy.scala.quartz.service

/**
  * Created by wangpeng
  * Date: 2019-02-28
  * Time: 11:37
  */
trait TestService {

  @throws[Exception]
  def run(id: String): Unit
}
