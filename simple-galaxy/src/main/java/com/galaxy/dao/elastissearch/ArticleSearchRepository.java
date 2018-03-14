package com.galaxy.dao.elastissearch;

import com.galaxy.entity.Article;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Created by wangpeng on 13/03/2018.
 */
public interface ArticleSearchRepository extends ElasticsearchRepository<Article, Long> {
}
