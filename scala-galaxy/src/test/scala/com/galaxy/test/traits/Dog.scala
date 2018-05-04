package com.galaxy.test.traits

class Dog (name: String) extends Pet (name) with Tail {
  def speak { println("woof") }
  override def ownerIsHome {
    wagTail
    speak
  }
}
