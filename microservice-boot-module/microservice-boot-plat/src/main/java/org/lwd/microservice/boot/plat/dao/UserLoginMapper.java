package org.lwd.microservice.boot.plat.dao;

import org.lwd.microservice.boot.plat.entity.UserLogin;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 登录 Mapper 接口
 * </p>
 *
 * @author lwd
 * @since 2023-06-30
 */
@Mapper
public interface UserLoginMapper extends BaseMapper<UserLogin> {

}
