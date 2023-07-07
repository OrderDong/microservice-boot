package org.lwd.microservice.boot.common.service.dubbo;

import com.alibaba.fastjson2.JSON;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboService;
import org.lwd.microservice.boot.common.api.dto.VisitDubboDTO;
import org.lwd.microservice.boot.common.api.dubbo.VisitDubboService;
import org.lwd.microservice.boot.common.entity.dto.VisitDTO;
import org.lwd.microservice.boot.common.service.VisitService;
import org.lwd.microservice.boot.core.constant.HttpStatusEnum;
import org.lwd.microservice.boot.core.entity.BaseResult;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * @author weidong
 * @version V1.0.0
 * @since 2023/6/28
 */
@Slf4j
@DubboService
public class VisitDubboServiceImpl implements VisitDubboService {

    @Autowired
    VisitService visitService;

    /**
     * 保存
     *
     * @return
     */
    @Override
    public BaseResult<Integer> saveVisitDubboService(VisitDubboDTO visitDubboDTO) {
        log.info("----i am do saveVisitDubboService-----:{}", JSON.toJSONString(visitDubboDTO));
        BaseResult<Integer> baseResult = BaseResult.success();
        VisitDTO visitDTO = new VisitDTO();
        BeanUtils.copyProperties(visitDubboDTO, visitDTO);
        BaseResult<Integer> result = visitService.saveVisit(visitDTO);
        if (result.isSuccess()) {
            baseResult.setData(baseResult.getData());
        } else {
            baseResult.setCode(HttpStatusEnum.REQUEST_FAIL.getCode());
            baseResult.setMessage("保存日志失败");
        }

        return baseResult;
    }
}
