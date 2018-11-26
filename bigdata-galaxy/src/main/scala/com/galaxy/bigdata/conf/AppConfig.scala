package com.galaxy.bigdata.conf

import javax.sql.DataSource
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.jdbc.core.JdbcTemplate

/**
  * Created by wangpeng
  * Date: 2018-11-26
  * Time: 17:41
  */
class AppConfig {

  @Value("${bigdata.impala.connectionURL}")
  var connectionURL: String = _

  @Value("${bigdata.impala.username}")
  var userName: String = _

  @Value("${bigdata.impala.password}")
  var password: String = _

  @Bean
  def datasource(): DataSource = {
    val dataSource: com.cloudera.impala.jdbc41.DataSource = new com.cloudera.impala.jdbc41.DataSource
    dataSource.setURL(connectionURL)
    dataSource
  }

  @Bean
  def jdbcTemplate(dataSource: com.cloudera.impala.jdbc41.DataSource) = new JdbcTemplate(dataSource)
}
