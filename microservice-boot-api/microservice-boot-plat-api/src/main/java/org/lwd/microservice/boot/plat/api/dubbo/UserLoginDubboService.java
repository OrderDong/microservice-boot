package org.lwd.microservice.boot.plat.api.dubbo;

import org.lwd.microservice.boot.core.entity.BaseResult;
import org.lwd.microservice.boot.plat.api.dto.UserLoginDubboDTO;

/**
 * 登录基本操作
 * @author weidong
 * @version V1.0.0
 * @since 2023/7/3
 */
public interface UserLoginDubboService {

    /**
     * 保存登录信息
     * @return 返回ID
     */
    BaseResult<Integer> saveUserLoginDubbo(UserLoginDubboDTO userLoginDubboDTO);

}
