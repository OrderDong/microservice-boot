package org.lwd.microservice.boot.es;

import org.lwd.microservice.boot.middle.runtime.util.YmlUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 *
 * @author weidong
 * @version V1.0.0
 * @since 2023/7/26
 */
@SpringBootApplication
public class EsApplication {
    public static void main(String[] args) {
        System.setProperty("project.name", YmlUtils.getApplicationName());
        ConfigurableApplicationContext context = SpringApplication.run(EsApplication.class, args);
    }
}

