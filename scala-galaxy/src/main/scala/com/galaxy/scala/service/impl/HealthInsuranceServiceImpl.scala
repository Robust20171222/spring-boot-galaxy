package com.galaxy.scala.service.impl

import com.galaxy.scala.entity.EmployeeHealthInsurance
import com.galaxy.scala.exception.InvalidInsuranceAmountException
import com.galaxy.scala.service.HealthInsuranceService
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
class HealthInsuranceServiceImpl extends HealthInsuranceService {

  import com.galaxy.scala.dao.HealthInsuranceDao
  import org.springframework.beans.factory.annotation.Autowired

  @Autowired
  private val healthInsuranceDao: HealthInsuranceDao = null

  @throws[InvalidInsuranceAmountException]
  override def registerEmployeeHealthInsurance(employeeHealthInsurance: EmployeeHealthInsurance): Unit = {
    if (employeeHealthInsurance.getCoverageAmount < 0) {
      throw InvalidInsuranceAmountException("Coverage Amount Should not be negative")
    }
    this.healthInsuranceDao.registerEmployeeHealthInsurance(employeeHealthInsurance)
  }

  override def deleteEmployeeHealthInsuranceById(empid: String): Unit = this.healthInsuranceDao.deleteEmployeeHealthInsuranceById(empid)
}