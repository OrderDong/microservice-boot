CREATE TABLE `t_friendly_link`
(
    `id`              int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '访问入口id',
    `merchant_id`     int(11) unsigned NOT NULL COMMENT '商户id',
    `merchant_name`   varchar(100) DEFAULT NULL COMMENT '商户名',
    `user_id`         int(11) unsigned DEFAULT NULL COMMENT '用户id',
    `employee_id`     int(11) unsigned NOT NULL COMMENT '员工id',
    `link_name`       varchar(30)  NOT NULL COMMENT '链接系统名称',
    `link_icon`       varchar(64)  NOT NULL COMMENT '链接系统图标',
    `link_url`        varchar(300) NOT NULL COMMENT '链接系统访问地址',
    `open_new_window` tinyint(1) NOT NULL DEFAULT '1' COMMENT '是否新窗口打开',
    `status`          tinyint(1) NOT NULL DEFAULT '1' COMMENT '类型状态 -1删除 0禁用 1启用 ',
    `deleted_tag`     tinyint(4) DEFAULT '0' COMMENT '删除标识 0:未删除 1:已删除',
    `create_by_id`    int(11) NOT NULL DEFAULT '0' COMMENT '创建人id',
    `create_time`     datetime     NOT NULL COMMENT '创建时间',
    `create_by_name`  varchar(40)  DEFAULT NULL COMMENT '创建人名',
    `update_by_id`    int(11) DEFAULT NULL COMMENT '更新人id',
    `update_time`     datetime     DEFAULT NULL COMMENT '更新人时间',
    `update_by_name`  varchar(40)  DEFAULT NULL COMMENT '更新人名',
    PRIMARY KEY (`id`),
    KEY               `idx_m_e` (`merchant_id`,`employee_id`) COMMENT '商户员工idx'
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COMMENT='员工连接';
