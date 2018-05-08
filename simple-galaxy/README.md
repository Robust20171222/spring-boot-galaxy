# Spring Boot学习笔记

## 一、[Spring Boot如何测试打包部署](http://www.ityouknow.com/springboot/2017/05/09/springboot-deploy.html)

## 二、[Spring Boot属性配置文件详解——多环境配置](http://blog.didispace.com/springbootproperties/)

## 三、[Spring Boot内嵌服务器Tomcat剖析](http://blog.720ui.com/2016/springboot_05_server_tomcat/)

Spring Boot 默认使用的是 Tomcat 作为内嵌的服务器。所以，我们搭建一个 Web 工程将会变得非常的简单，只需要一个 jar 包即可运行。此外，我们还可以对内嵌的 Tomcat 进行一些定制，例如端口、最大线程数、编码、 SSL 等。如果，我们还是希望通过 war 包的方式，部署到外部的 Tomcat 服务器上， Spring Boot 也是支持的，不过需要一些额外的配置，这个配置过程也只需要几个简单的步骤即可实现。

## 四、[Spring Boot 发布与部署-开发热部署](http://blog.720ui.com/2017/springboot_08_deploy_autoload/)

Spring Boot 支持页面与类文件的热部署。

spring-boot-devtools 实现热部署：spring-boot-devtools 最重要的功能就是热部署。它会监听 classpath 下的文件变动，并且会立即重启应用。

## 五、[Spring Boot 应用监控篇 - HTTP 应用监控](http://blog.720ui.com/2017/springboot_09_actuator_http/)

## 六、[Spring Boot构建RESTful API与单元测试](http://blog.didispace.com/springbootrestfulapi/)

- @Controller：修饰class，用来创建处理http请求的对象
- @RestController：Spring4之后加入的注解，原来在@Controller中返回json需要@ResponseBody来配合，如果直接用@RestController替代@Controller就不需要再配置@ResponseBody，默认返回json格式。
- @RequestMapping：配置url映射

## 七、[Spring Boot 1.5.x新特性：动态修改日志级别](http://blog.didispace.com/spring-boot-1-5-x-feature-1/)

- 通过引入spring-boot-starter-actuator使动态修改日志级别功能生效
- 修改的日志级别会保存在内存里，重启消失

## 八、[How to use Log4j 2 with Spring Boot](https://www.callicoder.com/spring-boot-log4j-2-example/)

## 九、单元测试

[Spring Boot+Mock+Junit单元测试](https://dzone.com/articles/spring-boot-unit-testing-and-mocking-with-mockito)

生成单元测试覆盖率报告：[学习Maven之Cobertura Maven Plugin](http://www.cnblogs.com/qyf404/p/5040593.html)

[浅谈代码覆盖率](https://tech.youzan.com/code-coverage/)

## 十、[Spring Boot中的事务管理](http://blog.didispace.com/springboottransactional/)

[Spring Boot教程第9篇：声明式事务](http://www.fangzhipeng.com/2017/05/05/SpringBoot%E9%9D%9E%E5%AE%98%E6%96%B9%E6%95%99%E7%A8%8B-%E7%AC%AC%E4%B8%83%E7%AF%87-springboot%E5%BC%80%E5%90%AF%E5%A3%B0%E6%98%8E%E5%BC%8F%E4%BA%8B%E5%8A%A1/)

[Spring transaction propagation tutorial](http://www.byteslounge.com/tutorials/spring-transaction-propagation-tutorial)

**五个事务隔离级别：**

隔离级别是指若干个并发的事务之间的隔离程度，与我们开发时候主要相关的场景包括：脏读取、重复读、幻读。

```
public enum Isolation {
    DEFAULT(-1),
    READ_UNCOMMITTED(1),
    READ_COMMITTED(2),
    REPEATABLE_READ(4),
    SERIALIZABLE(8);
}
```

- `DEFAULT`：这是默认值，表示使用底层数据库的默认隔离级别。对大部分数据库而言，通常这值就是：READ_COMMITTED。
- `READ_UNCOMMITTED`：该隔离级别表示一个事务可以读取另一个事务修改但还没有提交的数据。该级别不能防止脏读和不可重复读，因此很少使用该隔离级别。
- `READ_COMMITTED`：该隔离级别表示一个事务只能读取另一个事务已经提交的数据。该级别可以防止脏读，这也是大多数情况下的推荐值。
- `REPEATABLE_READ`：该隔离级别表示一个事务在整个过程中可以多次重复执行某个查询，并且每次返回的记录都相同。即使在多次查询之间有新增的数据满足该查询，这些新增的记录也会被忽略。该级别可以防止脏读和不可重复读。
- `SERIALIZABLE`：所有的事务依次逐个执行，这样事务之间就完全不可能产生干扰，也就是说，该级别可以防止脏读、不可重复读以及幻读。但是这将严重影响程序的性能。通常情况下也不会用到该级别。

**七个事务传播行为：**

所谓事务的传播行为是指，如果在开始当前事务之前，一个事务上下文已经存在，此时有若干选项可以指定一个事务性方法的执行行为。

```
public enum Propagation {
    REQUIRED(0),
    SUPPORTS(1),
    MANDATORY(2),
    REQUIRES_NEW(3),
    NOT_SUPPORTED(4),
    NEVER(5),
    NESTED(6);
}
```

* `REQUIRED`：如果当前存在事务，则加入该事务；如果当前没有事务，则创建一个新的事务。
* `REQUIRES_NEW`：创建一个新的事务，如果当前存在事务，则把当前事务挂起。
* `SUPPORTS`：如果当前存在事务，则加入该事务；如果当前没有事务，则以非事务的方式继续运行。
* `NOT_SUPPORTED`：以非事务方式运行，如果当前存在事务，则把当前事务挂起。
* `MANDATORY`：如果当前存在事务，则加入该事务；如果当前没有事务，则抛出异常。
* `NEVER`：以非事务方式运行，如果当前存在事务，则抛出异常。
* `NESTED`：如果当前存在事务，则创建一个事务作为当前事务的嵌套事务来运行；如果当前没有事务，则该取值等价于REQUIRED。

## 十一、Spring Boot ElasticSearch整合

整合：[Spring Boot + Spring Data + Elasticsearch example](https://www.mkyong.com/spring-boot/spring-boot-spring-data-elasticsearch-example/)

[5.6 Transport Client](https://www.elastic.co/guide/en/elasticsearch/client/java-api/5.6/transport-client.html#transport-client)

问题解决：

**1、None of the configured nodes are available：**

[使用elasticsearch的时候出现错误（一）](http://blog.csdn.net/Zebra_916/article/details/78653283)

[elasticsearch报错:None of the configured nodes are available: []](http://blog.csdn.net/lu_wei_wei/article/details/51263133)

## 十二、Spring Boot JPA整合

[Accessing data with MySQL](https://spring.io/guides/gs/accessing-data-mysql/)

## 十三、Spring Boot Async Task Executor

[Spring Boot Async Task Executor](http://www.devglan.com/spring-boot/spring-boot-async-task-executor)

## 十四、问题解决

[HOW TO PACKAGE SPRING BOOT APPLICATION AS EXECUTABLE JAR?](http://www.tsarx.com/2017/06/how-package-spring-boot-application-executable-jar/)

[Spring Boot Tomcat 端口](http://fanlychie.github.io/post/spring-boot-tomcat-port.html)

## 十五、Spring Boot 特性

- 使用 Spring 项目引导页面可以在几秒构建一个项目
- 方便对外输出各种形式的服务，如 REST API、WebSocket、Web、Streaming、Tasks
- 非常简洁的安全策略集成
- 支持关系数据库和非关系数据库
- 支持运行期内嵌容器，如 Tomcat、Jetty
- 强大的开发包，支持热启动

spring-boot-devtools可以实现页面、类文件热部署

- 自动管理依赖
- 自带应用监控
- 支持各种 IDE，如 IntelliJ IDEA 、NetBeans

缺点是集成度较高，使用过程中不太容易了解底层。