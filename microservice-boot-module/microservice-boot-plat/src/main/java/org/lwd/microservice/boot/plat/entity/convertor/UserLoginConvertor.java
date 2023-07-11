package org.lwd.microservice.boot.plat.entity.convertor;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.lwd.microservice.boot.core.convertor.AbstractConvertor;
import org.lwd.microservice.boot.plat.entity.dto.UserLoginDTO;
import org.lwd.microservice.boot.plat.entity.vo.UserLoginVO;
import org.lwd.microservice.boot.plat.entity.UserLogin;

/**
* <p>
* 登录数据转换
* </p>
*
* @author lwd
* @since 2023-06-30
*/
@Mapper
public interface UserLoginConvertor extends AbstractConvertor<UserLoginVO, UserLoginDTO, UserLogin> {
    UserLoginConvertor INSTANCE = Mappers.getMapper(UserLoginConvertor.class);
}
