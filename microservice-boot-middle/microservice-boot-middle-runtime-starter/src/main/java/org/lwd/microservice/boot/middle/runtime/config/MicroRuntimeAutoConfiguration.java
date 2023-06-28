package org.lwd.microservice.boot.middle.runtime.config;

import lombok.extern.slf4j.Slf4j;
import org.lwd.microservice.boot.core.constant.FilterOrderConstant;
import org.lwd.microservice.boot.middle.runtime.spring.SpringContextUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

/**
 * 运行时日志自动装配
 *
 * @author lwd
 * @since  2023/6/10
 */
@Slf4j
@Order(FilterOrderConstant.MIN_FILTER)
@Configuration
public class MicroRuntimeAutoConfiguration {
    public MicroRuntimeAutoConfiguration() {
        log.info("runtime组件装配完成...");
    }

    @Bean
    public ProjectNameConfig projectNameConfig() {
        return new ProjectNameConfig();
    }

    @Bean
    public SpringContextUtil springContextUtil(){return new SpringContextUtil();}

}
