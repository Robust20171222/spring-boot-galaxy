package com.galaxy.elastic.test

import java.util
import java.util.Date

import org.elasticsearch.action.bulk.byscroll.BulkByScrollResponse
import org.elasticsearch.action.get.GetResponse
import org.elasticsearch.action.search.SearchResponse
import org.elasticsearch.common.unit.TimeValue
import org.elasticsearch.index.query.QueryBuilders
import org.elasticsearch.index.reindex.DeleteByQueryAction
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder
import org.junit.Test

class DocumentApiTest extends BaseTest {

  def putJsonDocument(title: String, content: String, postDate: Date, tags: Array[String], author: String): java.util.Map[String, Object] = {
    val jsonDocument: java.util.Map[String, Object] = new util.HashMap[String, Object]
    jsonDocument.put("title", title)
    jsonDocument.put("content", content)
    jsonDocument.put("postDate", postDate)
    jsonDocument.put("tags", tags)
    jsonDocument.put("author", author)
    jsonDocument
  }

  @Test
  def testCreateIndex: Unit = {
    val doc: java.util.Map[String, Object] = putJsonDocument("ElasticSearch: Java API", "ElasticSearch provides the Java API, all operations "
      + "can be executed asynchronously using a client object.", new Date, Array("elasticsearch"), "Hüseyin Akdoğan")

    val response = this.transportClient.prepareIndex("kodcucom", "article", "1").setSource(doc).execute().actionGet()
    log.info(s"$response")
  }

  @Test
  def testGetIndex: Unit = {
    val getResponse: GetResponse = this.transportClient.prepareGet("kodcucom", "article", "1").execute.actionGet

    val source = getResponse.getSource
    println("------------------------------")
    println("Index: " + getResponse.getIndex)
    println("Type: " + getResponse.getType)
    println("Id: " + getResponse.getId)
    println("Version: " + getResponse.getVersion)
    println(source)
    println("------------------------------")
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

}