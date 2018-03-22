package com.galaxy.scala.entity

/**
  * Created by wangpeng on 16/03/2018.
  *
  * @see https://alvinalexander.com/scala/how-to-use-scala-enums-enumeration-examples
  */
object TripType extends Enumeration {
  type TripType = Value;
  val BUSINESS, COUPLES, FAMILY, FRIENDS, SOLO = Value
  val FOUR = Value(0)
}