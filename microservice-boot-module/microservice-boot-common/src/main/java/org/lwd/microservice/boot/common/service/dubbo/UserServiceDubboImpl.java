package org.lwd.microservice.boot.common.service.dubbo;/**
 * @author weidong
 * @description
 * @version V1.0.0
 * @since 2023/4/7
 */

import org.apache.dubbo.config.annotation.DubboService;
import org.lwd.microservice.boot.common.api.dto.User;
import org.lwd.microservice.boot.common.api.dubbo.UserServiceDubbo;
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
