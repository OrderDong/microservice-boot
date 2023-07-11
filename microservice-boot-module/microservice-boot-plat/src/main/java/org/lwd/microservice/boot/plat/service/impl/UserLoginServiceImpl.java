package org.lwd.microservice.boot.plat.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.lwd.microservice.boot.core.entity.*;
import org.lwd.microservice.boot.core.constant.*;
import org.lwd.microservice.boot.plat.entity.UserLogin;
import org.lwd.microservice.boot.plat.dao.UserLoginMapper;
import org.lwd.microservice.boot.plat.service.UserLoginService;
import org.lwd.microservice.boot.plat.entity.dto.UserLoginDTO;
import org.lwd.microservice.boot.plat.entity.convertor.UserLoginConvertor;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 登录 服务实现类
 * </p>
 *
 * @author lwd
 * @since 2023-06-30
 */
@Service
public class UserLoginServiceImpl extends ServiceImpl<UserLoginMapper, UserLogin> implements UserLoginService {

  /**
    * 保存UserLogin
    *
    * @param dto 参数
    * @return 保存结果
    */
    @Override
    public BaseResult<Integer> saveUserLogin(UserLoginDTO dto) {
        BaseResult<Integer> baseResult = BaseResult.success();
        UserLogin userLogin = UserLoginConvertor.INSTANCE.toEntity(dto);
        boolean result = this.save(userLogin);
        if(result){
            baseResult.setData(userLogin.getId());
        }else {
            baseResult.setCode(HttpStatusEnum.REQUEST_FAIL.getCode());
            baseResult.setMessage(HttpStatusEnum.REQUEST_FAIL.getMessage());
        }
        return baseResult;
    }

    /**
    * 修改UserLogin
    *
    * @param dto 参数
    * @return 保存结果
    */
    @Override
    public BaseResult<Integer> updateUserLogin(UserLoginDTO dto) {
        BaseResult<Integer> baseResult = BaseResult.success();
        UserLogin userLogin = UserLoginConvertor.INSTANCE.toEntity(dto);
        boolean result = this.updateById(userLogin);
        if(result){
            baseResult.setData(userLogin.getId());
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
    public BaseResult<UserLoginDTO> getUserLoginByPk(String pk) {
        BaseResult<UserLoginDTO> baseResult = BaseResult.success();
        UserLogin domain = this.getById(pk);
        baseResult.setData(UserLoginConvertor.INSTANCE.toDTO(domain));
        return baseResult;
     }

     /**
     * 根据主键删除
     *
     * @param pk 主键
     * @return 删除结果
     */
     @Override
    public BaseResult<Boolean>  deleteUserLoginByPk(String pk) {
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
    public BaseResult<IPage<UserLoginDTO>> selectUserLoginPageByDto(IPage<UserLogin> page, UserLoginDTO param) {
        // todo 根据实际情况组装查询where条件
        BaseResult<IPage<UserLoginDTO>> baseResult = BaseResult.success();
        QueryWrapper<UserLogin> queryWrapper = Wrappers.query();
        IPage<UserLogin> pageData = this.getBaseMapper().selectPage(page, queryWrapper);
        IPage<UserLoginDTO> results= pageData.convert(UserLoginConvertor.INSTANCE::toDTO);
        baseResult.setData(results);

        return baseResult;
    }

    /**
    * 根据主键查询DTO列表-不分页
    *
    * @return DTO
    */
    @Override
    public BaseResult<List<UserLoginDTO>> getUserLoginList() {
        BaseResult<List<UserLoginDTO>> baseResult = BaseResult.success();
        QueryWrapper<UserLogin> queryWrapper = Wrappers.query();
        List<UserLogin> userLoginList = this.getBaseMapper().selectList(queryWrapper);
        List<UserLoginDTO> results= (List<UserLoginDTO>)userLoginList.stream().map(UserLoginConvertor.INSTANCE::toDTO).collect(Collectors.toList());
        baseResult.setData(results);
        return baseResult;
    }
}
