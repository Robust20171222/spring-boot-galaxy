package com.galaxy.scala.dao.jpa

import java.lang

import com.galaxy.scala.entity.Hotel
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

/**
  * Created by wangpeng on 16/03/2018. 
  */
@Repository
trait HotelRepository extends CrudRepository[Hotel, Long] {

  @Query(nativeQuery = true,value = "select * from hotel group by name")
  def findAll(): lang.Iterable[Hotel]
}
