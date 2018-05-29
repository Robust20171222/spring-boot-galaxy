package com.galaxy.elastic.test

import java.net.InetAddress

import org.elasticsearch.client.{ClusterAdminClient, IndicesAdminClient}
import org.elasticsearch.client.transport.TransportClient
import org.elasticsearch.common.settings.Settings
import org.elasticsearch.common.transport.InetSocketTransportAddress
import org.elasticsearch.transport.client.PreBuiltTransportClient
import org.junit.{After, Before}
import org.slf4j.{Logger, LoggerFactory}

class BaseTest {

  val log: Logger = LoggerFactory.getLogger(classOf[BaseTest])

  var transportClient: TransportClient = _
  var indices: IndicesAdminClient = _
  var cluster: ClusterAdminClient = _

  @Before
  def init: Unit = {
    transportClient = client
    indices = transportClient.admin.indices
    cluster = transportClient.admin.cluster
  }

  @After
  def destory: Unit = {
    this.transportClient.close()
  }

  def client: TransportClient = {
    val settings = Settings.builder.put("cluster.name", "my-application").build
    transportClient = new PreBuiltTransportClient(settings)
      .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("localhost"), 9300))
    transportClient

//    val settings = Settings.builder.put("cluster.name", "aone").build
//    transportClient = new PreBuiltTransportClient(settings)
//      .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("es2"), 9300))
//    transportClient
  }
}
