package org.lwd.microservice.boot.middle.sentinel.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * sentinel API接口地址
 *
 * @author weidong
 * @version V1.0.0
 * @since 2023/7/20
 */
@Setter
@Getter
@Component
@ConfigurationProperties(prefix = "lwd.sentinel.api")
public class LwdSentinelApiConfigurationProperties {
    private String addr;
}
