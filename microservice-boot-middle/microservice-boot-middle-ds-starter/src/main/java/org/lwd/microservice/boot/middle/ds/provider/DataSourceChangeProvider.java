package org.lwd.microservice.boot.middle.ds.provider;

import com.alibaba.fastjson2.JSON;
import com.baomidou.dynamic.datasource.DynamicRoutingDataSource;
import com.baomidou.dynamic.datasource.creator.DefaultDataSourceCreator;
import com.baomidou.dynamic.datasource.spring.boot.autoconfigure.DataSourceProperty;
import com.baomidou.dynamic.datasource.toolkit.DynamicDataSourceContextHolder;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.common.utils.CollectionUtils;
import org.apache.dubbo.config.annotation.DubboReference;
import org.lwd.microservice.boot.common.api.dto.TenantDataSourceDubboDTO;
import org.lwd.microservice.boot.common.api.dubbo.TenantDataSourceDubboService;
import org.lwd.microservice.boot.core.constant.DataSourceConstant;
import org.lwd.microservice.boot.core.entity.BaseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.util.List;

/**
 * 加载数据库所有数据源
 *
 * @author weidong
 * @version V1.0.0
 * @since 2023/6/13
 */
@Order(1)
@Slf4j
@Component
public class DataSourceChangeProvider {

    public DataSourceChangeProvider() {
        log.info("--------init DataSourceChangeProvider--------");
    }


    @Autowired
    private DataSource dataSource;

    @Autowired
    @SuppressWarnings("all")
    private DefaultDataSourceCreator dataSourceCreator;

    //微服务模式下，替换成TenantDataSourceDubboService或者其他
    @DubboReference(timeout = 6000, check = false)
    TenantDataSourceDubboService tenantDataSourceDubboService;

    /**
     * 增加数据源
     *
     * @param dto 租户数据源信息
     */
    public void addDataSource(TenantDataSourceDubboDTO dto) {
        DynamicRoutingDataSource ds = (DynamicRoutingDataSource) dataSource;
        int tenantId = dto.getTenantId();
        String dsName = DataSourceConstant.TENANT_SOURCE_HEADER + tenantId;

        DataSourceProperty dataSourceProperty = new DataSourceProperty();
        dataSourceProperty.setPoolName(dsName);
        dataSourceProperty.setDriverClassName("com.mysql.jdbc.Driver");
        dataSourceProperty.setUrl(dto.getJdbcUri() + "?useUnicode=true&allowPublicKeyRetrieval=true&characterEncoding=utf8&zeroDateTimeBehavior=convertToNull&useSSL=true&serverTimezone=GMT%2B8&allowMultiQueries=true&nullCatalogMeansCurrent=true");
        dataSourceProperty.setUsername(dto.getJdbcUsername());
        dataSourceProperty.setPassword(dto.getJdbcPassword());
        DataSource dataSource = dataSourceCreator.createDataSource(dataSourceProperty);
        ds.addDataSource(dsName, dataSource);
    }

    /**
     * 移除数据源
     *
     * @param tenantId 租户ID
     */
    public void delDataSource(Integer tenantId) {
        DynamicRoutingDataSource ds = (DynamicRoutingDataSource) dataSource;
        ds.removeDataSource(DataSourceConstant.TENANT_SOURCE_HEADER + tenantId);
    }

    /**
     * 切换数据源
     *
     * @param dsName 租户ID
     */
    public void changeDataSource(String dsName) {
        //清空当前线程数据源
        //如果当前线程是连续切换数据源 只会移除掉当前线程的数据源名称
        DynamicDataSourceContextHolder.poll();
        DynamicDataSourceContextHolder.push(dsName);
    }


    /**
     * 检查数据源并加入
     *
     * @param dsName 数据源名称
     */
    public void checkDataSourceAndLoad(String dsName) {
        DynamicRoutingDataSource ds = (DynamicRoutingDataSource) dataSource;
        DataSource existDataSource = ds.getDataSources().get(dsName);
        if (existDataSource != null) return;

        BaseResult<List<TenantDataSourceDubboDTO>> listBaseResult = tenantDataSourceDubboService.getTenantDataSourceList();
        log.info("--------init DataSourceChangeProvider initDataSource--------:{}", JSON.toJSONString(listBaseResult));
        if (listBaseResult.isSuccess()) {
            List<TenantDataSourceDubboDTO> tenantDataSourceDubboDTOList = listBaseResult.getData();
            if (CollectionUtils.isNotEmpty(tenantDataSourceDubboDTOList)) {

                //数据源操作，也可以加入不同的数据源类型
                for (TenantDataSourceDubboDTO dto : tenantDataSourceDubboDTOList) {
                    if (dsName.equals(DataSourceConstant.TENANT_SOURCE_HEADER + dto.getTenantId())) {
                        this.addDataSource(dto);
                    }
                }
            }
        }
    }

}
