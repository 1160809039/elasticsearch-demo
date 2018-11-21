package xin.shaozeming.es.po;

import lombok.Data;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.Date;

@Document(indexName = "test",type = "newsArticle")
@Data
public class NewsArticlePO {
    /**
     * <pre>
     * 主键id
     * 表字段 : news_article.id
     * </pre>
     */
    private Long id;

    /**
     * <pre>
     * 新闻标题
     * 表字段 : news_article.news_title
     * </pre>
     */
    @Field(type = FieldType.Text, analyzer = "ik_max_word",searchAnalyzer = "ik_max_word")
    private String newsTitle;

    /**
     * <pre>
     * 新闻来源
     * 表字段 : news_article.news_resource
     * </pre>
     */
    private String newsResource;

    /**
     * <pre>
     * 新闻类型
     * 表字段 : news_article.news_cate
     * </pre>
     */
    private String newsCate;

    /**
     * <pre>
     * 新闻格式
     * 表字段 : news_article.format_type
     * </pre>
     */
    private Byte formatType;

    /**
     * <pre>
     * 新闻原地址
     * 表字段 : news_article.news_url
     * </pre>
     */
    private String newsUrl;

    /**
     * <pre>
     * 爬数据取唯一id
     * 表字段 : news_article.crawler_id
     * </pre>
     */
    private String crawlerId;

    /**
     * <pre>
     * 数据爬取来源
     * 表字段 : news_article.crawler_resource
     * </pre>
     */
    private String crawlerResource;

    /**
     * <pre>
     * 标题图片，多张以逗号隔开
     * 表字段 : news_article.title_pic
     * </pre>
     */
    private String titlePic;

    /**
     * <pre>
     * 新闻发布时间
     * 表字段 : news_article.news_date
     * </pre>
     */
    private String newsDate;

    /**
     * <pre>
     * 阅读次数
     * 表字段 : news_article.read_num
     * </pre>
     */
    private Integer readNum;

    /**
     * <pre>
     * 0 接口 1 自己 2 爬虫
     * 表字段 : news_article.pub_mark
     * </pre>
     */
    private Byte pubMark;

    /**
     * <pre>
     * 修改时间
     * 表字段 : news_article.gtm_modified
     * </pre>
     */
    private Date gtmModified;

    /**
     * <pre>
     * 创建时间
     * 表字段 : news_article.gtm_created
     * </pre>
     */
    private Date gtmCreated;

}