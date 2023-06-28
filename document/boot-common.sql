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
    `deleted_tag`     tinyint(1) DEFAULT '0' COMMENT '删除标识 0:未删除 1:已删除',
    `create_by_id`    int(11) NOT NULL DEFAULT '0' COMMENT '创建人id',
    `create_time`     datetime     NOT NULL COMMENT '创建时间',
    `create_by_name`  varchar(40)  DEFAULT NULL COMMENT '创建人名',
    `update_by_id`    int(11) DEFAULT NULL COMMENT '更新人id',
    `update_time`     datetime     DEFAULT NULL COMMENT '更新人时间',
    `update_by_name`  varchar(40)  DEFAULT NULL COMMENT '更新人名',
    PRIMARY KEY (`id`),
    KEY               `idx_tenant_tenant_id` (`tenant_id`) COMMENT '租户id'
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COMMENT='租户数据源';

-- master库
INSERT INTO `test`.`t_tenant_data_source` (`id`, `tenant_id`, `tenant_name`, `jdbc_type`, `microservice_db`, `jdbc_uri`,
                                           `jdbc_username`, `jdbc_password`, `status`, `deleted_tag`, `create_by_id`,
                                           `create_time`, `create_by_name`, `update_by_id`, `update_time`,
                                           `update_by_name`)
VALUES (1, 1, '租户1', 1, 1, 'jdbc:mysql://127.0.0.1:3306/test3', 'root', '123456', 1, 0, 1, '2023-06-13 16:34:36', 'lwd',
        NULL, NULL, NULL);
-- slave_1库
INSERT INTO `test2`.`t_tenant_data_source` (`id`, `tenant_id`, `tenant_name`, `jdbc_type`, `microservice_db`,
                                            `jdbc_uri`, `jdbc_username`, `jdbc_password`, `status`, `deleted_tag`,
                                            `create_by_id`, `create_time`, `create_by_name`, `update_by_id`,
                                            `update_time`, `update_by_name`)
VALUES (1, 2, '租户2', 1, 1, 'jdbc:mysql://127.0.0.1:3306/test3', 'root', '123456', 1, 0, 1, '2023-06-13 16:34:36', 'lwd',
        NULL, NULL, NULL);
-- test3动态库
INSERT INTO `test3`.`t_tenant_data_source` (`id`, `tenant_id`, `tenant_name`, `jdbc_type`, `microservice_db`,
                                            `jdbc_uri`, `jdbc_username`, `jdbc_password`, `status`, `deleted_tag`,
                                            `create_by_id`, `create_time`, `create_by_name`, `update_by_id`,
                                            `update_time`, `update_by_name`)
VALUES (1, 3, '租户3', 1, 1, 'jdbc:mysql://127.0.0.1:3306/test2', 'root', '123456', 1, 0, 1, '2023-06-13 16:34:36', 'lwd',
        NULL, NULL, NULL);


/**
  访问记录
 */

create table `visit`
(
    `id`                 int(11) not null auto_increment comment '访问id',
    `user_login_id`      varchar(50)   default '' comment '登录账号',
    `server_ip_address`  varchar(20)   default null comment '服务ip',
    `server_host_name`   varchar(255)  default null comment '服务名',
    `webapp_name`        varchar(60)   default null comment '业务模块',
    `initial_locale`     varchar(60)   default null comment '区域',
    `initial_request`    varchar(2000) default null comment '请求url',
    `initial_referrer`   varchar(2000) default null comment '请求关联',
    `initial_user_agent` varchar(255)  default null comment '浏览器信息',
    `enabled`            tinyint(1) default '0' comment '登录状态（0成功 1失败）',
    `msg`                varchar(255)  default '' comment '消息',
    `party_id`           int(11) default 0 comment '人员id',
    `role_type_id`       int(11) default 0 comment '人员角色',
    `from_date`          datetime      default null comment '访问时间',
    `thru_date`          datetime      default null comment '失效时间',
    `create_time`        datetime      default null comment '创建时间',
    `deleted_tag`        tinyint(1) default '0' comment '删除标识 0:未删除 1:已删除',
    primary key (`id`) using btree
) engine=innodb auto_increment=1 default charset=utf8mb4 row_format=dynamic comment='系统访问记录';
