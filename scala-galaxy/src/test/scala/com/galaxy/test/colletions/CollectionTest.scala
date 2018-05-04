package com.galaxy.test.colletions

import org.junit.Test

import scala.collection.mutable
import scala.collection.mutable.ArrayBuffer

class CollectionTest {

  @Test
  def testHashMap: Unit = {
    val hashMap = new mutable.HashMap[String, Object]()
    hashMap += ("A" -> "Cat")

    val hashMap2 = new mutable.HashMap[String, Object]()
    hashMap2 += ("B" -> "Bird")

    val hashMap3 = hashMap ++ hashMap2

    println(hashMap3)
  }

  @Test
  def testVector: Unit = {
    val a = Vector(1, 2, 3, 4, 5)
    val b = a.take(2)
    println(b)

    val c = a.filter(_ >= 2)

    println(c)

    var d = Vector(1, 2, 3)
    d = d ++ Vector(4, 5)
    println(d)

    val x = IndexedSeq(1, 2, 3)
    println(x)
  }

  @Test
  def testArrayBuffer: Unit = {
    var nums = ArrayBuffer(1, 2, 3)

    //add one element
    nums += 4

    //add two or more elements (method has a varargs parameter)
    nums += (5, 6)

    //add elements from another collection
    nums ++= List(7, 8)

    println(nums)

    // remove one element
    nums -= 8

    // remove two or more elements
    nums --= Array(5, 6)
    println(nums)
  }

  /**
    * @see 10.9. Looping over a Collection with foreach
    */
  @Test
  def testForeachLoop: Unit = {
    val x = Vector(1, 2, 3)
    x.foreach(println)

    "HAL".foreach(println)

    //declare a multiline function, use this format
    val longWords = new StringBuilder
    "Hello world it's Al".split(" ").foreach { e =>
      if (e.length > 4) longWords.append(s" $e")
      else println("Not added: " + e)
    }

    // foreach map
    val m = Map("fname" -> "Tyler", "lname" -> "LeDude")
    m foreach (x => println(s"${x._1} -> ${x._2}"))

    m.foreach {
      case (movie, rating) => println(s"key: $movie, value: $rating")
    }
  }

  /**
    * @see 10.10. Looping over a Collection with a for Loop
    */
  @Test
  def testForLoop: Unit = {
    //loop over any Traversable type (basically any sequence) using a for loop:
    val fruits = Traversable("apple", "banana", "orange")
    for (f <- fruits) println(f)

    val fruits2 = Array("apple", "banana", "orange")
    for (f <- fruits2) {
      val s = f.toUpperCase
      println(s)
    }

    val newArray = for (e <- fruits) yield e.toUpperCase
    println(newArray)

    val names = Map("fname" -> "Ed", "lname" -> "Chigliak")
    for ((k,v) <- names) println(s"key: $k, value: $v")
  }
}
