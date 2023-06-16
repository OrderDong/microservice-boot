package org.lwd.microservice.boot.common.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * <p>
 * 租户数据源
 * </p>
 *
 * @author lwd
 * @since 2023-06-15
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("t_tenant_data_source")
public class TenantDataSource implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 数据源id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 租户id
     */
    @TableField("tenant_id")
    private Integer tenantId;

    /**
     * 租户名
     */
    @TableField("tenant_name")
    private String tenantName;

    /**
     * db类型 1:mysql 
     */
    @TableField("jdbc_type")
    private Integer jdbcType;

    /**
     * 微服务业务db 1:基础库 2:平台库 3:财务库...
     */
    @TableField("microservice_db")
    private Integer microserviceDb;

    /**
     * 连接URL
     */
    @TableField("jdbc_uri")
    private String jdbcUri;

    /**
     * 连接用户
     */
    @TableField("jdbc_username")
    private String jdbcUsername;

    /**
     * 连接密码
     */
    @TableField("jdbc_password")
    private String jdbcPassword;

    /**
     * 类型状态 -1删除 0禁用 1启用 
     */
    @TableField("`status`")
    private Integer status;

    /**
     * 删除标识 0:未删除 1:已删除
     */
    @TableField("deleted_tag")
    @TableLogic
    private Integer deletedTag;

    /**
     * 创建人id
     */
    @TableField("create_by_id")
    private Integer createById;

    /**
     * 创建时间
     */
    @TableField("create_time")
    private Date createTime;

    /**
     * 创建人名
     */
    @TableField("create_by_name")
    private String createByName;

    /**
     * 更新人id
     */
    @TableField("update_by_id")
    private Integer updateById;

    /**
     * 更新人时间
     */
    @TableField("update_time")
    private Date updateTime;

    /**
     * 更新人名
     */
    @TableField("update_by_name")
    private String updateByName;


}
