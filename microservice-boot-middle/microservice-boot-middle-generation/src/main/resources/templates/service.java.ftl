package ${package.Service};

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.lwd.microservice.boot.core.entity.*;
import ${package.Entity}.${entity};
import ${package.Entity}.dto.${entity}DTO;
import ${superServiceClassPackage};

/**
 * <p>
 * ${table.comment!} 服务接口
 * </p>
 *
 * @author ${author}
 * @since ${date}
 */
<#if kotlin>
interface ${table.serviceName} : ${superServiceClass}<${entity}>
<#else>
public interface ${table.serviceName} extends ${superServiceClass}<${entity}> {

    /**
     * 保存${table.comment}
     *
     * @param dto 参数
     * @return 保存结果
     */
    public BaseResult<Integer> save${entity}(${entity}DTO dto);

    /**
    * 修改${table.comment}
    *
    * @param dto 参数
    * @return 修改结果
    */
    public BaseResult<Integer> update${entity}(${entity}DTO dto);

    /**
     * 根据主键查询DTO
     *
     * @param pk 主键
     * @return VO
     */
    public BaseResult<${entity}DTO> get${entity}ByPk(String pk);


    /**
     * 根据主键删除
     *
     * @param pk 主键
     * @return 删除结果
     */
    public BaseResult<Boolean> delete${entity}ByPk(String pk);

    /**
     * 支持分页的dto条件查询
     *
     * @param page  分页组件
     * @param param 查询参数
     * @return IPage
     */
    public BaseResult<IPage<${entity}DTO>> select${entity}PageByDto(IPage<${entity}> page, ${entity}DTO param);
}
</#if>
