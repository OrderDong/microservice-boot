package org.lwd.microservice.boot.common.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.lwd.microservice.boot.core.entity.*;
import org.lwd.microservice.boot.common.entity.TenantDataSource;
import org.lwd.microservice.boot.common.entity.dto.TenantDataSourceDTO;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 租户数据源 服务接口
 * </p>
 *
 * @author lwd
 * @since 2023-06-15
 */
public interface TenantDataSourceService extends IService<TenantDataSource> {

    /**
     * 保存租户数据源
     *
     * @param dto 参数
     * @return 保存结果
     */
    public BaseResult<Integer> saveTenantDataSource(TenantDataSourceDTO dto);

    /**
     * 修改租户数据源
     *
     * @param dto 参数
     * @return 修改结果
     */
    public BaseResult<Integer> updateTenantDataSource(TenantDataSourceDTO dto);

    /**
     * 根据主键查询DTO
     *
     * @param pk 主键
     * @return VO
     */
    public BaseResult<TenantDataSourceDTO> getTenantDataSourceByPk(String pk);


    /**
     * 根据主键删除
     *
     * @param pk 主键
     * @return 删除结果
     */
    public BaseResult<Boolean> deleteTenantDataSourceByPk(String pk);

    /**
     * 支持分页的dto条件查询
     *
     * @param page  分页组件
     * @param param 查询参数
     * @return IPage
     */
    public BaseResult<IPage<TenantDataSourceDTO>> selectTenantDataSourcePageByDto(IPage<TenantDataSource> page, TenantDataSourceDTO param);

    /**
     * 根据主键查询DTO列表-不分页
     *
     * @return DTO
     */
    public BaseResult<List<TenantDataSourceDTO>> getTenantDataSourceList();
}
