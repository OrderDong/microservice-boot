package org.lwd.microservice.boot.plat.service.impl;

import io.seata.core.context.RootContext;
import io.seata.spring.annotation.GlobalTransactional;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboReference;
import org.lwd.microservice.boot.common.api.dto.VisitDubboDTO;
import org.lwd.microservice.boot.common.api.dubbo.VisitDubboService;
import org.lwd.microservice.boot.core.constant.HttpStatusEnum;
import org.lwd.microservice.boot.core.entity.BaseResult;
import org.lwd.microservice.boot.plat.api.dto.UserLoginDubboDTO;
import org.lwd.microservice.boot.plat.api.dubbo.UserLoginDubboService;
import org.lwd.microservice.boot.plat.entity.dto.UserLoginDTO;
import org.lwd.microservice.boot.plat.service.UserLoginSeataService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

/**
 * @author weidong
 * @version V1.0.0
 * @since 2023/7/3
 */
@Slf4j
@Service
public class UserLoginSeataServiceImpl implements UserLoginSeataService {

    @DubboReference
    UserLoginDubboService userLoginDubboService;
    @DubboReference
    VisitDubboService visitDubboService;

    @Override
    @GlobalTransactional(timeoutMills = 30000, name = "default_tx_group")
    public BaseResult<Integer> saveUserLoginSeata(UserLoginDTO dto) throws Exception {
        BaseResult<Integer> result = BaseResult.success();
        log.info("开始全局事务:xid=" + RootContext.getXID());
        log.info("begin userLogin: " + dto);

        //保存登录信息
        UserLoginDubboDTO userLoginDubboDTO = new UserLoginDubboDTO();
        BeanUtils.copyProperties(dto, userLoginDubboDTO);
        BaseResult<Integer> userResult = userLoginDubboService.saveUserLoginDubbo(userLoginDubboDTO);
        if (userResult.isSuccess()) {
            result.setData(userResult.getData());
        } else {
            result.setCode(HttpStatusEnum.REQUEST_FAIL.getCode());
            throw new Exception("登录信息保存系统异常");
        }
        if (!result.isSuccess()) {
            return result;
        }

        //保存日志
        VisitDubboDTO visitDubboDTO = new VisitDubboDTO();
        visitDubboDTO.setServerIpAddress("3.3.3.3");
        if (dto.getEnabled().equals(1)) {
            visitDubboDTO.setCreateTime(dto.getCreateTime());
        }
        BaseResult<Integer> visitResult = visitDubboService.saveVisitDubboService(visitDubboDTO);
        if (visitResult.isSuccess()) {
            log.info("visit info pk:{}", visitResult.getData());
            result.setData(visitResult.getData());
        } else {
            result.setCode(HttpStatusEnum.REQUEST_FAIL.getCode());
            throw new Exception("日志保存系统异常");
        }

        return result;
    }
}
