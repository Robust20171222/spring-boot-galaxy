package com.galaxy.elastic.conf

import java.net.InetAddress

import org.elasticsearch.client.Client
import org.elasticsearch.common.settings.Settings
import org.elasticsearch.common.transport.InetSocketTransportAddress
import org.elasticsearch.transport.client.PreBuiltTransportClient
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean

class EsConfig {

  @Value("${elasticsearch.host}") private val EsHost = null

  @Value("${elasticsearch.port}") private val EsPort = 0

  @Value("${elasticsearch.clustername}") private val EsClusterName:String = null

  /**
    * @return
    * @throws Exception
    * @see https://www.elastic.co/guide/en/elasticsearch/client/java-api/5.6/transport-client.html
    */
  @Bean
  @throws[Exception]
  def client: Client = {
    val settings = Settings.builder.put("cluster.name", EsClusterName).build
    val client = new PreBuiltTransportClient(settings).addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName(EsHost), EsPort))
    client
  }

}
