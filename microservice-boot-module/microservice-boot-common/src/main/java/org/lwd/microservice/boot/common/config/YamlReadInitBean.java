package org.lwd.microservice.boot.common.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author weidong
 * @version V1.0.0
 * @description
 * @since 2023/4/7
 */
@Component
@Slf4j
public class YamlReadInitBean implements InitializingBean, DisposableBean {
    @Value("${dubbo.scan.base-packages}")
    private String DubboPackage;
    @Value("${dubbo.registry.parameters.namespace}")
    private String DubboNamespace;

    @Override
    public void afterPropertiesSet() throws Exception {
        log.info("DubboPackage:{}", DubboPackage);
        log.info("DubboNamespace:{}", DubboNamespace);
    }

    @Override
    public void destroy() throws Exception {
        log.info("YamlReadBean frontDestory:{}", DubboPackage);
    }
}
