package com.galaxy.scala.dao.impl

import com.galaxy.scala.dao.EmployeeDao
import com.galaxy.scala.entity.Employee
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
class EmployeeDaoImpl extends JdbcDaoSupport with EmployeeDao {

  @Autowired
  private var dataSource: DataSource = _

  @PostConstruct
  def initialize: Unit = {
    this.setDataSource(dataSource)
  }

  override def insertEmployee(cus: Employee): Unit = {
    val sql = "INSERT INTO employee " + "(empId, empName) VALUES (?, ?)"
    this.getJdbcTemplate.update(sql, cus.getEmpId, cus.getEmpName)
  }

  override def deleteEmployeeById(empid: String): Unit = {
    val sql = "DELETE FROM employee WHERE empId = ?"
    this.getJdbcTemplate.update(sql, empid)
  }
}