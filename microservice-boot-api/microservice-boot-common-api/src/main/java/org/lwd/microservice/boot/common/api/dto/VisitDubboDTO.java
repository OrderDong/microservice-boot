package org.lwd.microservice.boot.common.api.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 系统访问记录DTO
 * </p>
 *
 * @author lwd
 * @since 2023-06-28
 */
@Getter
@Setter
public class VisitDubboDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     *访问id
     */
    private Integer id;

    /**
     *登录账号
     */
    private String userLoginId;

    /**
     *服务ip
     */
    private String serverIpAddress;

    /**
     *服务名
     */
    private String serverHostName;

    /**
     *业务模块
     */
    private String webappName;

    /**
     *区域
     */
    private String initialLocale;

    /**
     *请求url
     */
    private String initialRequest;

    /**
     *请求关联
     */
    private String initialReferrer;

    /**
     *浏览器信息
     */
    private String initialUserAgent;

    /**
     *登录状态（0成功 1失败）
     */
    private Integer enabled;

    /**
     *消息
     */
    private String msg;

    /**
     *人员id
     */
    private Integer partyId;

    /**
     *人员角色
     */
    private Integer roleTypeId;

    /**
     *访问时间
     */
    private Date fromDate;

    /**
     *失效时间
     */
    private Date thruDate;

    /**
     *创建时间
     */
    private Date createTime;

    /**
     *删除标识 0:未删除 1:已删除
     */
    private Integer deletedTag;
}
