package org.lwd.microservice.boot.common.service.dubbo;

import com.alibaba.fastjson2.JSON;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboService;
import org.lwd.microservice.boot.common.api.dto.TenantDataSourceDubboDTO;
import org.lwd.microservice.boot.common.api.dubbo.TenantDataSourceDubboService;
import org.lwd.microservice.boot.common.entity.convertor.TenantDataSourceConvertor;
import org.lwd.microservice.boot.common.entity.dto.TenantDataSourceDTO;
import org.lwd.microservice.boot.common.service.TenantDataSourceService;
import org.lwd.microservice.boot.core.entity.BaseResult;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 数据源服务间接口实现
 *
 * @author weidong
 * @version V1.0.0
 * @since 2023/6/16
 */
@Slf4j
@DubboService(timeout = 6000)
public class TenantDataSourceDubboServiceImpl implements TenantDataSourceDubboService {

    @Autowired
    TenantDataSourceService tenantDataSourceService;

    /**
     * 获取数据源列表
     *
     * @return
     */
    @Override
    public BaseResult<List<TenantDataSourceDubboDTO>> getTenantDataSourceList() {
        BaseResult<List<TenantDataSourceDubboDTO>> baseResult = BaseResult.success();
        BaseResult<List<TenantDataSourceDTO>> listBaseResult = tenantDataSourceService.getTenantDataSourceList();
        log.info("------getTenanDubbotDataSourceList-----:{}", JSON.toJSONString(listBaseResult));
        List<TenantDataSourceDubboDTO> dubboDTOList = listBaseResult.getData().stream().map(tenantDataSourceDTO -> {
            TenantDataSourceDubboDTO tenantDataSourceDubboDTO = new TenantDataSourceDubboDTO();
            BeanUtils.copyProperties(tenantDataSourceDTO, tenantDataSourceDubboDTO);
            return tenantDataSourceDubboDTO;
        }).collect(Collectors.toList());
        baseResult.setData(dubboDTOList);
        return baseResult;
    }
}
