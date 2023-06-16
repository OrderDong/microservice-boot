package org.lwd.microservice.boot.common.dao;

import org.lwd.microservice.boot.common.entity.TenantDataSource;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 租户数据源 Mapper 接口
 * </p>
 *
 * @author lwd
 * @since 2023-06-15
 */
@Mapper
public interface TenantDataSourceMapper extends BaseMapper<TenantDataSource> {

}
