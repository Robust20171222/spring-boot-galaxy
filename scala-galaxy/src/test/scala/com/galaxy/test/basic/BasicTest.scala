package com.galaxy.test.basic

import org.junit.Test

/**
  * Created by wangpeng
  * Date: 2019-02-13
  * Time: 20:05
  */
class BasicTest {

  @Test
  def testForWithYield: Unit = {
    var a = 0
    val numList = List(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)

    // for loop execution with a yield
    val retVal = for {a <- numList if a != 3; if a < 8} yield a

    // Now print returned values using another loop.
    for (a <- retVal) {
      println("Value of a: " + a)
    }
  }

  @Test
  def testExists: Unit ={
    println("Step 1: How to initialize a Sequence of donuts")
    val donuts: Seq[String] = Seq("Plain Donut", "Strawberry Donut", "Glazed Donut")
    println(s"Elements of donuts = $donuts")

    println("\nStep 2: How to check if a particular element exists in the sequence using the exists function")
    val doesPlainDonutExists: Boolean = donuts.exists(donutName => donutName == "Plain Donut")
    println(s"Does Plain Donut exists = $doesPlainDonutExists")

    println("\nStep 3: How to declare a predicate value function for the exists function")
    val plainDonutPredicate: (String) => Boolean = (donutName) => donutName == "Plain Donut"
    println(s"Value function plainDonutPredicate = $plainDonutPredicate")

    println("\nStep 4: How to find element Plain Donut using the exists function and passing through the predicate function from Step 3")
    println(s"Does Plain Donut exists = ${donuts.exists(plainDonutPredicate)}")

    println("\nStep 5: How to declare a predicate def function for the exists function")
    def plainDonutPredicateFunction(donutName: String): Boolean = donutName == "Plain Donut"

    println("\nStep 6: How to find element Plain Donut using the exists function and passing through the predicate function from Step 5")
    println(s"Does plain Donut exists = ${donuts.exists(plainDonutPredicateFunction(_))}")
  }
}
