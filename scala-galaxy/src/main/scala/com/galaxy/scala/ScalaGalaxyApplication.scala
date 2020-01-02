package com.galaxy.scala

import com.galaxy.scala.entity.Employee
import com.galaxy.scala.exception.InvalidInsuranceAmountException
import com.galaxy.scala.service.OrganizationService
import com.galaxy.scala.service.impl.EmployeeServiceImpl
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.ComponentScan
import org.springframework.scheduling.annotation.EnableAsync

/**
  * Created by wangpeng on 16/03/2018. 
  */
@EnableAsync
@SpringBootApplication
@ComponentScan(value = Array("com.galaxy.*"))
class ScalaGalaxyApplication

object ScalaGalaxyApplication {

  @throws[InvalidInsuranceAmountException]
  def main(args: Array[String]): Unit = {
    val context = SpringApplication.run(classOf[ScalaGalaxyApplication])
    val organizationService = context.getBean(classOf[OrganizationService])
    val employeeService = context.getBean(classOf[EmployeeServiceImpl])

    val emp = new Employee
    emp.setEmpId("emp1")
    emp.setEmpName("emp1")

    import com.galaxy.scala.entity.EmployeeHealthInsurance
    val employeeHealthInsurance = new EmployeeHealthInsurance
    employeeHealthInsurance.setEmpId("emp1")
    employeeHealthInsurance.setHealthInsuranceSchemeName("premium")
    employeeHealthInsurance.setCoverageAmount(-1)

    organizationService.joinOrganization(emp, employeeHealthInsurance)
//    employeeService.insertEmployee(emp)
  }
}
