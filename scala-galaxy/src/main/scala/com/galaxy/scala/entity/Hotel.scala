package com.galaxy.scala.entity

import javax.persistence._

import scala.beans.BeanProperty

/**
  * Created by wangpeng on 16/03/2018. 
  */
@Entity
@Table(name = "hotel")
class Hotel extends Serializable {

  def this(name: String, address: String) {
    this()
    this.name = name
    this.address = address
  }

  def this(name: String) {
    this(name, "")
  }

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @BeanProperty
  var id: Long = _

  @Column(name = "city_id")
  @BeanProperty
  var city: Long = _

  @Column(name = "name")
  @BeanProperty
  var name: String = _

  @Column(name = "address")
  @BeanProperty
  var address: String = _

  @Column(name = "zip")
  @BeanProperty
  var zip: String = _
}
