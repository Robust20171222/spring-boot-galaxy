package com.galaxy.elastic.entity

import org.springframework.data.elasticsearch.annotations.Document

import scala.beans.BeanProperty

/**
  * Created by wangpeng
  * Date: 2018/8/3
  * Time: 10:40
  */
@Document(indexName = "blog", `type` = "article")
class Article {

  @BeanProperty
  var id: String = _

  @BeanProperty
  var title: String = _

  @BeanProperty
  var timestamp: String = _
}
