package com.galaxy.scala.entity

import scala.beans.BeanProperty

/**
  * represent the Employee details
  *
  * @author pengwang
  * @date 2020/01/01
  */
class Employee {

  @BeanProperty
  var empId: String = _
  @BeanProperty
  var empName: String = _

  override def toString = s"Employee(empId=$empId, empName=$empName)"
}