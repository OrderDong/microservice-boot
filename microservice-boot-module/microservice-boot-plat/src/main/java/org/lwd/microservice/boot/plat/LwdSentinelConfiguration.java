package org.lwd.microservice.boot.plat;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.lwd.microservice.boot.middle.sentinel.config.LwdSentinelApiConfigurationProperties;
import org.lwd.microservice.boot.middle.sentinel.config.LwdSentinelServerConfigurationProperties;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.env.StandardEnvironment;

/**
 * 验证获取自定义配置
 *
 * @author weidong
 * @version V1.0.0
 * @since 2023/7/20
 */

@Slf4j
@Configuration
@ConditionalOnProperty(
        prefix = "lwd.sentinel",
        name = {"enabled"},
        havingValue = "true",
        matchIfMissing = true
)
@AllArgsConstructor
public class LwdSentinelConfiguration implements EnvironmentAware {
    private final LwdSentinelServerConfigurationProperties serverConfigurationProperties;
    private final LwdSentinelApiConfigurationProperties apiConfigurationProperties;

    @Override
    public void setEnvironment(Environment environment) {
        String prefix = "lwd.sentinel.";
        StandardEnvironment standardEnvironment = (StandardEnvironment) environment;
        this.getProValue(prefix, standardEnvironment);
    }

    private void getProValue(String prefix, Environment environment) {
        log.info("------init sentinel server addr: {}", serverConfigurationProperties.getServer());
    }

}
