package org.lwd.microservice.boot.common.service.dubbo;

import com.alibaba.fastjson2.JSON;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboService;
import org.lwd.microservice.boot.common.api.dto.VisitDubboDTO;
import org.lwd.microservice.boot.common.api.dubbo.VisitDubboService;
import org.lwd.microservice.boot.core.entity.BaseResult;


/**
 * @author weidong
 * @version V1.0.0
 * @since 2023/6/28
 */
@Slf4j
@DubboService
public class VisitDubboServiceImpl implements VisitDubboService {

    /**
     * 保存
     * @return
     */
    @Override
    public BaseResult<Integer> saveVisitDubboService(VisitDubboDTO visitDubboDTO) {
        log.info("----i am do saveVisitDubboService-----:{}", JSON.toJSONString(visitDubboDTO));
        BaseResult<Integer> baseResult = BaseResult.success();
        baseResult.setData(1);
        return baseResult;
    }
}
