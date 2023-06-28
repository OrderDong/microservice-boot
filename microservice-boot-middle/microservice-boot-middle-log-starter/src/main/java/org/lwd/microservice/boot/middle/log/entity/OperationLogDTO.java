package org.lwd.microservice.boot.middle.log.entity;

import com.alibaba.fastjson2.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;

/**
 * 操作日志对象
 *
 * @author virtiL
 * @version 1.0.0.0
 * @time: 2022/6/12 21:40
 * @see
 */
@Slf4j
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OperationLogDTO implements Serializable {

    /**
     * 链路追踪编号
     */
    @JSONField(ordinal = 0)
    @JsonProperty(index = 0)
    private String traceId;

    /**
     * 用户id
     */
    @JSONField(ordinal = 1)
    @JsonProperty(index = 1)
    private String userId;

    /**
     * 请求地址
     */
    @JSONField(ordinal = 2)
    @JsonProperty(index = 2)
    private String requestUrl;

    /**
     * Java 方法入参
     */
    @JSONField(ordinal = 3)
    @JsonProperty(index = 3)
    private String javaMethodParams;

    /**
     * 账号id
     */
    @JSONField(ordinal = 4)
    @JsonProperty(index = 4)
    private String userLoginId;

    /**
     * 所属业务模块
     */
    @JSONField(ordinal = 5)
    @JsonProperty(index = 5)
    private String projectName;

    /**
     * 所属业务模块
     */
    @JSONField(ordinal = 6)
    @JsonProperty(index = 6)
    private String busModule;

    /**
     * 日志标题
     */
    @JSONField(ordinal = 7)
    @JsonProperty(index = 7)
    private String title;

    /**
     * 日志类型
     */
    @JSONField(ordinal = 8)
    @JsonProperty(index = 8)
    private Integer type;

    /**
     * 操作内容
     */
    @JSONField(ordinal = 9)
    @JsonProperty(index = 9)
    private String content;


    /**
     * 请求方法名
     */
    @JSONField(ordinal = 10)
    @JsonProperty(index = 10)
    private String requestMethod;

    /**
     * 客户端ip
     */
    @JSONField(ordinal = 11)
    @JsonProperty(index = 11)
    private String clientIp;

    /**
     * UserAgent
     */
    @JSONField(ordinal = 12)
    @JsonProperty(index = 12)
    private String userAgent;

    /**
     * Java 方法名
     */
    @JSONField(ordinal = 13)
    @JsonProperty(index = 13)
    private String javaMethod;


    /**
     * 开始时间
     */
    @JSONField(ordinal = 14)
    @JsonProperty(index = 14)
    private String startTime;

    /**
     * 执行时长
     */
    @JSONField(ordinal = 15)
    @JsonProperty(index = 15)
    private Integer execTime;

    /**
     * 结果码
     */
    @JSONField(ordinal = 16)
    @JsonProperty(index = 16)
    private Integer resultCode;

    /**
     * 结果提示信息
     */
    @JSONField(ordinal = 17)
    @JsonProperty(index = 17)
    private String resultMsg;

    /**
     * 返回的结果数据
     */
    @JSONField(ordinal = 18)
    @JsonProperty(index = 18)
    private String resultData;

    /**
     * 团队id/商户id/多租户标识
     */
    private Integer tenantId;

    /**
     * 商户名称
     */
    private String tenantName;
    /**
     * 用户名
     */
    private String userName;
    /**
     * 用户手机号
     */
    private String userMobile;
    /**
     * 所属应用
     */
    private String appName;

}
