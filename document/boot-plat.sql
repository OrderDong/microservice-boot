/*
 * 登录
*/
create table `user_login`
(
    `id`                      int(11) not null auto_increment comment 'id',
    `user_login_id`           varchar(100) not null comment '登录账号',
    `current_password`        varchar(100) default null comment '登录密码',
    `password_hint`           varchar(100) default null comment '提示',
    `mobile`                  varchar(20)  default null comment '手机号',
    `is_system`               tinyint(1) default '0' comment '系统账户 0否 1是 ',
    `enabled`                 tinyint(1) default '1' comment '状态  0禁用 1启用 ',
    `require_password_change` tinyint(1) default '0' comment '需更换密码  0否 1是 ',
    `last_currency_uom`       varchar(20)  default 'CNY' comment '币种',
    `last_locale`             varchar(10)  default 'zh_CN' comment '地区',
    `last_time_zone`          varchar(60)  default 'Asia/ShangHai' comment '时区',
    `disabled_date_time`      datetime     default null comment '失效时间',
    `external_auth_id`        varchar(250) default '0' comment '外部授权ID',
    `deleted_tag`             tinyint(1) default '0' comment '删除标识 0:未删除 1:已删除',
    `party_id`           int(11) default '0' comment '人员id',
    `create_by_id`            int(11) default '0' comment '创建人id',
    `create_time`             datetime     not null comment '创建时间',
    `create_by_name`          varchar(40)  default null comment '创建人名',
    `update_by_id`            int(11) default null comment '更新人id',
    `update_time`             datetime     default null comment '更新人时间',
    `update_by_name`          varchar(40)  default null comment '更新人名',
    primary key (`id`),
    unique key `user_login_user_login_id` (`user_login_id`),
    key                       `user_login_party_user_id` (`party_user_id`)
) engine=innodb default charset=utf8 comment '登录';


