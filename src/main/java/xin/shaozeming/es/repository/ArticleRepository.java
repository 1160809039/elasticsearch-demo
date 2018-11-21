package xin.shaozeming.es.repository;

import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import xin.shaozeming.es.po.NewsArticlePO;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;

/**
 * @author: 邵泽铭
 * @date: 2018/11/19
 * @description:
 **/

public interface ArticleRepository extends ElasticsearchRepository<NewsArticlePO,Long> {


    /**
    * 根据标题，类别两个字段搜索
    * */
    List<NewsArticlePO> findByNewsTitleAndAndNewsCate(String newsTitle,String newsCate);

    @Query("{\"bool\": {\"must\": [{ \"match\": { \"newsTitle\": \"?0\"}},{ \"match\": { \"newsCate\": \"?1\"}}]}}")
    List<NewsArticlePO> findByQuery(String newsTitle, String newsCate);
}