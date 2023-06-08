package org.lwd.microservice.boot.plat.controller;

import com.alibaba.fastjson.JSON;
import org.apache.dubbo.config.annotation.DubboReference;
import org.lwd.microservice.boot.common.api.dto.User;
import org.lwd.microservice.boot.common.api.dubbo.AaUserServiceDubbo;
import org.lwd.microservice.boot.common.api.dubbo.UserServiceDubbo;
import org.springframework.web.bind.annotation.*;

/**
 * @author weidong
 * @version V1.0.0
 * @description
 * @since 2023/4/7
 */
@RestController
@RequestMapping("/dubbo/")
@CrossOrigin
public class UserController {

    @DubboReference(check = false,timeout = 6000)
    private UserServiceDubbo userControllerServiceDubbo;

    @DubboReference(check = false,timeout = 6000, async = true)
    private AaUserServiceDubbo aaUserServiceDubbo;

    @GetMapping(value = "/get")
    public String getUser(){
        User user = userControllerServiceDubbo.getUserList();
        return JSON.toJSONString(user);
    }
    @GetMapping(value = "/testTimeout")
    public String testTimeout(){
        User user = aaUserServiceDubbo.getUserList();
        return JSON.toJSONString(user);
    }
}
