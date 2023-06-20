package org.lwd.microservice.boot.common.api.dubbo;

import org.lwd.microservice.boot.common.api.dto.TenantDataSourceDubboDTO;
import org.lwd.microservice.boot.core.entity.BaseResult;
import java.util.List;

/**
 * 数据源服务间接口
 * @author weidong
 * @version V1.0.0
 * @since 2023/6/16
 */
public interface TenantDataSourceDubboService {
    /**
     * 获取所有数据源
     * @return
     */
    BaseResult<List<TenantDataSourceDubboDTO>> getTenantDataSourceList();
}
