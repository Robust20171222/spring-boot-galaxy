package com.galaxy.scala.entity

import scala.beans.BeanProperty

/**
  *
  *
  * @author pengwang
  * @date 2020/01/01
  */
class EmployeeHealthInsurance {

  @BeanProperty
  var empId: String = _
  @BeanProperty
  var healthInsuranceSchemeName: String = _
  @BeanProperty
  var coverageAmount: Int = _
}