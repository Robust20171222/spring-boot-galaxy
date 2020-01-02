package com.galaxy.scala.service.impl

import com.galaxy.scala.dao.EmployeeDao
import com.galaxy.scala.entity.Employee
import com.galaxy.scala.service.EmployeeService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.{Propagation, Transactional}

/**
  *
  *
  * @author pengwang
  * @date 2020/01/01
  */
@Service
@Transactional(propagation = Propagation.REQUIRED)
class EmployeeServiceImpl extends EmployeeService {

  @Autowired
  private var employeeDao: EmployeeDao = _

  override def insertEmployee(emp: Employee): Unit = {
    this.employeeDao.insertEmployee(emp)
    //throw new RuntimeException("thowing exception to test transaction rollback")
  }

  override def deleteEmployeeById(empid: String): Unit = this.employeeDao.deleteEmployeeById(empid)
}