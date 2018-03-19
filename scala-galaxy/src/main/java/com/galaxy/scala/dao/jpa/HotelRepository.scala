package com.galaxy.scala.dao.jpa

import com.galaxy.scala.entity.Hotel
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

/**
  * Created by wangpeng on 16/03/2018. 
  */
@Repository
trait HotelRepository extends CrudRepository[Hotel, Long] {

}
