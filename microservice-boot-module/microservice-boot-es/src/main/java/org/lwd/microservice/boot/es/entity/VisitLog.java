package org.lwd.microservice.boot.es.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 系统访问日志
 * </p>
 *
 * @author lwd
 * @since 2023-07-26
 */
@Getter
@Setter
@Document(indexName = "visit_log")
public class VisitLog implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     *访问id
     */
    @Id
    private Integer id;

    /**
     * 不同业务表明
     */
    private String tableName;
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
     *请求url
     */
    private String initialRequest;

    /**
     *消息内容
     */
    private String msgContent;

    /**
     *创建时间
     */
    private Date createTime;

    /**
     * spring data elasticsearch 自动加入?
     *
     */
    private String _class;
}
