package org.lwd.microservice.boot.plat.controller;

import com.alibaba.fastjson2.JSON;
import lombok.extern.slf4j.Slf4j;
import org.lwd.microservice.boot.core.entity.BaseResult;
import org.lwd.microservice.boot.core.entity.WebResult;
import org.lwd.microservice.boot.middle.log.annotation.OperationLog;
import org.lwd.microservice.boot.middle.log.type.LogTypeEnum;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotEmpty;
import java.util.Map;

/**
 * @author weidong
 * @version V1.0.0
 * @description
 * @since 2023/4/7
 */
@Slf4j
@RestController
@RequestMapping("/test/")
@CrossOrigin
public class UserController {

    @GetMapping(value = "/tget")
    public String testInterGet(String name){
        log.info("----testInterGet---:{}",name);
        return JSON.toJSONString(name);
    }

    @OperationLog(busModule = "plat",title = "测试日志",context = "",logType = LogTypeEnum.SELECT,async = false)
    @PostMapping(value = "/tpost")
    public String testInterPost(@RequestBody Map<String,Object> param){
        log.info("----testInterPost---:{}", JSON.toJSONString(param));
        return JSON.toJSONString(param);
    }

    /**
     * 根据主键查询系统访问记录详情
     *
     * @param pk 主键
     * @return VO
     */
    @GetMapping("detail3")
    public WebResult<Boolean> detailVisitByPk(@Validated @NotEmpty String pk) {
        WebResult<Boolean> webResult = WebResult.success();
        webResult.setData(true);
        return webResult;
    }

}
