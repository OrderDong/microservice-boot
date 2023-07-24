package org.lwd.microservice.boot.middle.sentinel.init;

import com.alibaba.csp.sentinel.init.InitExecutor;
import org.lwd.microservice.boot.middle.sentinel.rule.SentinelRule;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * sentinel starter 初始化
 *
 * @author weidong
 * @version V1.0.0
 * @since 2023/7/21
 */

public class LwdSentinelInit {

    public static void sentinelInitExecutor(ConfigurableApplicationContext context) {
        // 连接到控制台，与sentinel控制台通信

        //方式一：通过环境变量获取
        //System.setProperty("project.name", context.getEnvironment().getProperty("spring.application.name", "sentinel"));
        System.setProperty("csp.sentinel.dashboard.server", context.getEnvironment().getProperty("lwd.sentinel.dashboard.server", "1"));
        System.setProperty("csp.sentinel.api.port", context.getEnvironment().getProperty("lwd.sentinel.api.addr", "1"));
        //System.setProperty("sentinel.dashboard.app.hideAppNoMachineMillis", "60000");

        //方式二：手动设置
        //dashboard 地址
        //System.setProperty("csp.sentinel.dashboard.server", "localhost:7080");
        //API端口
        //System.setProperty("csp.sentinel.api.port", "localhost:8719");
        InitExecutor.doInit();
        SentinelRule.initSentinelRule();
    }
}
