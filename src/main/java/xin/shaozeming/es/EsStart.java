package xin.shaozeming.es;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author: 邵泽铭
 * @date: 2018/11/19
 * @description:
 **/
@SpringBootApplication
public class EsStart {
    public static void main(String[] args) throws Exception{
        System.setProperty("es.set.netty.runtime.available.processors", "false");
        SpringApplication.run(EsStart.class,args);

    }
}