package com.galaxy.elastic.dao

import com.galaxy.elastic.entity.Article
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository

/**
  * Created by wangpeng
  * Date: 2018/8/3
  * Time: 10:50
  */
trait ArticleRepository extends ElasticsearchRepository[Article, String]{

}
