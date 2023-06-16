package org.lwd.microservice.boot.common.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.lwd.microservice.boot.common.aop.DataSourceChangeProvider;
import org.lwd.microservice.boot.core.entity.*;
import org.lwd.microservice.boot.core.constant.*;
import org.lwd.microservice.boot.common.entity.TenantDataSource;
import org.lwd.microservice.boot.common.dao.TenantDataSourceMapper;
import org.lwd.microservice.boot.common.service.TenantDataSourceService;
import org.lwd.microservice.boot.common.entity.dto.TenantDataSourceDTO;
import org.lwd.microservice.boot.common.entity.convertor.TenantDataSourceConvertor;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 租户数据源 服务实现类
 * </p>
 *
 * @author lwd
 * @since 2023-06-15
 */
@Service
public class TenantDataSourceServiceImpl extends ServiceImpl<TenantDataSourceMapper, TenantDataSource> implements TenantDataSourceService {

    @Autowired
    DataSourceChangeProvider dataSourceChangeProvider;
  /**
    * 保存TenantDataSource
    *
    * @param dto 参数
    * @return 保存结果
    */
    @Override
    public BaseResult<Integer> saveTenantDataSource(TenantDataSourceDTO dto) {
        BaseResult<Integer> baseResult = BaseResult.success();
        TenantDataSource tenantDataSource = TenantDataSourceConvertor.INSTANCE.toEntity(dto);
        boolean result = this.save(tenantDataSource);
        if(result){
            baseResult.setData(tenantDataSource.getId());
            //TODO 微服务模块下，需要消息通知各个业务模块动态增加数据源
            //当前模块动态加载数据源
            dataSourceChangeProvider.addDataSource(TenantDataSourceConvertor.INSTANCE.toDTO(tenantDataSource));
        }else {
            baseResult.setCode(HttpStatusEnum.REQUEST_FAIL.getCode());
            baseResult.setMessage(HttpStatusEnum.REQUEST_FAIL.getMessage());
        }
        return baseResult;
    }

    /**
    * 修改TenantDataSource
    *
    * @param dto 参数
    * @return 保存结果
    */
    @Override
    public BaseResult<Integer> updateTenantDataSource(TenantDataSourceDTO dto) {
        BaseResult<Integer> baseResult = BaseResult.success();
        TenantDataSource tenantDataSource = TenantDataSourceConvertor.INSTANCE.toEntity(dto);
        boolean result = this.updateById(tenantDataSource);
        if(result){
            baseResult.setData(tenantDataSource.getId());
        }else {
            baseResult.setCode(HttpStatusEnum.REQUEST_FAIL.getCode());
            baseResult.setMessage(HttpStatusEnum.REQUEST_FAIL.getMessage());
        }
        return baseResult;
    }

    /**
     * 根据主键查询VO
     *
     * @param pk 主键
     * @return DTO
     */
    @DS("slave_1")
     @Override
    public BaseResult<TenantDataSourceDTO> getTenantDataSourceByPk(String pk) {
        BaseResult<TenantDataSourceDTO> baseResult = BaseResult.success();
        TenantDataSource domain = this.getById(pk);
        baseResult.setData(TenantDataSourceConvertor.INSTANCE.toDTO(domain));
        return baseResult;
     }

     /**
     * 根据主键删除
     *
     * @param pk 主键
     * @return 删除结果
     */
     @Override
    public BaseResult<Boolean>  deleteTenantDataSourceByPk(String pk) {
        BaseResult<Boolean> baseResult = BaseResult.success();
        boolean result = this.removeById(pk);
        //3.5.0后推荐使用
        //int retInt = this.getBaseMapper().deleteById(pk);
        if(result){
            baseResult.setData(result);
            //TODO 微服务模块下，需要消息通知各个业务模块动态移除数据源
            //当前模块动态移除数据源
            dataSourceChangeProvider.delDataSource(Integer.parseInt(pk));
        }else {
            baseResult.setCode(HttpStatusEnum.REQUEST_FAIL.getCode());
            baseResult.setMessage(HttpStatusEnum.REQUEST_FAIL.getMessage());
        }
         return baseResult;
     }

    /**
    * 支持分页的dto条件查询
    *
    * @param page  分页组件
    * @param param 查询参数
    * @return IPage
    */
    @Override
    public BaseResult<IPage<TenantDataSourceDTO>> selectTenantDataSourcePageByDto(IPage<TenantDataSource> page, TenantDataSourceDTO param) {
        // todo 根据实际情况组装查询where条件
        BaseResult<IPage<TenantDataSourceDTO>> baseResult = BaseResult.success();
        QueryWrapper<TenantDataSource> queryWrapper = Wrappers.query();
        IPage<TenantDataSource> pageData = this.getBaseMapper().selectPage(page, queryWrapper);
        IPage<TenantDataSourceDTO> results= pageData.convert(TenantDataSourceConvertor.INSTANCE::toDTO);
        baseResult.setData(results);

        return baseResult;
    }

    /**
    * 根据主键查询DTO列表-不分页
    *
    * @return DTO
    */
    @Override
    public BaseResult<List<TenantDataSourceDTO>> getTenantDataSourceList() {
        BaseResult<List<TenantDataSourceDTO>> baseResult = BaseResult.success();
        QueryWrapper<TenantDataSource> queryWrapper = Wrappers.query();
        List<TenantDataSource> tenantDataSourceList = this.getBaseMapper().selectList(queryWrapper);
        List<TenantDataSourceDTO> results= (List<TenantDataSourceDTO>)tenantDataSourceList.stream().map(TenantDataSourceConvertor.INSTANCE::toDTO).collect(Collectors.toList());
        baseResult.setData(results);
        return baseResult;
    }
}
