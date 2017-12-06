## jww项目简介

- jww是Java语言的分布式系统架构。 使用SpringBoot整合开源框架。
- 使用Maven对项目进行模块化管理，提高项目的易开发性、扩展性。
- 系统包括5个子系统：公共功能、系统管理Service、系统管理Web、业务Service、业务Web。
- 公共功能：公共功能(AOP、缓存、基类、调度等等)、公共配置、工具类。
- 系统管理：包括用户管理、权限管理、数据字典、系统参数管理等等。
- 业务相关：您的业务开发。
- 可以无限的扩展子系统，子系统之间使用Dubbo或MQ进行通信。

## 主要功能
 1. 数据库：Druid数据库连接池，监控数据库访问性能，统计SQL的执行性能。 数据库密码加密，加密方式请查看PropertiesUtil，decryptProperties属性配置需要解密的key。 
 2. 持久层：mybatis持久化，使用MyBatis-Plus优化，减少sql开发量；aop切换数据库实现读写分离。Transtraction注解事务。
 3. MVC： 基于spring mvc注解,Rest风格Controller。Exception统一管理。
 4. 调度：Spring+quartz, 可以查询、修改周期、暂停、删除、新增、立即执行，查询执行记录等。
 5. 基于session的国际化提示信息，职责链模式的本地语言拦截器,Shiro登录、URL权限管理。会话管理，强制结束会话。
 6. 缓存和Session：注解redis缓存数据，Spring-session和redis实现分布式session同步，重启服务会话不丢失。
 7. 多系统交互：Dubbo,ActiveMQ多系统交互，ftp/sftp/fastdafs发送文件到独立服务器，使文件服务分离。
 8. 前后端分离：没有权限的文件只用nginx代理即可。
 9. 日志：log4j2打印日志，业务日志和调试日志分开打印。同时基于时间和文件大小分割日志文件。
 10. QQ、微信、新浪微博第三方登录。
 11. 工具类：excel导入导出，汉字转拼音，身份证号码验证，数字转大写人民币，FTP/SFTP/fastDFS上传下载，发送邮件，redis缓存，加密等等。

## 技术选型
    ● 核心框架：Spring Boot 1.5.8 + Dubbo 2.5.3
    ● 安全框架：Apache Shiro 1.4.0
    ● 持久层框架：MyBatis 3.4.5 + MyBatis-Plus 2.1.6
    ● 数据库连接池：Alibaba Druid 1.1.5
    ● 缓存框架：Redis.clients:jedis 2.8.2
    ● 队列框架：Apache ActiveMQ 5.14.5
    ● 会话管理：Spring-Session 1.3.1
    ● 日志管理：SLF4J 
    ● 前端框架：Layui
    ● 公用工具集：Hutool 3.2.1
    ● 支付组件：Egan pay-java-parent 2.0.4
    ● 代码简化：Lombok 1.16.18
    ● 序列化框架：Alibaba Fastjson 1.2.41
    ● HTTP客户端：Hutool-http 3.2.1
    

**项目结构** 
```
jww
├─jww-common 公共模块
│  ├─jww-common-core 核心组件
│  ├─jww-common-db 数据访问组件
│  ├─jww-common-http HTTP客户端
│  ├─jww-common-log 日志服务
│  ├─jww-common-mq 队列服务
│  ├─jww-common-pay 支付宝/微信支付组件
│  ├─jww-common-redis 缓存服务
│  └─jww-common-web WEB组件
│ 
├─jww-ui 页面模块
│  └─jww-ui-ump 统一管理平台页面
│ 
├─pay-ump 统一管理平台项目
│  ├─jww-ump-common 项目公共组件
│  ├─jww-ump-dao 项目公共组件
│  ├─jww-ump-generator 项目公共组件
│  ├─jww-ump-model 项目公共组件
│  ├─jww-ump-mq 项目公共组件
│  ├─jww-ump-rpc-api 项目公共组件
│  ├─jww-ump-rpc-service 项目公共组件
│  └─sqls 项目SQL语句
``` 


## 本地部署

  1. 环境要求
   * JDK1.7+
   * MySQL5.5+
   * Maven3.0+
   * Zookeeper3.3+
   * Redis3.0+
   * Nginx1.8+
   * Apache Activemq-5.0+ (可选)
 2.	执行SQL文件jww/jww-ump/sqls/jww.sql，初始化表和数据；
 3.	修改jww/jww-ump/jww-ump-rpc-service/src/main/resources/application-dev.yml，更新MySQL帐号密码，Redis的IP、端口和密码；
 4.	修改jww/jww-ump/jww-ump-server/src/main/resources/plication-dev.yml，更新Redis的IP、端口和密码；
 5.	修改Nginx/conf/ nginx.conf，指定静态页面地址：
 6.	启动MySQL, Zookeeper, Redis, Nginx;
 7.	IntelliJ IDEA中右键 >> Run jww/jww-ump/jww-ump-rpc-service/src/main/java/com/jww/ump/rpc/service/ServiceApplication.java;
 8.	IntelliJ IDEA中右键 >> Run jww/jww-ump/jww-ump-server/src/main/java/com/jww/ump/server/ ServerApplication.java;
 9.	访问地址：http://localhost 帐户密码：admin/admin

    
## 版权声明
jww使用 [Apache License 2.0][] 协议.

[Apache License 2.0]: http://www.apache.org/licenses/LICENSE-2.0
