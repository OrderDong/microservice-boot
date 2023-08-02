package org.lwd.microservice.boot.es.controller;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch._types.ShardStatistics;
import co.elastic.clients.elasticsearch.core.SearchRequest;
import co.elastic.clients.elasticsearch.core.SearchResponse;
import co.elastic.clients.elasticsearch.core.search.Hit;
import co.elastic.clients.elasticsearch.core.search.HitsMetadata;
import co.elastic.clients.elasticsearch.core.search.TotalHits;
import com.alibaba.fastjson2.JSON;
import lombok.extern.slf4j.Slf4j;
import org.lwd.microservice.boot.es.entity.VisitLog;
import org.lwd.microservice.boot.es.service.VisitLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.*;
import org.springframework.data.elasticsearch.core.query.Query;
import org.springframework.data.elasticsearch.core.query.StringQuery;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.io.StringReader;
import java.util.Arrays;
import java.util.List;

/**
 * @author weidong
 * @version V1.0.0
 * @since 2023/7/26
 */
@Slf4j
@RestController
@RequestMapping("/es")
public class VisitLogController {
    @Autowired
    VisitLogService visitLogService;

    //rest模版操作
    @Autowired
    ElasticsearchOperations elasticsearchOperations;

    //响应式模版操作
    @Autowired
    ReactiveElasticsearchOperations reactiveElasticsearchOperations;


    @PostMapping("/saveVisitLog")
    @ResponseBody
    public String saveVisitLog(@RequestBody VisitLog visitLog){
        VisitLog saveVisitLog = visitLogService.saveVisitLog(visitLog);
        log.info("----saveVisitLog----:{}", JSON.toJSONString(saveVisitLog));
        return JSON.toJSONString(saveVisitLog);
    }

    @GetMapping("/getVisitLogAll")
    @ResponseBody
    public String getVisitLogAll(){
        List<VisitLog> logList = visitLogService.findAll();
        log.info("----getVisitLogAll----:{}", JSON.toJSONString(logList));
        return JSON.toJSONString(logList);
    }

    //自定义查询
    @GetMapping("/getVisitLogByUserLoginId")
    @ResponseBody
    public String getVisitLogByUserLoginId(Integer userLoginId){
        List<VisitLog> logList = visitLogService.findByUserLoginId(userLoginId);
        log.info("----getVisitLogAll----:{}", JSON.toJSONString(logList));
        return JSON.toJSONString(logList);
    }

    //rest客户端模版
    @GetMapping("/getHighTempt")
    @ResponseBody
    public String getHighTempt(){
        Query query = new StringQuery("{ \"match\": { \"userLoginId\": 1 } } ");
        query.setPageable(PageRequest.of(0, 10));
        SearchHits<VisitLog>  searchHits = elasticsearchOperations.search(query,VisitLog.class);
        return JSON.toJSONString(searchHits);
    }

    //响应式客户端
    @GetMapping("/getReactiveTemp")
    @ResponseBody
    public String getReactiveTemp(){
        Query query = new StringQuery("{ \"match\": { \"userLoginId\": 1 } } ");
        query.setPageable(PageRequest.of(0, 10));
        Flux<SearchHit<VisitLog>> result = reactiveElasticsearchOperations.search(query,VisitLog.class);
        return JSON.toJSONString(result);
    }

    @Autowired
    ElasticsearchClient elasticsearchClient;
    //elasticsearch client 7.17.9
    @GetMapping("/getRestTemp")
    @ResponseBody
    public String getRestTemp(){
        StringReader sr = new StringReader(
                "{\n" +
                        "  \"query\": { \"match\": {\n" +
                        "    \"userLoginId\": \"1\"\n" +
                        "  }}" +
                        "}"
        );
        //查询所有
       /* StringReader sr = new StringReader(
               "{\n" +
                       "  \"query\": {\n" +
                       "    \"match_all\" : {}\n" +
                       "  }\n" +
                       "}\n"
        );*/
        SearchRequest request = new SearchRequest.Builder().index(Arrays.asList(new String[]{"visit_log"}))
                .withJson(sr)
                .build();
        try {
            SearchResponse<VisitLog>  search = elasticsearchClient.search(request,VisitLog.class);
            System.out.println("search.toString() = " + search.toString());
            long took = search.took();
            System.out.println("took = " + took);
            boolean b = search.timedOut();
            System.out.println("b = " + b);
            ShardStatistics shards = search.shards();
            System.out.println("shards = " + shards);
            HitsMetadata<VisitLog> hits = search.hits();
            TotalHits total = hits.total();
            System.out.println("total = " + total);
            Double maxScore = hits.maxScore();
            System.out.println("maxScore = " + maxScore);
            List<Hit<VisitLog>> list = hits.hits();
            for (Hit<VisitLog> visitLogHit : list) {
                System.out.println("visitLogHit.source() = " + JSON.toJSONString(visitLogHit.source()));
                System.out.println("visitLogHit.score() = " + visitLogHit.score());
                System.out.println("visitLogHit.index() = " + visitLogHit.index());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

}
