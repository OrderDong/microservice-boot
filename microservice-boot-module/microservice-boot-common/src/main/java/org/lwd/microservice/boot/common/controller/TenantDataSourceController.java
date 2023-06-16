package org.lwd.microservice.boot.common.controller;


import org.lwd.microservice.boot.core.entity.*;
import org.lwd.microservice.boot.core.constant.*;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.lwd.microservice.boot.common.service.TenantDataSourceService;
import org.lwd.microservice.boot.common.entity.TenantDataSource;
import org.lwd.microservice.boot.common.entity.dto.TenantDataSourceDTO;
import org.lwd.microservice.boot.common.entity.vo.TenantDataSourceVO;
import org.lwd.microservice.boot.common.entity.convertor.TenantDataSourceConvertor;

import javax.validation.constraints.NotEmpty;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 租户数据源 前端控制器
 * </p>
 *
 * @author lwd
 * @since 2023-06-15
 */
@RestController
@RequestMapping("tenantDataSource")
public class TenantDataSourceController {
    @Autowired
    private TenantDataSourceService tenantDataSourceService;

    /**
     * 保存租户数据源
     *
     * @param dto 参数
     * @return 保存结果
     */
    @PostMapping("add")
    public WebResult<Integer> addTenantDataSource(@Validated @RequestBody TenantDataSourceDTO dto) {
        WebResult<Integer> webResult = WebResult.success();
        BaseResult<Integer> baseResult = this.tenantDataSourceService.saveTenantDataSource(dto);
        if(baseResult.isSuccess()){
            webResult.setData(baseResult.getData());
        }
        return webResult;
    }

    /**
    * 修改租户数据源
    *
    * @param dto 参数
    * @return 保存结果
    */
    @PostMapping("update")
    public WebResult<Integer> updateTenantDataSource(@Validated @RequestBody TenantDataSourceDTO dto) {
        WebResult<Integer> webResult = WebResult.success();
        BaseResult<Integer> baseResult = this.tenantDataSourceService.updateTenantDataSource(dto);
        if(baseResult.isSuccess()){
            webResult.setData(baseResult.getData());
        }
        return webResult;
    }

    /**
     * 根据主键查询租户数据源详情
     *
     * @param pk 主键
     * @return VO
     */
    @GetMapping("detail")
    public WebResult<TenantDataSourceVO> detailTenantDataSourceByPk(@Validated @NotEmpty String pk) {
        WebResult<TenantDataSourceVO> webResult = WebResult.success();
        BaseResult<TenantDataSourceDTO> baseResult = this.tenantDataSourceService.getTenantDataSourceByPk(pk);

        if(baseResult.isSuccess() && baseResult.getData() != null){
            webResult.setData(TenantDataSourceConvertor.INSTANCE.dtoToVO(baseResult.getData()));
        }
        return webResult;
    }

    /**
     * 根据主键查询租户数据源详情2
     *
     * @param pk 主键
     * @return VO
     */
    @GetMapping("detail2")
    public WebResult<TenantDataSourceVO> detailTenantDataSourceByPk2(@Validated @NotEmpty String pk) {
        WebResult<TenantDataSourceVO> webResult = WebResult.success();
        BaseResult<TenantDataSourceDTO> baseResult = this.tenantDataSourceService.getTenantDataSourceByPk(pk);

        if(baseResult.isSuccess() && baseResult.getData() != null){
            webResult.setData(TenantDataSourceConvertor.INSTANCE.dtoToVO(baseResult.getData()));
        }
        return webResult;
    }

    /**
     * 根据主键查询租户数据源详情3
     *
     * @param pk 主键
     * @return VO
     */
    @GetMapping("detail3")
    public WebResult<TenantDataSourceVO> detailTenantDataSourceByPk3(@Validated @NotEmpty String pk) {
        WebResult<TenantDataSourceVO> webResult = WebResult.success();
        BaseResult<TenantDataSourceDTO> baseResult = this.tenantDataSourceService.getTenantDataSourceByPk(pk);

        if(baseResult.isSuccess() && baseResult.getData() != null){
            webResult.setData(TenantDataSourceConvertor.INSTANCE.dtoToVO(baseResult.getData()));
        }
        return webResult;
    }

    /**
     * 根据主键删除租户数据源
     *
     * @param pk 主键
     * @return 删除结果
     */
    @PostMapping("/del")
    public WebResult<Boolean> delTenantDataSourceByPk(@Validated @NotEmpty @RequestBody String pk) {
        WebResult<Boolean> webResult = WebResult.success();
        BaseResult<Boolean> baseResult = this.tenantDataSourceService.deleteTenantDataSourceByPk(pk);

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
    public WebResult<IPage<TenantDataSourceVO>> page(TenantDataSourceDTO param, PageSearch request) {
        WebResult<IPage<TenantDataSourceVO>> webResult = WebResult.success();
        IPage<TenantDataSource> page = new Page<>(request.getPageNum(), request.getPageSize());
        BaseResult<IPage<TenantDataSourceDTO>> list = this.tenantDataSourceService.selectTenantDataSourcePageByDto(page, param);
        if(list.isSuccess()){
            IPage<TenantDataSourceVO> voPage = list.getData().convert(TenantDataSourceConvertor.INSTANCE::dtoToVO);
            webResult.setData(voPage);
        }
        return webResult;
    }
}
