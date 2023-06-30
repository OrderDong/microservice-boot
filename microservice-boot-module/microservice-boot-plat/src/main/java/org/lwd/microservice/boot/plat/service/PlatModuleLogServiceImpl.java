
package org.lwd.microservice.boot.plat.service;

import com.alibaba.fastjson2.JSON;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboReference;
import org.lwd.microservice.boot.common.api.dto.VisitDubboDTO;
import org.lwd.microservice.boot.common.api.dubbo.VisitDubboService;
import org.lwd.microservice.boot.middle.log.entity.OperationLogDTO;
import org.lwd.microservice.boot.middle.log.service.ModuleLogService;
import org.springframework.stereotype.Service;

import java.util.concurrent.Future;


/**
 * @author weidong
 * @version V1.0.0
 * @since 2023/6/27
 */

@Slf4j
@Service
public class PlatModuleLogServiceImpl implements ModuleLogService {

    @DubboReference(check = false, timeout = 6000)
    VisitDubboService visitDubboService;

    @Override
    public Future<Boolean> savePlatLogAsync(OperationLogDTO operateLogDTO) {
        log.info("----PlatModuleLogServiceImpl savePlatLogAsync---:{}", JSON.toJSONString(operateLogDTO));
        return null;
    }

    @Override
    public Boolean savePlatLog(OperationLogDTO operateLogDTO) {
        log.info("----PlatModuleLogServiceImpl savePlatLog---:{}", JSON.toJSONString(operateLogDTO));
        VisitDubboDTO visitDubboDTO = new VisitDubboDTO();
        visitDubboDTO.setServerIpAddress(operateLogDTO.getClientIp());
        visitDubboService.saveVisitDubboService(visitDubboDTO);
        return null;
    }
}

