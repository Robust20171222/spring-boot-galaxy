package com.galaxy.scala.web

import com.galaxy.scala.entity.Hotel
import com.galaxy.scala.service.impl.HotelServiceImpl
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation._

/**
  * Created by wangpeng on 16/03/2018. 
  */
@RestController
@RequestMapping(value = Array("/hotel"), produces = Array(MediaType.APPLICATION_JSON_UTF8_VALUE))
class HotelController {

  @Autowired
  var hotelService: HotelServiceImpl = _

  @GetMapping(value = Array("/list"))
  def findAll(): java.lang.Iterable[Hotel] = {
    this.hotelService.findAll()
  }
}
