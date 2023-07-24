package org.lwd.microservice.boot.plat.controller;

import com.alibaba.csp.sentinel.EntryType;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.fastjson2.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 流量控制-测试控制器
 *
 * @author weidong
 * @version V1.0.0
 * @since 2023/7/14
 */
@Slf4j
@RestController
@RequestMapping("/sentinel/")
@CrossOrigin
public class SentinelController {

    @SentinelResource(value = "sayHello",
            fallback = "circuitBreakerFallback", blockHandler = "sayHelloExceptionHandler", entryType = EntryType.IN)
    @GetMapping(value = "/sayHello")
    public String testInterGet(String name) {
        log.info("----hello sentinel---:{}", name);
        return JSON.toJSONString(name);
    }

    /**
     * 熔断降级
     * @return
     */
    @SentinelResource(value = "circuitBreaker", fallback = "circuitBreakerFallback", blockHandler = "sayHelloExceptionHandler")
    @GetMapping(value = "/circuitBreaker")
    public String circuitBreaker(String name){
        if ("lwd".equals(name)){
            return "hello,"+ name;
        }
        throw new RuntimeException("发生异常");
    }

    public String circuitBreakerFallback(String name){
        log.info("----熔断降级处理---:{}",name);
        return "服务异常，熔断降级, 请稍后重试!";
    }

    public String sayHelloExceptionHandler(String name, BlockException ex){
        log.info("----限流降级处理---:{}",name);
        return "访问过快，限流降级, 请稍后重试!";
    }


}
