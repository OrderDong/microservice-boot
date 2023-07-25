# 1. 产品介绍和目标
* 分布式、SOA、微服务技术应用
* 基于平台整合常用技术
* 基于平台设计技术应用的业务场景
* 设计业务平台
* 高性能、高并发、高可用保障
* 各项指标监控
* 压力测试和调优
* 大数据平台
* 其他

PS：同样会有spring cloud alibaba整合相关技术、业务、性能、优化、大数据等

# 2. 架构设计
1、基础微服务(分布式)架构

基础架构，整体基于springboot做分布式、SOA、微服务的基础技术平台

2、基础技术版本

springboot2.X + dubbo3.1.0 + nacos2.1

3、架构图
有时间更新。。。。

# 3. 模块图谱

# 4. 版本(release)说明
更新迭代版本分支说明
## microservice-boot-1.0.0
- 基础微服务(分布式)(SOA)架构搭建：springboot2.X + dubbo3.1.0 + nacos2.1整合
- 使用场景：springboot高版本中间件整合，配置中心和注册中心使用nacos高版本，dubbo使用3.x版本
- 其他说明：消费项目中有测试TestUserController.http请求
- 主要用途：springboot2.3.1作为基础脚手架，整合主流dubbo3.1.0（服务治理，微服务框架） + nacos2.1（主流注册中心和配置中心）

## microservice-boot-1.0.1-generation
- 增加mybatis-plus ORM中间件
- 增加generation 代码生成器
- 增加mysql连接，使用druid连接池

## microservice-boot-1.0.2-dynamicDataSource
- 1、多租户数据源（DB）管理 
- 2、多租户数据源动态切换 
```
基于dynamic-DataSource 3.5.1版本 
静态yaml配置切换
动态DB数据源切换
    
```
- 3、增加logback日志 
- 4、增加动态数据源springboot microservice-boot-middle-ds-starter动态数据源
- 5、数据源starter中使用Dubbo获取DB数据源，实现多模块调用

## microservice-boot-1.0.3-logAndPlat
- 0、springboot-log-starter封装
- 1、增加日志统一管理
- 2、统一API层日志注释配置和日志输出
- 3、统一服务和DUBBO服务日志处理
- 4、各业务模块个性化日志处理
- 5、异常日志处理

## microservice-boot-1.0.4-seata
- 1、集成分布式/微服务事务处理
- 2、使用注释方式处理事务
- 3、默认使用AT注释方式

## microservice-boot-1.0.5-sentinel
- 1、springboot集成sentinel流控中间件
- 2、sentinel流控、熔断、降级、系统安全等规则验证
- 3、制作springboot sentinel自动yaml文件配置
- 4、springboot sentinel nacos 配置和starter制作
- 5、springcloudalibaba sentinel 配置介绍





















