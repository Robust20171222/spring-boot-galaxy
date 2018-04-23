package com.galaxy.test.colletions

import org.junit.Test

import scala.collection.mutable


class MapTest {

  @Test
  def test: Unit ={
    val hashMap = new mutable.HashMap[String,Object]()
    hashMap+=("A"->"Cat")

    val hashMap2 = new mutable.HashMap[String,Object]()
    hashMap2+=("B"->"Bird")

    val hashMap3 = hashMap++hashMap2

    println(hashMap3)
  }
}
