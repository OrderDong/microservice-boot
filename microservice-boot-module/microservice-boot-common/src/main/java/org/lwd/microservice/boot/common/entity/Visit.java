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
 * 系统访问记录
 * </p>
 *
 * @author lwd
 * @since 2023-06-28
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("visit")
public class Visit implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 访问id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 登录账号
     */
    @TableField("user_login_id")
    private String userLoginId;

    /**
     * 服务ip
     */
    @TableField("server_ip_address")
    private String serverIpAddress;

    /**
     * 服务名
     */
    @TableField("server_host_name")
    private String serverHostName;

    /**
     * 业务模块
     */
    @TableField("webapp_name")
    private String webappName;

    /**
     * 区域
     */
    @TableField("initial_locale")
    private String initialLocale;

    /**
     * 请求url
     */
    @TableField("initial_request")
    private String initialRequest;

    /**
     * 请求关联
     */
    @TableField("initial_referrer")
    private String initialReferrer;

    /**
     * 浏览器信息
     */
    @TableField("initial_user_agent")
    private String initialUserAgent;

    /**
     * 登录状态（0成功 1失败）
     */
    @TableField("enabled")
    private Integer enabled;

    /**
     * 消息
     */
    @TableField("msg")
    private String msg;

    /**
     * 人员id
     */
    @TableField("party_id")
    private Integer partyId;

    /**
     * 人员角色
     */
    @TableField("role_type_id")
    private Integer roleTypeId;

    /**
     * 访问时间
     */
    @TableField("from_date")
    private Date fromDate;

    /**
     * 失效时间
     */
    @TableField("thru_date")
    private Date thruDate;

    /**
     * 创建时间
     */
    @TableField("create_time")
    private Date createTime;

    /**
     * 删除标识 0:未删除 1:已删除
     */
    @TableField("deleted_tag")
    @TableLogic
    private Integer deletedTag;


}
