package org.lwd.microservice.boot.common;

import org.lwd.microservice.boot.middle.runtime.util.YmlUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 *
 * @author weidong
 * @version V1.0.0
 * @since 2023/4/7
 */
@SpringBootApplication
public class CommonApplication {
    public static void main(String[] args) {
        //TODO 这块有一个问题，seata在注册到nacos时，订阅端的应用名称为unknown，经验证是获取不到ProjectNameConfig中设置的${spring.application.name}
        //估计是加载顺序获取其他问题，现在这获取数据，优先处理
        System.setProperty("project.name", YmlUtils.getApplicationName());
        SpringApplication.run(CommonApplication.class, args);
    }
}

