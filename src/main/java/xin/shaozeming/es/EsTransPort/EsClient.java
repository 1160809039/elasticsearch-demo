package xin.shaozeming.es.EsTransPort;

import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.transport.client.PreBuiltTransportClient;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.InetSocketAddress;

/**
 * @author: 邵泽铭
 * @date: 2018/11/20
 * @description:
 **/
public class EsClient {
    private TransportClient client;
    public TransportClient getClient(){
        Settings setting = Settings.builder()
                .put("cluster.name", "elas_cluster")
                .put("client.transport.ignore_cluster_name", true)
//                    .put("client.transport.nodes_sampler_interval", 5) //
                .put("client.transport.sniff", true)
                .build();

        client = new PreBuiltTransportClient(setting)
                .addTransportAddress(
                        new TransportAddress(new InetSocketAddress("192.168.0.2",9300))
                );
        return  client;

    }

    public  void  search(){

        QueryBuilder queryBuilder= QueryBuilders.boolQuery()
                .must(QueryBuilders.matchQuery("newsTitle","首个电商俱乐部"))
                .must(QueryBuilders.matchQuery("newsCate","科技"));

        SearchResponse response= client.prepareSearch("test")
                .setTypes("newsArticle")
                .setQuery(queryBuilder).get();
        SearchHits searchHits = response.getHits();
        for(SearchHit searchHit : searchHits) {
            System.out.println(searchHit);
        }

        client.close();
    }

    public static void main(String[] args) throws Exception{
        EsClient esClient=new EsClient();
        esClient.getClient();
        esClient.search();
    }

}