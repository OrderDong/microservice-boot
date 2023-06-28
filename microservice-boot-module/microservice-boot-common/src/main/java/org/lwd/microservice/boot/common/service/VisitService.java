package org.lwd.microservice.boot.common.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.lwd.microservice.boot.core.entity.*;
import org.lwd.microservice.boot.common.entity.Visit;
import org.lwd.microservice.boot.common.entity.dto.VisitDTO;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 * <p>
 * 系统访问记录 服务接口
 * </p>
 *
 * @author lwd
 * @since 2023-06-28
 */
public interface VisitService extends IService<Visit> {

    /**
     * 保存系统访问记录
     *
     * @param dto 参数
     * @return 保存结果
     */
    public BaseResult<Integer> saveVisit(VisitDTO dto);

    /**
    * 修改系统访问记录
    *
    * @param dto 参数
    * @return 修改结果
    */
    public BaseResult<Integer> updateVisit(VisitDTO dto);

    /**
     * 根据主键查询DTO
     *
     * @param pk 主键
     * @return VO
     */
    public BaseResult<VisitDTO> getVisitByPk(String pk);


    /**
     * 根据主键删除
     *
     * @param pk 主键
     * @return 删除结果
     */
    public BaseResult<Boolean> deleteVisitByPk(String pk);

    /**
     * 支持分页的dto条件查询
     *
     * @param page  分页组件
     * @param param 查询参数
     * @return IPage
     */
    public BaseResult<IPage<VisitDTO>> selectVisitPageByDto(IPage<Visit> page, VisitDTO param);

    /**
    * 根据主键查询DTO列表-不分页
    *
    * @return DTO
    */
    public BaseResult<List<VisitDTO>> getVisitList();
}
