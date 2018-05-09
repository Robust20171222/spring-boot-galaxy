package com.galaxy.elastic.test

import java.util
import java.util.Date

import org.elasticsearch.action.get.GetResponse
import org.elasticsearch.action.search.SearchResponse
import org.elasticsearch.index.query.{QueryBuilder, QueryBuilders}
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder
import org.junit.Test

import scala.io.Source

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
    val plainResponse: SearchResponse = this.transportClient.prepareSearch(indexName).setTypes(indexType).setQuery(QueryBuilders.matchQuery("title","elasticsearch")).highlighter(highlightBuilder).get()
    log.info(s"PlainResponse: ${plainResponse}")

    val highlightBuilderWithFvh = new HighlightBuilder().highlighterType("fvh").field("*")
    val fvhResponse: SearchResponse = this.transportClient.prepareSearch(indexName).setTypes(indexType).setQuery(QueryBuilders.matchQuery("title","elasticsearch guide")).highlighter(highlightBuilderWithFvh).get()
    log.info(s"FvhResponse: ${fvhResponse}")

    val highlightBuilderWithPostings = new HighlightBuilder().highlighterType("postings").field("*")
    val postingsResponse: SearchResponse = this.transportClient.prepareSearch(indexName).setTypes(indexType).setQuery(QueryBuilders.matchQuery("summary","elasticsearch guide")).highlighter(highlightBuilderWithPostings).get()
    log.info(s"PostingsResponse: ${postingsResponse}")
  }

  @Test
  def testHttp: Unit = {
    println(Source.fromURL("http://127.0.0.1:9200", "utf-8").getLines.mkString("\n"))
  }
}
