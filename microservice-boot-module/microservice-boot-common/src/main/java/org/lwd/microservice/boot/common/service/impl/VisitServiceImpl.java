package org.lwd.microservice.boot.common.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.lwd.microservice.boot.core.entity.*;
import org.lwd.microservice.boot.core.constant.*;
import org.lwd.microservice.boot.common.entity.Visit;
import org.lwd.microservice.boot.common.dao.VisitMapper;
import org.lwd.microservice.boot.common.service.VisitService;
import org.lwd.microservice.boot.common.entity.dto.VisitDTO;
import org.lwd.microservice.boot.common.entity.convertor.VisitConvertor;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 系统访问记录 服务实现类
 * </p>
 *
 * @author lwd
 * @since 2023-06-28
 */
@Service
public class VisitServiceImpl extends ServiceImpl<VisitMapper, Visit> implements VisitService {

  /**
    * 保存Visit
    *
    * @param dto 参数
    * @return 保存结果
    */
    @Override
    public BaseResult<Integer> saveVisit(VisitDTO dto) {
        BaseResult<Integer> baseResult = BaseResult.success();
        Visit visit = VisitConvertor.INSTANCE.toEntity(dto);
        boolean result = this.save(visit);
        if(result){
            baseResult.setData(visit.getId());
        }else {
            baseResult.setCode(HttpStatusEnum.REQUEST_FAIL.getCode());
            baseResult.setMessage(HttpStatusEnum.REQUEST_FAIL.getMessage());
        }
        return baseResult;
    }

    /**
    * 修改Visit
    *
    * @param dto 参数
    * @return 保存结果
    */
    @Override
    public BaseResult<Integer> updateVisit(VisitDTO dto) {
        BaseResult<Integer> baseResult = BaseResult.success();
        Visit visit = VisitConvertor.INSTANCE.toEntity(dto);
        boolean result = this.updateById(visit);
        if(result){
            baseResult.setData(visit.getId());
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
     @Override
    public BaseResult<VisitDTO> getVisitByPk(String pk) {
        BaseResult<VisitDTO> baseResult = BaseResult.success();
        Visit domain = this.getById(pk);
        baseResult.setData(VisitConvertor.INSTANCE.toDTO(domain));
        return baseResult;
     }

     /**
     * 根据主键删除
     *
     * @param pk 主键
     * @return 删除结果
     */
     @Override
    public BaseResult<Boolean>  deleteVisitByPk(String pk) {
        BaseResult<Boolean> baseResult = BaseResult.success();
        boolean result = this.removeById(pk);
        //3.5.0后推荐使用
        //int retInt = this.getBaseMapper().deleteById(pk);
        if(result){
            baseResult.setData(result);
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
    public BaseResult<IPage<VisitDTO>> selectVisitPageByDto(IPage<Visit> page, VisitDTO param) {
        // todo 根据实际情况组装查询where条件
        BaseResult<IPage<VisitDTO>> baseResult = BaseResult.success();
        QueryWrapper<Visit> queryWrapper = Wrappers.query();
        IPage<Visit> pageData = this.getBaseMapper().selectPage(page, queryWrapper);
        IPage<VisitDTO> results= pageData.convert(VisitConvertor.INSTANCE::toDTO);
        baseResult.setData(results);

        return baseResult;
    }

    /**
    * 根据主键查询DTO列表-不分页
    *
    * @return DTO
    */
    @Override
    public BaseResult<List<VisitDTO>> getVisitList() {
        BaseResult<List<VisitDTO>> baseResult = BaseResult.success();
        QueryWrapper<Visit> queryWrapper = Wrappers.query();
        List<Visit> visitList = this.getBaseMapper().selectList(queryWrapper);
        List<VisitDTO> results= (List<VisitDTO>)visitList.stream().map(VisitConvertor.INSTANCE::toDTO).collect(Collectors.toList());
        baseResult.setData(results);
        return baseResult;
    }
}
