package com.galaxy.test.traits

abstract class Pet (var name: String) {
  def speak // abstract
  def ownerIsHome { println("excited") }
  def jumpForJoy { println("jumping for joy") }
}