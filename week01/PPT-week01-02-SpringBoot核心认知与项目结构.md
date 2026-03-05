# PPT 大纲：week01-课时2 Spring Boot 核心认知与项目结构

---

## 封面

- 标题：`Spring Boot 核心认知与项目结构`  
- 副标题：`从 0 创建第一个 Spring Boot 项目`  
- 信息：授课教师、学院、学期、课次（week01-课时2）  

---

## 目录 / Agenda

- Spring 与 Spring Boot 对比  
- Spring Boot 核心特性  
- 三层架构与职责分工  
- 最小 Spring Boot 项目结构解析  
- 在 IDEA 中新建 Spring Boot 项目  
- 运行项目与启动日志解读  

---

## Spring 与 Spring Boot 对比

- 传统 Spring：  
  - 需要大量 XML 或 Java Config 配置；  
  - 项目搭建步骤多，入门成本高；  
  - 部署通常需要打包成 WAR 放到外部容器中。  

- Spring Boot：  
  - “约定优于配置”，大量默认配置开箱即用；  
  - 提供 `Starter` 起步依赖，快速集成常用功能；  
  - 内嵌 Tomcat/Jetty 等服务器，直接运行 Jar 即可。  

---

## Spring Boot 核心特性

- **自动配置（Auto Configuration）**  
  - 根据类路径上的依赖与配置，自动装配相关 Bean；  
  - 大幅减少样板配置代码。  

- **起步依赖（Starter）**  
  - 例如：`spring-boot-starter-web`、`spring-boot-starter-data-jpa`；  
  - 一行依赖拉起一整套功能栈。  

- **生产就绪（Actuator）**  
  - 健康检查、指标监控、环境信息等开箱即用；  
  - 方便后期部署与运维。  

---

## 分层架构：Controller / Service / Mapper

简单层次图（可用图示展示）：

```text
Controller 层：接收请求、返回响应
    ↓
Service 层：封装业务逻辑
    ↓
Mapper/Repository 层：访问数据库
    ↓
Database：持久化存储
```

- **Controller 层**  
  - 处理 HTTP 请求；  
  - 调用 Service 完成业务；  
  - 不写复杂业务逻辑。  

- **Service 层**  
  - 封装核心业务规则；  
  - 调用数据访问层；  
  - 保持可复用性与可测试性。  

- **Mapper/Repository 层**  
  - 直接操作数据库；  
  - 尽量不包含业务逻辑。  

---

## 最小 Spring Boot 项目结构（1）

示例结构：

```text
springboot-hello/
  pom.xml
  src/
    main/
      java/
        com/example/springboothello/
          SpringbootHelloApplication.java
      resources/
        application.properties (或 application.yml)
    test/
      java/...
```

- `pom.xml`：Maven 项目配置文件，声明依赖与插件。  
- `SpringbootHelloApplication.java`：入口类，包含 `main` 方法。  
- `application.properties` / `application.yml`：应用配置文件（端口、数据源等）。  

---

## 最小 Spring Boot 项目结构（2）—— 启动类

示例代码：

```java
@SpringBootApplication
public class SpringbootHelloApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringbootHelloApplication.class, args);
    }
}
```

- `@SpringBootApplication`：组合注解，包含：  
  - `@Configuration`  
  - `@EnableAutoConfiguration`  
  - `@ComponentScan`  
- 作用：标记应用入口并启用自动配置和组件扫描。  

---

## 在 IntelliJ IDEA 中新建 Spring Boot 项目（1）

步骤示意：  

1. 打开 IDEA → `New Project`。  
2. 选择 `Spring Boot`。  
3. 填写项目信息：  
   - Group：`com.example`  
   - Artifact：`springboot-hello`  
   - Language：`Java`  
   - Java：`17`  

---

## 在 IntelliJ IDEA 中新建 Spring Boot 项目（2）

4. 选择依赖：  
   - 勾选 `Spring Web`（最基础的 Web 能力）。  
5. 检查项目预览信息无误后点击 `Finish`。  
6. 等待 IDEA 下载依赖、构建项目。  

注意提示：

- 若下载依赖缓慢，可 Maven 的阿里云镜像；  
- 统一约定项目编码为 UTF-8。  

---

## 运行 Spring Boot 项目

1. 在 `SpringbootHelloApplication` 类左侧点击绿色运行按钮，选择 `Run`。  
2. 观察控制台日志：  
   - 无红色错误堆栈；  
   - 有类似如下行：  
     - `Started SpringbootHelloApplication in X.XXX seconds`  
3. 打开浏览器访问 `http://localhost:8080`：  
   - 看到默认错误页面 / Whitelabel Error Page 也算启动成功（因为还没有编写 Controller）。  

---

## 为什么要分层？（引导讨论）

问题引导：  

- 如果我们把所有逻辑都写在一个类里，会怎样？  
  - 难以维护、难以测试、协作冲突严重。  
- 分层带来的好处：  
  - 修改业务逻辑时不必动 Controller；  
  - 更容易做单元测试与重构；  
  - 有利于团队协作和职责划分。  

可准备 1–2 个简单问答：  

- “Controller 层的主要职责是什么？”  
- “Service 层为什么不能直接返回 HTTP 响应？”  

---

## 小结

- 本节课要点：  
  - 理解了 Spring 与 Spring Boot 的区别；  
  - 知道了 Spring Boot 的核心特性和项目基本结构；  
  - 了解了标准分层架构的职责分工。  

- 下一步：  
  - 在 IDEA 中创建自己的 `springboot-hello` 项目；  
  - 成功运行并截图作为证据；  
  - 在文档中记录对项目结构的理解。  

