package com.galaxy.elastic.controller

import com.galaxy.elastic.entity.Article
import com.galaxy.elastic.service.ArticleService
import io.swagger.annotations.{Api, ApiOperation}
import org.joda.time.DateTime
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.{PostMapping, RequestBody, RequestMapping, RestController}

/**
  * Created by wangpeng
  * Date: 2018/8/3
  * Time: 10:57
  */
@RequestMapping(value = Array("/article"))
@RestController
@Api(value = "文章信息Restful API") // it description of api at top
class ArticleController {

  final val DEFAULT_ES_TIMESTAMP_FORMAT: String = "yyyy-MM-dd'T'HH:mm:ssZZ"

  @Autowired
  private var articleServiceImpl: ArticleService = _

  @ApiOperation(value = "增加新的文章信息")               // it description of api
  @PostMapping(value = Array("/save"))
  def save(@RequestBody article: Article): Article = {
    article.timestamp = new DateTime().toString(DEFAULT_ES_TIMESTAMP_FORMAT)
    this.articleServiceImpl.save(article)
    article
  }
}
