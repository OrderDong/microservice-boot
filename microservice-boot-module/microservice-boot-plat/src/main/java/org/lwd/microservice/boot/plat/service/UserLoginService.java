package org.lwd.microservice.boot.plat.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.lwd.microservice.boot.core.entity.*;
import org.lwd.microservice.boot.plat.entity.UserLogin;
import org.lwd.microservice.boot.plat.entity.dto.UserLoginDTO;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 * <p>
 * 登录 服务接口
 * </p>
 *
 * @author lwd
 * @since 2023-06-30
 */
public interface UserLoginService extends IService<UserLogin> {

    /**
     * 保存登录
     *
     * @param dto 参数
     * @return 保存结果
     */
    public BaseResult<Integer> saveUserLogin(UserLoginDTO dto);

    /**
    * 修改登录
    *
    * @param dto 参数
    * @return 修改结果
    */
    public BaseResult<Integer> updateUserLogin(UserLoginDTO dto);

    /**
     * 根据主键查询DTO
     *
     * @param pk 主键
     * @return VO
     */
    public BaseResult<UserLoginDTO> getUserLoginByPk(String pk);


    /**
     * 根据主键删除
     *
     * @param pk 主键
     * @return 删除结果
     */
    public BaseResult<Boolean> deleteUserLoginByPk(String pk);

    /**
     * 支持分页的dto条件查询
     *
     * @param page  分页组件
     * @param param 查询参数
     * @return IPage
     */
    public BaseResult<IPage<UserLoginDTO>> selectUserLoginPageByDto(IPage<UserLogin> page, UserLoginDTO param);

    /**
    * 根据主键查询DTO列表-不分页
    *
    * @return DTO
    */
    public BaseResult<List<UserLoginDTO>> getUserLoginList();
}
