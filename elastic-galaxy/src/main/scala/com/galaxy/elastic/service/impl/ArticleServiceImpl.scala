package com.galaxy.elastic.service.impl

import com.galaxy.elastic.dao.ArticleRepository
import com.galaxy.elastic.entity.Article
import com.galaxy.elastic.service.ArticleService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

/**
  * Created by wangpeng
  * Date: 2018/8/3
  * Time: 10:51
  */
@Service
class ArticleServiceImpl extends ArticleService {

  @Autowired
  private var articleRepository: ArticleRepository = _

  override def save(article: Article): Article = this.articleRepository.save(article)

}
