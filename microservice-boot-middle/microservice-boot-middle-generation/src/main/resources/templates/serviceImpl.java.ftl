package ${package.ServiceImpl};

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.lwd.microservice.boot.core.entity.*;
import org.lwd.microservice.boot.core.constant.*;
import ${package.Entity}.${entity};
import ${package.Mapper}.${table.mapperName};
import ${package.Service}.${table.serviceName};
import ${package.Entity}.dto.${entity}DTO;
import ${package.Entity}.convertor.${entity}Convertor;
import ${superServiceImplClassPackage};
import org.springframework.stereotype.Service;

/**
 * <p>
 * ${table.comment!} 服务实现类
 * </p>
 *
 * @author ${author}
 * @since ${date}
 */
@Service
<#if kotlin>
open class ${table.serviceImplName} : ${superServiceImplClass}<${table.mapperName}, ${entity}>(), ${table.serviceName} {

}
<#else>
public class ${table.serviceImplName} extends ${superServiceImplClass}<${table.mapperName}, ${entity}> implements ${table.serviceName} {

  /**
    * 保存${entity}
    *
    * @param dto 参数
    * @return 保存结果
    */
    @Override
    public BaseResult<Integer> save${entity}(${entity}DTO dto) {
        BaseResult<Integer> baseResult = BaseResult.success();
        ${entity} ${entity?uncap_first} = ${entity}Convertor.INSTANCE.toEntity(dto);
        boolean result = this.save(${entity?uncap_first});
        if(result){
            baseResult.setData(${entity?uncap_first}.getId());
        }else {
            baseResult.setCode(HttpStatusEnum.REQUEST_FAIL.getCode());
            baseResult.setMessage(HttpStatusEnum.REQUEST_FAIL.getMessage());
        }
        return baseResult;
    }

    /**
    * 修改${entity}
    *
    * @param dto 参数
    * @return 保存结果
    */
    @Override
    public BaseResult<Integer> update${entity}(${entity}DTO dto) {
        BaseResult<Integer> baseResult = BaseResult.success();
        ${entity} ${entity?uncap_first} = ${entity}Convertor.INSTANCE.toEntity(dto);
        boolean result = this.updateById(${entity?uncap_first});
        if(result){
            baseResult.setData(${entity?uncap_first}.getId());
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
    public BaseResult<${entity}DTO> get${entity}ByPk(String pk) {
        BaseResult<${entity}DTO> baseResult = BaseResult.success();
        ${entity} domain = this.getById(pk);
        baseResult.setData(${entity}Convertor.INSTANCE.toDTO(domain));
        return baseResult;
     }

     /**
     * 根据主键删除
     *
     * @param pk 主键
     * @return 删除结果
     */
     @Override
    public BaseResult<Boolean>  delete${entity}ByPk(String pk) {
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
    public BaseResult<IPage<${entity}DTO>> select${entity}PageByDto(IPage<${entity}> page, ${entity}DTO param) {
        // todo 根据实际情况组装查询where条件
        BaseResult<IPage<${entity}DTO>> baseResult = BaseResult.success();
        QueryWrapper<${entity}> queryWrapper = Wrappers.query();
        IPage<${entity}> pageData = this.getBaseMapper().selectPage(page, queryWrapper);
        IPage<${entity}DTO> results= pageData.convert(${entity}Convertor.INSTANCE::toDTO);
        baseResult.setData(results);

        return baseResult;
    }
}
</#if>
