# 介绍
基于mybatis-plus代码生成工具
后续会不断完善
# 规划
后续会基于此功能搞低代码平台，会有前端VUE
# mybatis-plus介绍&特性
	• 无侵入：只做增强不做改变，引入它不会对现有工程产生影响，如丝般顺滑
	• 损耗小：启动即会自动注入基本 CURD，性能基本无损耗，直接面向对象操作
	• 强大的 CRUD 操作：内置通用 Mapper、通用 Service，仅仅通过少量配置即可实现单表大部分 CRUD 操作，更有强大的条件构造器，满足各类使用需求
	• 支持 Lambda 形式调用：通过 Lambda 表达式，方便的编写各类查询条件，无需再担心字段写错
	• 支持主键自动生成：支持多达 4 种主键策略（内含分布式唯一 ID 生成器 - Sequence），可自由配置，完美解决主键问题
	• 支持 ActiveRecord 模式：支持 ActiveRecord 形式调用，实体类只需继承 Model 类即可进行强大的 CRUD 操作
	• 支持自定义全局通用操作：支持全局通用方法注入（ Write once, use anywhere ）
	• 内置代码生成器：采用代码或者 Maven 插件可快速生成 Mapper 、 Model 、 Service 、 Controller 层代码，支持模板引擎，更有超多自定义配置等您来使用
	• 内置分页插件：基于 MyBatis 物理分页，开发者无需关心具体操作，配置好插件之后，写分页等同于普通 List 查询
	• 分页插件支持多种数据库：支持 MySQL、MariaDB、Oracle、DB2、H2、HSQL、SQLite、Postgre、SQLServer 等多种数据库
	• 内置性能分析插件：可输出 SQL 语句以及其执行时间，建议开发测试时启用该功能，能快速揪出慢查询
    • 内置全局拦截插件：提供全表 delete 、 update 操作智能分析阻断，也可自定义拦截规则，预防误操作

# 代码生成
* 自定义模版
* 生成内容
controller/entity/dto/vo/convertor/service/serviceImpl/dao/mapper

# 功能说明
基于模版生成，模版在templates下
restful接口和基础功能实现
service服务实现和基础功能实现
entity/dto/vo/convertor，不同传输对象和转化
使用分页插件
使用逻辑删除标识

# 使用说明
CodeGeneration，直接执行即可
如需要做个性化配置，则自己改即可

# 测试类
测试sql在resources/sql/test.sql
查看TestGeneration.http实现

# 升级说明
1、增加服务不分页列表功能


