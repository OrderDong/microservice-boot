package org.lwd.microservice.boot.provider.service;/**
 * @author weidong
 * @description
 * @version V1.0.0
 * @since 2023/4/7
 */

import org.lwd.microservice.boot.api.User;
import org.lwd.microservice.boot.api.UserServiceDubbo;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@DubboService
public class UserServiceDubboImpl implements UserServiceDubbo {
    @Override
    public User getUserList() {
        User user = new User();
        user.setCreateTime(new Date());
        user.setName("lwd");
        user.setId(1);
        return user;
    }
}
