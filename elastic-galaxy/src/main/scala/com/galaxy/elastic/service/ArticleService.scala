package com.galaxy.elastic.service

import com.galaxy.elastic.entity.Article

/**
  * Created by wangpeng
  * Date: 2018/8/3
  * Time: 10:51
  */
trait ArticleService {

  def save(article: Article): Article
}
