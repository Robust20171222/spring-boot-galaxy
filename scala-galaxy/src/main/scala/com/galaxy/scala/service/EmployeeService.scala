package com.galaxy.scala.service

import com.galaxy.scala.entity.Employee

/**
  *
  *
  * @author pengwang
  * @date 2020/01/01
  */
trait EmployeeService {

  def insertEmployee(emp: Employee)

  def deleteEmployeeById(empid :String)
}