package com.galaxy.elastic.conf

import java.net.InetAddress
import java.util.concurrent.TimeUnit

import org.elasticsearch.client.transport.TransportClient
import org.elasticsearch.common.settings.Settings
import org.elasticsearch.common.transport.InetSocketTransportAddress
import org.elasticsearch.common.unit.TimeValue
import org.elasticsearch.transport.client.PreBuiltTransportClient
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.{Bean, ComponentScan, Configuration}
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories

@Configuration
@ConfigurationProperties(prefix = "es")
@EnableElasticsearchRepositories(basePackages = Array("com.galaxy.elastic.dao"))
@ComponentScan(basePackages = Array("com.galaxy.elastic.service"))
class EsConfig {

  @Value("${es.host}")
  private var host: String = _

  @Value("${es.port}")
  private var port: Int = 0

  @Bean
  def transportClient: TransportClient = {
    val settings = Settings.builder
      .put("client.transport.ignore_cluster_name", true)
      .put("transport.tcp.connect_timeout", new TimeValue(60, TimeUnit.SECONDS))
      .put("transport.tcp.compress", true)
      .build

    val client = new PreBuiltTransportClient(settings)
    client.addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName(host), port))
    client
  }

  @Bean
  def elasticsearchTemplate = new ElasticsearchTemplate(transportClient)

}
