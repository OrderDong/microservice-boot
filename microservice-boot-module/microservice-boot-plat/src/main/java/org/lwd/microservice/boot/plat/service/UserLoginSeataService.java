package org.lwd.microservice.boot.plat.service;

import org.lwd.microservice.boot.core.entity.BaseResult;
import org.lwd.microservice.boot.plat.entity.dto.UserLoginDTO;

/**
 * seata登录信息服务处理
 * @author weidong
 * @version V1.0.0
 * @since 2023/7/3
 */
public interface UserLoginSeataService {

    /**
     * seata保存登录,保存登录信息和日志信息
     *
     * @param dto 参数
     * @return 保存结果
     */
    public BaseResult<Integer> saveUserLoginSeata(UserLoginDTO dto) throws Exception;

}
