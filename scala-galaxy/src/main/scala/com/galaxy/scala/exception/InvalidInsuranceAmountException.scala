package com.galaxy.scala.exception

/**
  *
  *
  * @author pengwang
  * @date 2020/01/02
  */
case class InvalidInsuranceAmountException(cause: String) extends Exception(cause)