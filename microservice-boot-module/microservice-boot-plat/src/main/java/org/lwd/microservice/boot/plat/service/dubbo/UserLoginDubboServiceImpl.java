package org.lwd.microservice.boot.plat.service.dubbo;

import org.apache.dubbo.config.annotation.DubboService;
import org.lwd.microservice.boot.core.entity.BaseResult;
import org.lwd.microservice.boot.plat.api.dto.UserLoginDubboDTO;
import org.lwd.microservice.boot.plat.api.dubbo.UserLoginDubboService;
import org.lwd.microservice.boot.plat.entity.dto.UserLoginDTO;
import org.lwd.microservice.boot.plat.service.UserLoginService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 登录基本服务
 * @author weidong
 * @version V1.0.0
 * @since 2023/7/3
 */
@DubboService
public class UserLoginDubboServiceImpl implements UserLoginDubboService {

    @Autowired
    UserLoginService userLoginService;

    @Override
    public BaseResult<Integer> saveUserLoginDubbo(UserLoginDubboDTO userLoginDubboDTO) {
        BaseResult<Integer> result = BaseResult.success();
        UserLoginDTO userLoginDTO = new UserLoginDTO();
        BeanUtils.copyProperties(userLoginDubboDTO,userLoginDTO);
        BaseResult<Integer> baseResult = userLoginService.saveUserLogin(userLoginDTO);
        if(baseResult.isSuccess()){
            result.setData(baseResult.getData());
        }
        return result;
    }
}
