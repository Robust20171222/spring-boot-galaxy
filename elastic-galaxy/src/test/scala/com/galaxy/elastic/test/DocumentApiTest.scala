package com.galaxy.elastic.test

import java.text.SimpleDateFormat
import java.util.Date

import org.apache.commons.lang3.StringUtils
import org.apache.commons.lang3.time.DateUtils
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest
import org.elasticsearch.action.admin.indices.stats.IndicesStatsRequest
import org.elasticsearch.action.bulk.byscroll.BulkByScrollResponse
import org.elasticsearch.action.search.SearchResponse
import org.elasticsearch.common.unit.TimeValue
import org.elasticsearch.common.xcontent.{XContentBuilder, XContentFactory}
import org.elasticsearch.index.query.QueryBuilders
import org.elasticsearch.index.reindex.DeleteByQueryAction
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder
import org.junit.Test

import scala.collection.JavaConversions._
import scala.collection.mutable.ListBuffer

class DocumentApiTest extends BaseTest {

  @Test
  def testCreateIndex: Unit = {
    val builder = XContentFactory.jsonBuilder()

    builder.startObject()
    builder.startObject("properties")
    builder.startObject("timestamp")
    builder.field("type","date")
    builder.endObject()
    builder.endObject()
    builder.endObject()

    println(builder.toString)
   // val isAcknowledged = this.transportClient.admin().indices().prepareCreate("test1").addMapping("test", "timestamp", "type=date").execute().get().isAcknowledged

    val isAcknowledged = this.transportClient.admin().indices().prepareCreate("test2").addMapping("test", builder).execute().get().isAcknowledged
    println(isAcknowledged)
  }

  /**
    * 搜索高亮测试
    */
  @Test
  def testSearchIndexWithHighlight: Unit = {
    val indexName = "bookdb_index"
    val indexType = "book"

    val highlightBuilder: HighlightBuilder = new HighlightBuilder().highlighterType("plain").field("*")
    val plainResponse: SearchResponse = this.transportClient.prepareSearch(indexName).setTypes(indexType).setQuery(QueryBuilders.matchQuery("title", "elasticsearch")).highlighter(highlightBuilder).get()
    log.info(s"PlainResponse: ${plainResponse}")

    val highlightBuilderWithFvh = new HighlightBuilder().highlighterType("fvh").field("*")
    val fvhResponse: SearchResponse = this.transportClient.prepareSearch(indexName).setTypes(indexType).setQuery(QueryBuilders.matchQuery("title", "elasticsearch guide")).highlighter(highlightBuilderWithFvh).get()
    log.info(s"FvhResponse: ${fvhResponse}")

    val highlightBuilderWithPostings = new HighlightBuilder().highlighterType("postings").field("*")
    val postingsResponse: SearchResponse = this.transportClient.prepareSearch(indexName).setTypes(indexType).setQuery(QueryBuilders.matchQuery("summary", "elasticsearch guide")).highlighter(highlightBuilderWithPostings).get()
    log.info(s"PostingsResponse: ${postingsResponse}")
  }

  /**
    * Using scrolls in Java
    * https://www.elastic.co/guide/en/elasticsearch/client/java-api/5.4/java-search-scrolling.html
    */
  @Test
  def testScroll: Unit = {
    val qb = QueryBuilders.rangeQuery("timestamp").gt("2018-05-01").lt("2018-05-15").includeLower(true).includeUpper(true)

    val searchRequestBuilder = this.transportClient.prepareSearch(".es_cluster")
    searchRequestBuilder.setScroll(new TimeValue(60000)).setQuery(qb).setSize(1000)
    println(s"searchRequestBuilder: ${searchRequestBuilder.toString}")
    var scrollResp = searchRequestBuilder.get()

    var length = 0
    var sum = 0
    do {
      scrollResp.getHits.getHits.foreach(f => {
        println(f.getSource)
      })
      length = scrollResp.getHits.getHits.length
      sum += length
      scrollResp = client.prepareSearchScroll(scrollResp.getScrollId).setScroll(new TimeValue(60000)).execute.actionGet
    } while (length != 0)
    println(sum)
  }

  @Test
  def testDeleteByQuery: Unit = {
    val qb = QueryBuilders.rangeQuery("publish_date").lt("now-8d").format("epoch_millis").includeLower(true).includeUpper(true)
    println(s"DeleteByQueryRequestBuilder: ${qb.toString}")

    /**
      * 同步删除测试
      */
    val response: BulkByScrollResponse = DeleteByQueryAction.INSTANCE.newRequestBuilder(client).abortOnVersionConflict(false).filter(qb).source("_all").get
    println(s"Sync delete test: ${response}")

    /**
      * 异步删除测试
      */
    //val deleteByQueryRequestBuilder: DeleteByQueryRequestBuilder = DeleteByQueryAction.INSTANCE.newRequestBuilder(client).abortOnVersionConflict(false).source("_all")

    //    deleteByQueryRequestBuilder.filter(qb).execute(new ActionListener[BulkByScrollResponse] {
    //      override def onResponse(response: BulkByScrollResponse): Unit = {
    //        log.info(s"Log del successful: ${response.toString}")
    //      }
    //
    //      override def onFailure(e: Exception): Unit = {
    //        log.error(s"Log del fail: ${ExceptionUtils.getMessage(e)}")
    //      }
    //    })
  }

  @Test
  def testDeleteIndex: Unit = {
    val indicesAdminClient = this.transportClient.admin().indices()
    val dateFormat = new SimpleDateFormat("yyyy.MM.dd")
    val nowTime = dateFormat.format(DateUtils.addDays(new Date(), -7))
    val nowDate = dateFormat.parse(nowTime)
    val isr = indicesAdminClient.stats(new IndicesStatsRequest().all).actionGet().getIndices.keySet()

    val list = new ListBuffer[String]()
    isr.foreach(index => {
      val lastIndex = index.lastIndexOf("-")
      if (lastIndex != -1) {
        val time = index.substring(lastIndex + 1)
        if (StringUtils.isNoneBlank(time)) {
          val indexDate = dateFormat.parse(time)
          val num = indexDate.compareTo(nowDate)
          if (num == -1) {
            if (indicesAdminClient.prepareExists(index).execute.actionGet.isExists) {
              list += index
            }
          }
        }
      }
    })

    if (list.nonEmpty) {
      indicesAdminClient.delete(new DeleteIndexRequest(list: _*)).actionGet()
    }
  }
}