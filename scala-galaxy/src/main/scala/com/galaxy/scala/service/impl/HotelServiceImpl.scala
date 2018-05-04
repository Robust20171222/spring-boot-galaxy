package com.galaxy.scala.service.impl

import java.lang

import com.galaxy.scala.dao.jpa.HotelRepository
import com.galaxy.scala.entity.Hotel
import com.galaxy.scala.service.HotelService
import javax.transaction.Transactional
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

/**
  * Created by wangpeng on 16/03/2018. 
  */
@Service
@Transactional
class HotelServiceImpl extends HotelService {

  @Autowired
  private var hotelRepository: HotelRepository = _

  override def findAll(): lang.Iterable[Hotel] = {
    this.hotelRepository.findAll()
  }
}


