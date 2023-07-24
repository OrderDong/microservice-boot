package org.lwd.microservice.boot.middle.sentinel.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 控制台配置
 *
 * @author weidong
 * @version V1.0.0
 * @since 2023/7/20
 */
@Setter
@Getter
@Component
@ConfigurationProperties(prefix = "lwd.sentinel.dashboard")
public class LwdSentinelServerConfigurationProperties {
    private String name;
    private String server;
}
