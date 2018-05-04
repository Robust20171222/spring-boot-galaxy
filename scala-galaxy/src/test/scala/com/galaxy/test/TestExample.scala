package com.galaxy.test

import com.galaxy.scala.entity.TripType
import org.junit.Test
import scala.annotation.switch

/**
  * Created by wangpeng on 19/03/2018. 
  */
class TestExample {

  @Test
  def testEnum() {
    val name = TripType.BUSINESS

    println(name)

    TripType.values foreach println
  }

  /**
    * @see https://alvinalexander.com/scala/scala-convert-array-to-string-mkstring
    */
  @Test
  def testArrayToString(): Unit ={
    val args = Array("Hello", "world", "it's", "me")
    var string = args.mkString(" ")
    println(string)

    string = args.mkString("\n")
    print(string)

    string = args.mkString(" . ")
    println(string)

    val numbers = Array(1,2,3)
    string = numbers.mkString(", ")
    println(string)
  }

  @Test
  def testSwitch(): Unit ={
    val i = 1
    val x = (i: @switch) match {
      case 1  => println("One")
      case 2  => println("Two")
      case _  => println("Other")
    }

    // test classOF
    val stringClass = classOf[String]
    println(stringClass.getMethods)
  }

  @Test
  def testList: Unit ={
    val x = List(2)
    val y = 1 :: x
    val z = 0 :: y
    println()
  }
}
