package org.lwd.microservice.boot.consumer.service;

import org.lwd.microservice.boot.api.UserServiceDubbo;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.stereotype.Component;

/**
 * @author weidong
 * @version V1.0.0
 * @description
 * @since 2023/4/12
 */
@Component
public class BaClient {
    @DubboReference
    private UserServiceDubbo userServiceDubbo;
}
