package com.galaxy.scala.service

import com.galaxy.scala.exception.InvalidInsuranceAmountException

/**
  *
  *
  * @author pengwang
  * @date 2020/01/01
  */
trait HealthInsuranceService {

  import com.galaxy.scala.entity.EmployeeHealthInsurance

  @throws[InvalidInsuranceAmountException]
  def registerEmployeeHealthInsurance(employeeHealthInsurance: EmployeeHealthInsurance): Unit

  def deleteEmployeeHealthInsuranceById(empid: String): Unit
}