package org.lwd.microservice.boot.plat.entity;

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
 * 登录
 * </p>
 *
 * @author lwd
 * @since 2023-06-30
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("user_login")
public class UserLogin implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 登录账号
     */
    @TableField("user_login_id")
    private String userLoginId;

    /**
     * 登录密码
     */
    @TableField("current_password")
    private String currentPassword;

    /**
     * 提示
     */
    @TableField("password_hint")
    private String passwordHint;

    /**
     * 手机号
     */
    @TableField("mobile")
    private String mobile;

    /**
     * 系统账户 0否 1是 
     */
    @TableField("is_system")
    private Integer isSystem;

    /**
     * 状态  0禁用 1启用 
     */
    @TableField("enabled")
    private Integer enabled;

    /**
     * 需更换密码  0否 1是 
     */
    @TableField("require_password_change")
    private Integer requirePasswordChange;

    /**
     * 币种
     */
    @TableField("last_currency_uom")
    private String lastCurrencyUom;

    /**
     * 地区
     */
    @TableField("last_locale")
    private String lastLocale;

    /**
     * 时区
     */
    @TableField("last_time_zone")
    private String lastTimeZone;

    /**
     * 失效时间
     */
    @TableField("disabled_date_time")
    private Date disabledDateTime;

    /**
     * 外部授权ID
     */
    @TableField("external_auth_id")
    private String externalAuthId;

    /**
     * 删除标识 0:未删除 1:已删除
     */
    @TableField("deleted_tag")
    @TableLogic
    private Integer deletedTag;

    /**
     * 人员id
     */
    @TableField("party_id")
    private Integer partyId;

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
