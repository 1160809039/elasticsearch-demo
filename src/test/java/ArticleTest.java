import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import xin.shaozeming.es.EsStart;
import xin.shaozeming.es.po.NewsArticlePO;
import xin.shaozeming.es.service.NewsArticleService;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author: 邵泽铭
 * @date: 2018/11/20
 * @description:
 **/
@RunWith(SpringRunner.class)
@SpringBootTest(classes = EsStart.class)
@WebAppConfiguration
public class ArticleTest {
  @Resource
    private NewsArticleService newsArticleService;

  @Test
    public  void  save(){
      newsArticleService.save();
    }

  @Test
  public  void  deleteAll(){
    newsArticleService.deleteAll();
  }

  @Test
  public void select(){
    List<NewsArticlePO> list= newsArticleService.findByNewsTitleAndAndNewsCate("首个电商俱乐部","科技");

    System.out.println(list.get(0).toString());
  }

  @Test
  public void selectQuery(){
    List<NewsArticlePO> list= newsArticleService.selectQuery("首个电商俱乐部","科技");

    System.out.println(list.get(0).toString());
  }

  @Test
  public void selectPage(){
    Pageable pageable=new PageRequest(1,5);
    Page<NewsArticlePO> page= newsArticleService.selectPage(pageable);

    System.out.println(page.getTotalPages());
  }


  @Test
  public void selectBuild(){
    Pageable pageable=new PageRequest(1,5);
    Page<NewsArticlePO> page= newsArticleService.searchBuild("首个电商俱乐部","科技");

    System.out.println(page.getNumber());
  }

  @Test
  public void searchTemplate(){
    Pageable pageable=new PageRequest(1,5);
    List page= newsArticleService.searchTemplate("首个电商俱乐部","科技");

    System.out.println(page);
  }
}