package xin.shaozeming.es.service;

import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.data.elasticsearch.repository.support.AbstractElasticsearchRepository;
import org.springframework.stereotype.Service;
import xin.shaozeming.es.dao.NewsArticleDao;
import xin.shaozeming.es.po.NewsArticlePO;
import xin.shaozeming.es.repository.ArticleRepository;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: 邵泽铭
 * @date: 2018/11/20
 * @description:
 **/
@Service("newsArticleService")
public class NewsArticleService {
    @Resource
    private ArticleRepository articleRepository;
    @Resource
    private NewsArticleDao newsArticleDao;
    @Resource
    private ElasticsearchTemplate elasticsearchTemplate;
    public void save(){
       List<NewsArticlePO> list= newsArticleDao.selectList();
       Iterable<NewsArticlePO> res=  articleRepository.saveAll(list);
//        AbstractElasticsearchRepository
       res.forEach(item->{
           System.out.println(item.toString());
       });
    }

    public void deleteAll() {
        articleRepository.deleteAll();
    }


    public List<NewsArticlePO> findByNewsTitleAndAndNewsCate(String newsTitle,String newsCate){
        return   articleRepository.findByNewsTitleAndAndNewsCate(newsTitle,newsCate);
    }

    public List<NewsArticlePO> selectQuery(String newsTitle, String newsCate) {
        return   articleRepository.findByQuery(newsTitle,newsCate);
    }

    public Page<NewsArticlePO> selectPage(Pageable pageable) {

       return   articleRepository.findAll(pageable);
    }

    public Page<NewsArticlePO> searchBuild(String newsTitle, String newsCate) {

        QueryBuilder queryBuilder= QueryBuilders.boolQuery()
                .must(QueryBuilders.matchQuery("newsTitle",newsTitle))
                .must(QueryBuilders.matchQuery("newsCate",newsCate));

        SearchQuery searchQuery = new NativeSearchQueryBuilder()
                .withQuery(queryBuilder)
                .build();
        return   articleRepository.search(searchQuery);
    }


    public List<NewsArticlePO> searchTemplate(String newsTitle, String newsCate){
        QueryBuilder queryBuilder= QueryBuilders.boolQuery()
                .must(QueryBuilders.matchQuery("newsTitle",newsTitle))
                .must(QueryBuilders.matchQuery("newsCate",newsCate));

        SearchQuery searchQuery = new NativeSearchQueryBuilder()
                .withQuery(queryBuilder)
                .build();
        return elasticsearchTemplate.queryForList(searchQuery,NewsArticlePO.class);
    }
}