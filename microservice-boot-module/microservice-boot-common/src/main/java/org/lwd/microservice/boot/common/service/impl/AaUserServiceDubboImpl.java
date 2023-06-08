package org.lwd.microservice.boot.common.service.impl;

import org.apache.dubbo.config.annotation.DubboService;
import org.lwd.microservice.boot.common.api.dto.User;
import org.lwd.microservice.boot.common.api.dubbo.AaUserServiceDubbo;
import org.springframework.stereotype.Service;

/**
 * @author weidong
 * @version V1.0.0
 * @description
 * @since 2023/4/12
 */
@Service
@DubboService(timeout = 5000, async = true)
public class AaUserServiceDubboImpl implements AaUserServiceDubbo {
    @Override
    public User getUserList() {
        for (int i = 0; i < 50; i++) {
            System.out.println(i);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
