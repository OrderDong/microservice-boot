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

## microservice-boot-1.0.1
- 增加mybatis-plus ORM中间件
- 增加generation 代码生成器
- 增加mysql连接，使用druid连接池



