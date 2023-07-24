package org.lwd.microservice.boot.middle.sentinel;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * 操作sentinel自动装配
 *
 * @author lwd
 * @since 2023/6/10
 */
@Slf4j
@ComponentScan(basePackages = "org.lwd.microservice.boot.middle.sentinel.config")
@Configuration
public class MicroSentinelAutoConfiguration {
    public MicroSentinelAutoConfiguration() {
        log.info("sentinel组件装配完成...");
    }

}
