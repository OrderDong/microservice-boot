package org.lwd.microservice.boot.common.api.dubbo;

import org.lwd.microservice.boot.common.api.dto.VisitDubboDTO;
import org.lwd.microservice.boot.core.entity.BaseResult;

/**
 * 访问日志接口
 * @author weidong
 * @version V1.0.0
 * @since 2023/6/28
 */
public interface VisitDubboService {
    /**
     * 保存访问日志
     * @return
     */
    BaseResult<Integer> saveVisitDubboService(VisitDubboDTO visitDubboDTO);
}
