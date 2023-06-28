package org.lwd.microservice.boot.middle.runtime.config;


import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

/**
 * @author weidong
 * @date 20230429
 * @description nacos消费应用名称配置
 */
@Configuration
public class ProjectNameConfig implements EnvironmentAware {

    @Value("${spring.application.name}")
    private  String applicationName;

    @Override
    public void setEnvironment(Environment environment) {
        if(StringUtils.isBlank(System.getProperty("project.name"))){
            System.setProperty("project.name",applicationName);
        }
    }
}

