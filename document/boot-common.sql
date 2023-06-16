/**
 *租户数据源
 */
CREATE TABLE `t_tenant_data_source`
(
    `id`              int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '数据源id',
    `tenant_id`       int(11) unsigned NOT NULL COMMENT '租户id',
    `tenant_name`     varchar(100) DEFAULT NULL COMMENT '租户名',
    `microservice_db` tinyint(3) DEFAULT '1' COMMENT '微服务业务db 1:基础库 2:平台库 3:财务库...',
    `jdbc_type`       tinyint(1) DEFAULT '1' COMMENT 'db类型 1:mysql ',
    `jdbc_uri`        varchar(255) NOT NULL COMMENT '连接URL',
    `jdbc_username`   varchar(255) NOT NULL COMMENT '连接用户',
    `jdbc_password`   varchar(255) NOT NULL COMMENT '连接密码',
    `status`          tinyint(1) NOT NULL DEFAULT '1' COMMENT '类型状态 -1删除 0禁用 1启用 ',
    `deleted_tag`     tinyint(4) DEFAULT '0' COMMENT '删除标识 0:未删除 1:已删除',
    `create_by_id`    int(11) NOT NULL DEFAULT '0' COMMENT '创建人id',
    `create_time`     datetime     NOT NULL COMMENT '创建时间',
    `create_by_name`  varchar(40)  DEFAULT NULL COMMENT '创建人名',
    `update_by_id`    int(11) DEFAULT NULL COMMENT '更新人id',
    `update_time`     datetime     DEFAULT NULL COMMENT '更新人时间',
    `update_by_name`  varchar(40)  DEFAULT NULL COMMENT '更新人名',
    PRIMARY KEY (`id`),
    KEY               `idx_tenant_tenant_id` (`tenant_id`) COMMENT '租户id'
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COMMENT='租户数据源';

