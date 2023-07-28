package org.lwd.microservice.boot.es.controller;

import com.alibaba.fastjson2.JSON;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.client.RestHighLevelClient;
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

    //TODO 后边我们再说
    @GetMapping("/getReactiveTemp")
    @ResponseBody
    public String getReactiveTemp(){
        Query query = new StringQuery("{ \"match\": { \"userLoginId\": 1 } } ");
        query.setPageable(PageRequest.of(0, 10));
        Flux<SearchHit<VisitLog>> result = reactiveElasticsearchOperations.search(query,VisitLog.class);
        return JSON.toJSONString(result);
    }

}
