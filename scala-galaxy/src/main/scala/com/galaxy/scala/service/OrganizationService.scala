package com.galaxy.scala.service

import com.galaxy.scala.exception.InvalidInsuranceAmountException

/**
  *
  *
  * @author pengwang
  * @date 2020/01/01
  */
trait OrganizationService {

  import com.galaxy.scala.entity.{Employee, EmployeeHealthInsurance}

  @throws[InvalidInsuranceAmountException]
  def joinOrganization(employee: Employee, employeeHealthInsurance: EmployeeHealthInsurance): Unit

  def leaveOrganization(employee: Employee, employeeHealthInsurance: EmployeeHealthInsurance): Unit
}