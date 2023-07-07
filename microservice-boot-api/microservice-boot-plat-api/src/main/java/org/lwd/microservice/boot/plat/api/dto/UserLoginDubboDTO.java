package org.lwd.microservice.boot.plat.api.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 登录dubbo DTO
 * </p>
 *
 * @author lwd
 * @since 2023-06-30
 */
@Getter
@Setter
public class UserLoginDubboDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     *id
     */
    private Integer id;

    /**
     *登录账号
     */
    private String userLoginId;

    /**
     *登录密码
     */
    private String currentPassword;

    /**
     *提示
     */
    private String passwordHint;

    /**
     *手机号
     */
    private String mobile;

    /**
     *系统账户 0否 1是
     */
    private Integer isSystem;

    /**
     *状态  0禁用 1启用
     */
    private Integer enabled;

    /**
     *需更换密码  0否 1是
     */
    private Integer requirePasswordChange;

    /**
     *币种
     */
    private String lastCurrencyUom;

    /**
     *地区
     */
    private String lastLocale;

    /**
     *时区
     */
    private String lastTimeZone;

    /**
     *失效时间
     */
    private Date disabledDateTime;

    /**
     *外部授权ID
     */
    private String externalAuthId;

    /**
     *删除标识 0:未删除 1:已删除
     */
    private Integer deletedTag;

    /**
     *人员id
     */
    private Integer partyId;

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
