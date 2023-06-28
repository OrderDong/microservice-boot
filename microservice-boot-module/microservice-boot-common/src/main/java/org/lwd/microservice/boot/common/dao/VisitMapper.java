package org.lwd.microservice.boot.common.dao;

import org.lwd.microservice.boot.common.entity.Visit;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 系统访问记录 Mapper 接口
 * </p>
 *
 * @author lwd
 * @since 2023-06-28
 */
@Mapper
public interface VisitMapper extends BaseMapper<Visit> {

}
