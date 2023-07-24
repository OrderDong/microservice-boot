package org.lwd.microservice.boot.plat;

import org.lwd.microservice.boot.middle.runtime.util.YmlUtils;
import org.lwd.microservice.boot.middle.sentinel.init.LwdSentinelInit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * @author weidong
 * @version V1.0.0
 * @description
 * @since 2023/4/7
 */
@SpringBootApplication
public class PlatApplication {

    public static void main(String[] args) {
        System.setProperty("project.name", YmlUtils.getApplicationName());
        ConfigurableApplicationContext context = SpringApplication.run(PlatApplication.class, args);
        LwdSentinelInit.sentinelInitExecutor(context);
    }

}
