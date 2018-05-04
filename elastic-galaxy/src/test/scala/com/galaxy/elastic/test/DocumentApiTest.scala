package com.galaxy.elastic.test

import java.net.InetAddress

import lombok.extern.slf4j.Slf4j
import org.elasticsearch.action.index.IndexResponse
import org.elasticsearch.action.update.UpdateRequest
import org.elasticsearch.client.transport.TransportClient
import org.elasticsearch.client.{ClusterAdminClient, IndicesAdminClient}
import org.elasticsearch.common.settings.Settings
import org.elasticsearch.common.transport.InetSocketTransportAddress
import org.elasticsearch.common.xcontent.XContentFactory._
import org.elasticsearch.transport.client.PreBuiltTransportClient
import org.junit.{After, Before, Test}
import org.slf4j.{Logger, LoggerFactory}

import scala.collection.JavaConversions._
import util.control.Breaks._
import scala.collection.mutable

@Slf4j
class DocumentApiTest {

  private val log: Logger = LoggerFactory.getLogger(classOf[DocumentApiTest])

  private var transportClient: TransportClient = _
  private var indices: IndicesAdminClient = _
  private var cluster: ClusterAdminClient = _

  @Before
  def init: Unit = {
    transportClient = client
    indices = transportClient.admin.indices
    cluster = transportClient.admin.cluster
  }

  @Test
  def testClusterStatus: Unit = {
    log.info(s"${clusterStatus}")
  }

  @Test
  def testIndexDoc: Unit = {
    val json: String = "{" +
      "\"user\":\"kimchy\"," +
      "\"postDate\":\"2013-01-30\"," +
      "\"message\":\"trying out Elasticsearch\"" +
      "}"
    val response: IndexResponse = this.transportClient.prepareIndex("twitter", "tweet", "1").setSource(json).get()

    // Index name// Index name
    val _index = response.getIndex
    // Type name
    val _type = response.getType
    // Document ID (generated or not)
    val _id = response.getId
    // Version (if it's the first time you index this document, you will get: 1)
    val _version = response.getVersion
    // status has stored current instance statement.
    val status = response.status

    log.info(s"${response}")
  }

  @Test
  def testGetDoc: Unit = {
    val response = this.transportClient.prepareGet("twitter", "tweet", "1").get
    log.info(s"${response}")
  }

  @Test
  def testAliases: Unit = {
    val aliasesRequest = this.transportClient.admin().cluster().prepareState().execute().actionGet().getState.getMetaData.getAliasAndIndexLookup
    log.info(s"${aliasesRequest}")
  }

  @Test
  def testDelDoc: Unit = {
    val response = this.transportClient.prepareDelete("twitter", "tweet", "1").get
    log.info(s"${response}")
  }

  @Test
  def testUpdateDoc: Unit = {
    val updateRequest = new UpdateRequest
    updateRequest.index("twitter")
    updateRequest.`type`("tweet")
    updateRequest.id("1")
    updateRequest.doc(jsonBuilder.startObject.field("gender", "male").endObject)
    val response = this.transportClient.update(updateRequest).get
    log.info(s"${response}")
  }

  /**
    * @see https://www.elastic.co/guide/en/elasticsearch/client/java-api/5.4/java-docs-multi-get.html
    */
  @Test
  def testMultiGetResponse: Unit = {
    val multiGetItemResponses = client.prepareMultiGet.add("twitter", "tweet", "1").add("twitter", "tweet", "2", "3", "4").add("another", "type", "foo").get

    for (itemResponse <- multiGetItemResponses) {
      val response = itemResponse.getResponse
      if (response != null && response.isExists) {
        val json = response.getSourceAsString
        log.info(s"result : $json")
      }
    }
  }

  @After
  def destory: Unit = {
    this.transportClient.close()
  }

  /**
    * get the status of cluster
    *
    * @return
    */
  def clusterStatus: Map[String, Any] = {
    val health = cluster.prepareHealth().get
    val clusterStat = cluster.prepareClusterStats().get

    val map = mutable.HashMap[String, Any]()
    map += ("timestamp" -> clusterStat.getTimestamp)
    map += ("status" -> clusterStat.getStatus.name)
    map += ("node_total" -> clusterStat.getNodesStats.getCounts.getTotal)
    map ++= clusterStat.getNodesStats.getCounts.getRoles.map(entry => "node_" + entry._1 -> entry._2).toMap
    map += ("shard_total" -> clusterStat.getIndicesStats.getShards.getTotal)
    map += ("shard_success" -> health.getActiveShards)
    map += ("index_total" -> clusterStat.getIndicesStats.getIndexCount)
    map += ("doc_total" -> clusterStat.getIndicesStats.getDocs.getCount)
    map += ("disk_used" -> clusterStat.getIndicesStats.getStore.sizeInBytes)
    map += ("mem_used" -> clusterStat.getIndicesStats.getFieldData.getMemorySizeInBytes)
    map += ("replication" -> clusterStat.getIndicesStats.getShards.getReplication)
    map.toMap
  }

  def client: TransportClient = {
    val settings = Settings.builder.put("cluster.name", "my-application").put("logger.org.elasticsearch.transport.TransportService.tracer", "TRACE").build
    transportClient = new PreBuiltTransportClient(settings)
      .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("127.0.0.1"), 9300))
    transportClient
  }
}
