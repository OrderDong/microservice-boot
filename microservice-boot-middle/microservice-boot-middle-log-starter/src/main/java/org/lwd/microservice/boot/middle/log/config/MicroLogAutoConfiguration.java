package org.lwd.microservice.boot.middle.log.config;

import lombok.extern.slf4j.Slf4j;
import org.lwd.microservice.boot.core.constant.FilterOrderConstant;
import org.lwd.microservice.boot.middle.log.aop.OperationLogAspect;
import org.lwd.microservice.boot.middle.log.filter.DubboTraceIdFilter;
import org.lwd.microservice.boot.middle.log.filter.WebTraceIdFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

/**
 * 操作日志自动装配
 *
 * @author lwd
 * @since  2023/6/10
 */
@Slf4j
@Order(FilterOrderConstant.LOG_FILTER)
@Configuration
public class MicroLogAutoConfiguration {
    public MicroLogAutoConfiguration() {
        log.info("log组件装配完成...");
    }

    @Bean
    public DubboTraceIdFilter dubboTraceIdFilter(){return new DubboTraceIdFilter();}

    @Bean
    public OperationLogAspect operationLogInterceptorAspect() {
        return new OperationLogAspect();
    }

    /*@Bean
    SystemLogAspect systemLogAspect() {
        return new SystemLogAspect();
    }*/

    @Bean
    public WebTraceIdFilter webTraceIdFilter() {
        return new WebTraceIdFilter();
    }

}
