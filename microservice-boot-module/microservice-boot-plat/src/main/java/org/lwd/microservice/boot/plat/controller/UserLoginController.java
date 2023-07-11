package org.lwd.microservice.boot.plat.controller;


import lombok.extern.slf4j.Slf4j;
import org.lwd.microservice.boot.core.entity.*;
import org.lwd.microservice.boot.core.constant.*;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.lwd.microservice.boot.plat.service.UserLoginSeataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.lwd.microservice.boot.plat.service.UserLoginService;
import org.lwd.microservice.boot.plat.entity.UserLogin;
import org.lwd.microservice.boot.plat.entity.dto.UserLoginDTO;
import org.lwd.microservice.boot.plat.entity.vo.UserLoginVO;
import org.lwd.microservice.boot.plat.entity.convertor.UserLoginConvertor;

import javax.validation.constraints.NotEmpty;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 登录 前端控制器
 * </p>
 *
 * @author lwd
 * @since 2023-06-30
 */
@Slf4j
@RestController
@RequestMapping("userLogin")
public class UserLoginController {
    @Autowired
    private UserLoginService userLoginService;
    @Autowired
    private UserLoginSeataService userLoginSeataService;

    /**
     * 保存登录
     *
     * @param dto 参数
     * @return 保存结果
     */
    @PostMapping("add")
    public WebResult<Integer> addUserLogin(@Validated @RequestBody UserLoginDTO dto) {
        WebResult<Integer> webResult = WebResult.success();
        BaseResult<Integer> baseResult = this.userLoginService.saveUserLogin(dto);
        if(baseResult.isSuccess()){
            webResult.setData(baseResult.getData());
        }
        return webResult;
    }

    /**
    * 修改登录
    *
    * @param dto 参数
    * @return 保存结果
    */
    @PostMapping("update")
    public WebResult<Integer> updateUserLogin(@Validated @RequestBody UserLoginDTO dto) {
        WebResult<Integer> webResult = WebResult.success();
        BaseResult<Integer> baseResult = this.userLoginService.updateUserLogin(dto);
        if(baseResult.isSuccess()){
            webResult.setData(baseResult.getData());
        }
        return webResult;
    }

    /**
     * 根据主键查询登录详情
     *
     * @param pk 主键
     * @return VO
     */
    @GetMapping("detail")
    public WebResult<UserLoginVO> detailUserLoginByPk(@Validated @NotEmpty String pk) {
        WebResult<UserLoginVO> webResult = WebResult.success();
        BaseResult<UserLoginDTO> baseResult = this.userLoginService.getUserLoginByPk(pk);

        if(baseResult.isSuccess() && baseResult.getData() != null){
            webResult.setData(UserLoginConvertor.INSTANCE.dtoToVO(baseResult.getData()));
        }
        return webResult;
    }

    /**
     * 根据主键删除登录
     *
     * @param pk 主键
     * @return 删除结果
     */
    @PostMapping("/del")
    public WebResult<Boolean> delUserLoginByPk(@Validated @NotEmpty @RequestBody String pk) {
        WebResult<Boolean> webResult = WebResult.success();
        BaseResult<Boolean> baseResult = this.userLoginService.deleteUserLoginByPk(pk);

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
    public WebResult<IPage<UserLoginVO>> page(UserLoginDTO param, PageSearch request) {
        WebResult<IPage<UserLoginVO>> webResult = WebResult.success();
        IPage<UserLogin> page = new Page<>(request.getPageNum(), request.getPageSize());
        BaseResult<IPage<UserLoginDTO>> list = this.userLoginService.selectUserLoginPageByDto(page, param);
        if(list.isSuccess()){
            IPage<UserLoginVO> voPage = list.getData().convert(UserLoginConvertor.INSTANCE::dtoToVO);
            webResult.setData(voPage);
        }
        return webResult;
    }

    /**
     * seata保存登录
     *
     * @param dto 参数
     * @return 保存结果
     */
    @PostMapping("seataAdd")
    public WebResult<Integer> seataAdd(@Validated @RequestBody UserLoginDTO dto) {
        WebResult<Integer> webResult = WebResult.success();
        try {
            BaseResult<Integer> baseResult = this.userLoginSeataService.saveUserLoginSeata(dto);
            if (baseResult.isSuccess()) {
                webResult.setData(baseResult.getData());
            }else {
                webResult.setCode(HttpStatusEnum.REQUEST_FAIL.getCode());
            }
        }catch (Exception e){
            log.error("seataAdd error:{}",e.getMessage());
        }
        return webResult;
    }
}
