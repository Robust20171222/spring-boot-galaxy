package com.galaxy.scala.service

import java.lang

import com.galaxy.scala.entity.Hotel

/**
  * Created by wangpeng on 16/03/2018. 
  */
trait HotelService {

  def findAll(): lang.Iterable[Hotel]
}
