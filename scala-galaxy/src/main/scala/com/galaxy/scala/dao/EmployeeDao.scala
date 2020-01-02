package com.galaxy.scala.dao

import com.galaxy.scala.entity.Employee

/**
  *
  *
  * @author pengwang
  * @date 2020/01/01
  */
trait EmployeeDao {

  def insertEmployee(cus: Employee)

  def deleteEmployeeById(empid: String)
}