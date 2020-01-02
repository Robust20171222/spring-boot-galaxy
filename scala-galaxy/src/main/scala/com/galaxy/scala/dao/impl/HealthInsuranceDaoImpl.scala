package com.galaxy.scala.dao.impl

import com.galaxy.scala.dao.HealthInsuranceDao
import com.galaxy.scala.entity.EmployeeHealthInsurance
import javax.annotation.PostConstruct
import javax.sql.DataSource
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.jdbc.core.support.JdbcDaoSupport
import org.springframework.stereotype.Repository

/**
  *
  *
  * @author pengwang
  * @date 2020/01/01
  */
@Repository
class HealthInsuranceDaoImpl extends JdbcDaoSupport with HealthInsuranceDao {

  @Autowired
  private var dataSource: DataSource = _

  @PostConstruct
  def initialize: Unit = {
    this.setDataSource(dataSource)
  }

  override def registerEmployeeHealthInsurance(emp: EmployeeHealthInsurance): Unit = {
    val sql = "INSERT INTO employeeHealthInsurance (empId, healthInsuranceSchemeName, coverageAmount) VALUES (?,?,?)"
    this.getJdbcTemplate.update(sql, emp.getEmpId, emp.getHealthInsuranceSchemeName, Int.box(emp.getCoverageAmount))
  }

  override def deleteEmployeeHealthInsuranceById(empid: String): Unit = {
    val sql = "DELETE FROM employeeHealthInsurance WHERE empId = ?"
    this.getJdbcTemplate.update(sql, empid)
  }
}