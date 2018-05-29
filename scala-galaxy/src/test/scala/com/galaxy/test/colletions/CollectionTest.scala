package com.galaxy.test.colletions

import org.junit.Test

import scala.collection.mutable
import scala.collection.mutable.{ArrayBuffer, ListBuffer}

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

  /**
    * @see 10.14. Transforming One Collection to Another with map
    */
  @Test
  def testTransforming: Unit ={
    val helpers = Vector("adam", "kim", "melissa")
    val caps = helpers.map(e => e.capitalize)
    println(caps)

    val s = " eggs, milk, butter, Coco Puffs "
    val items = s.split(",").map(_.trim)
    println(items.mkString(","))

    val fruits = List("apple", "banana", "lime", "orange", "raspberry")
    val temp = fruits.filter(_.length < 6).map(_.toUpperCase)
    println(temp)
  }

  /**
    * @see 10.15. Flattening a List of Lists with flatten
    */
  @Test
  def testFlatten: Unit ={
    val lol = List(List(1,2), List(3,4))
    val result = lol.flatten
    println(result)
  }

  /**
    * @see 10.17. Using filter to Filter a Collection
    */
  @Test
  def testFilter: Unit ={
    val list = "apple" :: "banana" :: 1 :: 2 :: Nil

    val strings = list.filter {
      case s: String => true
      case _ => false
    }

    println(strings)
  }

  /**
    * 10.19. Splitting Sequences into Subsets (groupBy,partition, etc.)
    */
  @Test
  def testUnzip: Unit ={
    val couples = List(("Kim", "Al"), ("Julia", "Terry"))
    val (women, men) = couples.unzip
    println(women)
    println(men)

    val couples2 = women zip men
    println(couples2)
  }

  /**
    * 10.20. Walking Through a Collection with the reduce and
    * fold Methods
    */
  @Test
  def testReduceAndFold: Unit ={
    val a = Array(12, 6, 15, 2, 20, 9)

    var result = a.reduceLeft(_ + _)
    println(result)

    result = a.reduceLeft(_ * _)
    println(result)

    result = a.reduceLeft(_ min _)
    println(result)

    result = a.reduceLeft(_ max _)
    println(result)

    var fold_result = a.foldLeft(20)(_ + _)
    println(fold_result)

    fold_result = a.foldLeft(100)(_ + _)
    println(fold_result)
  }

  /**
    * 10.22. Merging Sequential Collections
    */
  @Test
  def testMerge: Unit ={
    val a = collection.mutable.ArrayBuffer(1,2,3)

    // Use the ++= method to merge a sequence into a mutable sequence.
    val b = a ++= Seq(4,5,6)
    println(b)

    // Use the ++ method to merge two mutable or immutable sequences.
    val c = a ++ b
    println(c)

    // distinct elements from both collections
    val d = a.union(b).distinct
    println(d)

    val e = a.diff(b)
    println(e)
  }

  @Test
  def testLazyView: Unit ={
    val view = (1 to 100).view
    println(view)
  }

  @Test
  def testListBuffer: Unit ={
    var fruits = new ListBuffer[String]()
    // add one element at a time to the ListBuffer
    fruits += "Apple"
    fruits += "Banana"
    fruits += "Orange"

    // add multiple elements
    fruits += ("Strawberry", "Kiwi", "Pineapple") // remove one element
    fruits -= "Apple"
    // remove multiple elements
    fruits -= ("Banana", "Orange")
    // remove multiple elements specified by another sequence
    fruits --= Seq("Kiwi", "Pineapple")

    // convert the ListBuffer to a List when you need to
    val fruitsList = fruits.toList
    println(fruitsList)
  }
}
