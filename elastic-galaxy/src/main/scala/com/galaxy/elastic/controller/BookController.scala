package com.galaxy.elastic.controller

import java.util
import java.util.Date

import org.apache.commons.lang3.StringUtils
import org.elasticsearch.action.update.UpdateRequest
import org.elasticsearch.client.transport.TransportClient
import org.elasticsearch.common.xcontent.XContentFactory
import org.elasticsearch.index.query.QueryBuilders
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.format.annotation.DateTimeFormat
import org.springframework.http.{HttpStatus, ResponseEntity}
import org.springframework.web.bind.annotation._

import scala.collection.JavaConversions._

/**
  * 图书信息管理ES Restful API
  *
  * Created by wangpeng
  * Date: 2018/9/18
  * Time: 16:13
  */
@RequestMapping(value = Array("/book"))
@RestController
class BookController {

  @Autowired
  private var transportClient: TransportClient = _

  @GetMapping(value = Array("/"))
  def index: Unit = {
    println("index")
  }

  @GetMapping(value = Array("/get/book/novel"))
  def get(id: String): ResponseEntity[Any] = {
    val result = this.transportClient.prepareGet("book", "novel", id).get()
    if (!result.isExists) {
      new ResponseEntity[Any](HttpStatus.NOT_FOUND)
    }
    new ResponseEntity[Any](result.getSource, HttpStatus.OK)
  }

  @PostMapping(value = Array("/add/book/novel"))
  def add(title: String, author: String, word_count: Int, @RequestParam(value = "publish_date") @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") publishDate: Date): ResponseEntity[Any] = {
    try {
      val content = XContentFactory.jsonBuilder().startObject().field("title", title).field("author", author).field("word_count", word_count).field("publish_date", publishDate.getTime).endObject()
      val result = this.transportClient.prepareIndex("book", "novel").setSource(content).get()
      new ResponseEntity[Any](result, HttpStatus.OK)
    } catch {
      case e: Exception =>
        e.printStackTrace()
        new ResponseEntity[Any](HttpStatus.INTERNAL_SERVER_ERROR)
    }
  }

  @DeleteMapping(value = Array("/delete/book/novel"))
  def delete(id: String): ResponseEntity[Any] = {
    val result = this.transportClient.prepareDelete("book", "novel", id).get()
    new ResponseEntity[Any](result.getResult.toString, HttpStatus.OK)
  }

  @PutMapping(value = Array("/update/book/novel"))
  def update(id: String, @RequestParam(required = false) title: String, @RequestParam(required = false) author: String): ResponseEntity[Any] = {
    try {
      val update = new UpdateRequest("book", "novel", id)
      val builder = XContentFactory.jsonBuilder().startObject()
      if (StringUtils.isNoneBlank(title)) {
        builder.field("title", title)
      }
      if (StringUtils.isNoneBlank(author)) {
        builder.field("author", author)
      }
      builder.endObject()
      update.doc(builder)

      val response = this.transportClient.update(update).get()
      new ResponseEntity[Any](response.getResult.toString, HttpStatus.OK)
    } catch {
      case e: Exception =>
        e.printStackTrace()
        new ResponseEntity[Any](HttpStatus.INTERNAL_SERVER_ERROR)
    }
  }

  @PostMapping(value = Array("/query/book/novel"))
  def query(@RequestParam(required = false) title: String, @RequestParam(required = false) author: String, @RequestParam(value = "gt_word_count", defaultValue = "0") gtWordCount: Int, @RequestParam(value = "lt_word_count", required = false) ltWordCount: Integer): ResponseEntity[Any] = {
    try {
      val boolQuery = QueryBuilders.boolQuery()

      if (StringUtils.isNoneBlank(title)) {
        boolQuery.must(QueryBuilders.matchQuery("title", title))
      }
      if (StringUtils.isNoneBlank(author)) {
        boolQuery.must(QueryBuilders.matchQuery("author", author))
      }

      val rangeQuery = QueryBuilders.rangeQuery("word_count").from(gtWordCount)
      if (ltWordCount != null && ltWordCount > 0) {
        rangeQuery.to(ltWordCount)
      }

      boolQuery.filter(rangeQuery)

      val builder = this.transportClient.prepareSearch("book").setTypes("novel").setQuery(boolQuery).setFrom(0).setSize(10)
      val response = builder.get()

      val result = new util.ArrayList[util.Map[String, Object]]()
      response.getHits.foreach(hit => {
        result.add(hit.getSource)
      })

      new ResponseEntity[Any](result, HttpStatus.OK)
    } catch {
      case e: Exception =>
        e.printStackTrace()
        new ResponseEntity[Any](HttpStatus.INTERNAL_SERVER_ERROR)
    }
  }
}
