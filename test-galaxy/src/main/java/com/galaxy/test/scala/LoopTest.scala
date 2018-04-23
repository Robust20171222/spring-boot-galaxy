package com.galaxy.test.scala

/**
  * Created by wangpeng on 26/03/2018. 
  */
object LoopTest {

  def main(args: Array[String]): Unit = {
    val listTest:ListTest = new ListTest
    listTest.loopList()
  }
}

class ListTest{

  def loopList(): Unit ={
    val list = List(1,2,3,4,5,6,7,8,9)  // Creating a list
    //list.foreach((ele:Int)=>println(ele))
    for (num <- list){
      println(num)
    }
  }
}