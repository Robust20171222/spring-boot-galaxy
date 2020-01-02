package com.galaxy.scala.dao

import com.galaxy.scala.entity.EmployeeHealthInsurance

/**
  *
  *
  * @author pengwang
  * @date 2020/01/01
  */
trait HealthInsuranceDao {

  def registerEmployeeHealthInsurance(employeeHealthInsurance: EmployeeHealthInsurance)

  def deleteEmployeeHealthInsuranceById(empid: String)
}