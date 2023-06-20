package org.lwd.microservice.boot.common.api.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 租户数据源DTO
 * </p>
 *
 * @author lwd
 * @since 2023-06-15
 */
@Getter
@Setter
public class TenantDataSourceDubboDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     *数据源id
     */
    private Integer id;

    /**
     *租户id
     */
    private Integer tenantId;

    /**
     *租户名
     */
    private String tenantName;

    /**
     *db类型 1:mysql
     */
    private Integer jdbcType;

    /**
     *微服务业务db 1:基础库 2:平台库 3:财务库...
     */
    private Integer microserviceDb;

    /**
     *连接URL
     */
    private String jdbcUri;

    /**
     *连接用户
     */
    private String jdbcUsername;

    /**
     *连接密码
     */
    private String jdbcPassword;

    /**
     *类型状态 -1删除 0禁用 1启用
     */
    private Integer status;

    /**
     *删除标识 0:未删除 1:已删除
     */
    private Integer deletedTag;

    /**
     *创建人id
     */
    private Integer createById;

    /**
     *创建时间
     */
    private Date createTime;

    /**
     *创建人名
     */
    private String createByName;

    /**
     *更新人id
     */
    private Integer updateById;

    /**
     *更新人时间
     */
    private Date updateTime;

    /**
     *更新人名
     */
    private String updateByName;
}
