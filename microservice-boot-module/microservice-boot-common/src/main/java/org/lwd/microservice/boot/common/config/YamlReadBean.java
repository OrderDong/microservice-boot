package org.lwd.microservice.boot.common.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * @author weidong
 * @version V1.0.0
 * @description
 * @since 2023/4/7
 */
@Component
@Slf4j
public class YamlReadBean {
    @Value("${dubbo.scan.base-packages}")
    private String DubboPackage;
    @Value("${dubbo.registry.parameters.namespace}")
    private String DubboNamespace;

    @PostConstruct
    public void printYamlParam() {
        log.info("DubboPackage:{}", DubboPackage);
        log.info("DubboNamespace:{}", DubboNamespace);
    }

    @PreDestroy
    public void frontDestory() {
        log.info("YamlReadBean frontDestory:{}", DubboPackage);
    }
}
