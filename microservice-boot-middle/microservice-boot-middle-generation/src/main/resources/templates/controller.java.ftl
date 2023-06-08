package ${package.Controller};


import org.lwd.microservice.boot.core.entity.*;
import org.lwd.microservice.boot.core.constant.*;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ${package.Service}.${table.serviceName};
import ${package.Entity}.${entity};
import ${package.Entity}.dto.${entity}DTO;
import ${package.Entity}.vo.${entity}VO;
import ${package.Entity}.convertor.${entity}Convertor;

import javax.validation.constraints.NotEmpty;
<#if restControllerStyle>
import org.springframework.web.bind.annotation.RestController;
<#else>
import org.springframework.stereotype.Controller;
</#if>
<#if superControllerClassPackage??>
import ${superControllerClassPackage};
</#if>

/**
 * <p>
 * ${table.comment!} 前端控制器
 * </p>
 *
 * @author ${author}
 * @since ${date}
 */
<#if restControllerStyle>
@RestController
<#else>
@Controller
</#if>
@RequestMapping("<#if package.ModuleName?? && package.ModuleName != "">/${package.ModuleName}<#else>${entity?uncap_first}</#if>")
<#if kotlin>
class ${table.controllerName}<#if superControllerClass??> : ${superControllerClass}()</#if>
<#else>
<#if superControllerClass??>
public class ${table.controllerName} extends ${superControllerClass} {
<#else>
public class ${table.controllerName} {
</#if>
    @Autowired
    private ${table.serviceName} ${entity?uncap_first}Service;

    /**
     * 保存${table.comment!}
     *
     * @param dto 参数
     * @return 保存结果
     */
    @PostMapping("add")
    public WebResult<Integer> add${entity}(@Validated @RequestBody ${entity}DTO dto) {
        WebResult<Integer> webResult = WebResult.success();
        BaseResult<Integer> baseResult = this.${entity?uncap_first}Service.save${entity}(dto);
        if(baseResult.isSuccess()){
            webResult.setData(baseResult.getData());
        }
        return webResult;
    }

    /**
    * 修改${table.comment!}
    *
    * @param dto 参数
    * @return 保存结果
    */
    @PostMapping("update")
    public WebResult<Integer> update${entity}(@Validated @RequestBody ${entity}DTO dto) {
        WebResult<Integer> webResult = WebResult.success();
        BaseResult<Integer> baseResult = this.${entity?uncap_first}Service.update${entity}(dto);
        if(baseResult.isSuccess()){
            webResult.setData(baseResult.getData());
        }
        return webResult;
    }

    /**
     * 根据主键查询${table.comment!}详情
     *
     * @param pk 主键
     * @return VO
     */
    @GetMapping("detail")
    public WebResult<${entity}VO> detail${entity}ByPk(@Validated @NotEmpty String pk) {
        WebResult<${entity}VO> webResult = WebResult.success();
        BaseResult<${entity}DTO> baseResult = this.${entity?uncap_first}Service.get${entity}ByPk(pk);

        if(baseResult.isSuccess() && baseResult.getData() != null){
            webResult.setData(${entity}Convertor.INSTANCE.dtoToVO(baseResult.getData()));
        }
        return webResult;
    }

    /**
     * 根据主键删除${table.comment!}
     *
     * @param pk 主键
     * @return 删除结果
     */
    @PostMapping("/del")
    public WebResult<Boolean> del${entity}ByPk(@Validated @NotEmpty @RequestBody String pk) {
        WebResult<Boolean> webResult = WebResult.success();
        BaseResult<Boolean> baseResult = this.${entity?uncap_first}Service.delete${entity}ByPk(pk);

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
    public WebResult<IPage<${entity}VO>> page(${entity}DTO param, PageSearch request) {
        WebResult<IPage<${entity}VO>> webResult = WebResult.success();
        IPage<${entity}> page = new Page<>(request.getPageNum(), request.getPageSize());
        BaseResult<IPage<${entity}DTO>> list = this.${entity?uncap_first}Service.select${entity}PageByDto(page, param);
        if(list.isSuccess()){
            IPage<${entity}VO> voPage = list.getData().convert(${entity}Convertor.INSTANCE::dtoToVO);
            webResult.setData(voPage);
        }
        return webResult;
    }
}
</#if>
