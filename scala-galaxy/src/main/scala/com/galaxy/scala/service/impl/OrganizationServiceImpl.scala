package com.galaxy.scala.service.impl

import com.galaxy.scala.entity.{Employee, EmployeeHealthInsurance}
import com.galaxy.scala.exception.InvalidInsuranceAmountException
import com.galaxy.scala.service.OrganizationService
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

/**
  *
  *
  * @author pengwang
  * @date 2020/01/01
  */
@Service
@Transactional
class OrganizationServiceImpl extends OrganizationService {

  import com.galaxy.scala.service.EmployeeService
  import com.galaxy.scala.service.HealthInsuranceService
  import org.springframework.beans.factory.annotation.Autowired

  @Autowired private val employeeService: EmployeeService = null
  @Autowired private val healthInsuranceService: HealthInsuranceService = null

  @Transactional(rollbackFor = Array(classOf[InvalidInsuranceAmountException]))
  @throws[InvalidInsuranceAmountException]
  override def joinOrganization(employee: Employee, employeeHealthInsurance: EmployeeHealthInsurance): Unit = {
    this.employeeService.insertEmployee(employee)
    try {
      this.healthInsuranceService.registerEmployeeHealthInsurance(employeeHealthInsurance)
    } catch {
      case e: InvalidInsuranceAmountException =>
        throw InvalidInsuranceAmountException("Exception is thrown")
    }
  }

  @Transactional
  override def leaveOrganization(employee: Employee, employeeHealthInsurance: EmployeeHealthInsurance): Unit = {
    this.employeeService.deleteEmployeeById(employee.getEmpId)
    this.healthInsuranceService.deleteEmployeeHealthInsuranceById(employeeHealthInsurance.getEmpId)
  }
}