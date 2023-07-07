package org.lwd.microservice.boot.plat;

import org.lwd.microservice.boot.middle.runtime.util.YmlUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author weidong
 * @version V1.0.0
 * @description
 * @since 2023/4/7
 */
@SpringBootApplication
public class PlatApplication {

    public static void main(String[] args) {
        System.setProperty("project.name",YmlUtils.getApplicationName());
        SpringApplication.run(PlatApplication.class, args);
    }

}
