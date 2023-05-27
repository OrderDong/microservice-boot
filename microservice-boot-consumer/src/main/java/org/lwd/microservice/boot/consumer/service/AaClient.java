package org.lwd.microservice.boot.consumer.service;

import org.lwd.microservice.boot.api.AaUserServiceDubbo;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.stereotype.Component;

/**
 * @author weidong
 * @version V1.0.0
 * @description
 * @since 2023/4/12
 */
@Component
public class AaClient {
    @DubboReference
    private AaUserServiceDubbo aaUserServiceDubbo;
}
