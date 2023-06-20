package org.lwd.microservice.boot.middle.ds.config;

import com.alibaba.fastjson2.JSON;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.common.utils.CollectionUtils;
import org.apache.dubbo.config.annotation.DubboReference;
import org.lwd.microservice.boot.common.api.dto.TenantDataSourceDubboDTO;
import org.lwd.microservice.boot.common.api.dubbo.TenantDataSourceDubboService;
import org.lwd.microservice.boot.core.entity.BaseResult;
import org.lwd.microservice.boot.middle.ds.aop.DataSourceChangeAdvisor;
import org.lwd.microservice.boot.middle.ds.provider.DataSourceChangeProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.annotation.Order;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * @author weidong
 * @version V1.0.0
 * @since 2023/6/16
 */
@Order(1)
@Slf4j
@Configuration
@Import({
        DataSourceChangeProvider.class,
        DataSourceChangeAdvisor.class
})
public class DataSourceChangeConfig {

    public DataSourceChangeConfig() {
        log.info("init DataSourceChangeConfig advisor .....");
    }

}


