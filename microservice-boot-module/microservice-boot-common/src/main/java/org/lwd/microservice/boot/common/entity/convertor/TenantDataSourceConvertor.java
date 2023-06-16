package org.lwd.microservice.boot.common.entity.convertor;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.lwd.microservice.boot.core.convertor.AbstractConvertor;
import org.lwd.microservice.boot.common.entity.dto.TenantDataSourceDTO;
import org.lwd.microservice.boot.common.entity.vo.TenantDataSourceVO;
import org.lwd.microservice.boot.common.entity.TenantDataSource;

/**
* <p>
* 租户数据源数据转换
* </p>
*
* @author lwd
* @since 2023-06-15
*/
@Mapper
public interface TenantDataSourceConvertor extends AbstractConvertor<TenantDataSourceVO, TenantDataSourceDTO, TenantDataSource> {
    TenantDataSourceConvertor INSTANCE = Mappers.getMapper(TenantDataSourceConvertor.class);
}
