package org.lwd.microservice.boot.plat.service;

import org.apache.dubbo.config.annotation.DubboReference;
import org.lwd.microservice.boot.common.api.dubbo.UserServiceDubbo;
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
