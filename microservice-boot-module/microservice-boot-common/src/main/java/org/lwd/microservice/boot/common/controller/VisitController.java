package org.lwd.microservice.boot.common.controller;


import org.lwd.microservice.boot.core.entity.*;
import org.lwd.microservice.boot.core.constant.*;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.lwd.microservice.boot.common.service.VisitService;
import org.lwd.microservice.boot.common.entity.Visit;
import org.lwd.microservice.boot.common.entity.dto.VisitDTO;
import org.lwd.microservice.boot.common.entity.vo.VisitVO;
import org.lwd.microservice.boot.common.entity.convertor.VisitConvertor;

import javax.validation.constraints.NotEmpty;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 系统访问记录 前端控制器
 * </p>
 *
 * @author lwd
 * @since 2023-06-28
 */
@RestController
@RequestMapping("visit")
public class VisitController {
    @Autowired
    private VisitService visitService;

    /**
     * 保存系统访问记录
     *
     * @param dto 参数
     * @return 保存结果
     */
    @PostMapping("add")
    public WebResult<Integer> addVisit(@Validated @RequestBody VisitDTO dto) {
        WebResult<Integer> webResult = WebResult.success();
        BaseResult<Integer> baseResult = this.visitService.saveVisit(dto);
        if(baseResult.isSuccess()){
            webResult.setData(baseResult.getData());
        }
        return webResult;
    }

    /**
    * 修改系统访问记录
    *
    * @param dto 参数
    * @return 保存结果
    */
    @PostMapping("update")
    public WebResult<Integer> updateVisit(@Validated @RequestBody VisitDTO dto) {
        WebResult<Integer> webResult = WebResult.success();
        BaseResult<Integer> baseResult = this.visitService.updateVisit(dto);
        if(baseResult.isSuccess()){
            webResult.setData(baseResult.getData());
        }
        return webResult;
    }

    /**
     * 根据主键查询系统访问记录详情
     *
     * @param pk 主键
     * @return VO
     */
    @GetMapping("detail")
    public WebResult<VisitVO> detailVisitByPk(@Validated @NotEmpty String pk) {
        WebResult<VisitVO> webResult = WebResult.success();
        BaseResult<VisitDTO> baseResult = this.visitService.getVisitByPk(pk);

        if(baseResult.isSuccess() && baseResult.getData() != null){
            webResult.setData(VisitConvertor.INSTANCE.dtoToVO(baseResult.getData()));
        }
        return webResult;
    }

    /**
     * 根据主键删除系统访问记录
     *
     * @param pk 主键
     * @return 删除结果
     */
    @PostMapping("/del")
    public WebResult<Boolean> delVisitByPk(@Validated @NotEmpty @RequestBody String pk) {
        WebResult<Boolean> webResult = WebResult.success();
        BaseResult<Boolean> baseResult = this.visitService.deleteVisitByPk(pk);

        if(baseResult.isSuccess()){
            webResult.setData(baseResult.getData());
        }
        return webResult;
    }


    /**
     * 分页查询
     *
     * @return WebResult
     */
    @GetMapping("/page")
    public WebResult<IPage<VisitVO>> page(VisitDTO param, PageSearch request) {
        WebResult<IPage<VisitVO>> webResult = WebResult.success();
        IPage<Visit> page = new Page<>(request.getPageNum(), request.getPageSize());
        BaseResult<IPage<VisitDTO>> list = this.visitService.selectVisitPageByDto(page, param);
        if(list.isSuccess()){
            IPage<VisitVO> voPage = list.getData().convert(VisitConvertor.INSTANCE::dtoToVO);
            webResult.setData(voPage);
        }
        return webResult;
    }
}
