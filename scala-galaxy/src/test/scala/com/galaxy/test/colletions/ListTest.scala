package com.galaxy.test.colletions

import org.junit.Test

/**
  * Created by wangpeng
  * Date: 2019-01-14
  * Time: 14:08
  */
class ListTest {

  @Test
  def testExists: Unit ={
    val donuts: Seq[String] = Seq("Plain Donut", "Strawberry Donut", "Glazed Donut")
    println(s"Elements of donuts = $donuts")

    println("\nStep 2: How to check if a particular element exists in the sequence using the exists function")
    val doesPlainDonutExists: Boolean = donuts.exists(_ == "Plain Donut")
    println(s"Does Plain Donut exists = $doesPlainDonutExists")

    println("\nStep 3: How to declare a predicate value function for the exists function")
    val plainDonutPredicate: (String) => Boolean = (donutName) => donutName == "Plain Donut"
    println(s"Value function plainDonutPredicate = $plainDonutPredicate")

    val a:Option[Long] = Some(1)
    val c = a.exists(_ == 1)
    println(c)
  }

}
