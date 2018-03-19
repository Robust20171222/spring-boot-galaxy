package com.galaxy.test

import com.galaxy.scala.entity.TripType

/**
  * Created by wangpeng on 19/03/2018. 
  */
object Test {

  def main(args: Array[String]): Unit = {
    test();
  }

  def test() {
    var name = TripType.BUSINESS

    println(name)

    TripType.values foreach println
  }

}
