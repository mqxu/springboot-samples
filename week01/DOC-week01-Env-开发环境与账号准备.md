# 开发环境与账号准备说明（week01 使用）

## 1. 软件环境要求

- 操作系统：Windows 10+/macOS/Linux 任一  
- JDK：JDK 17 或 JDK 21  
- 构建工具：Maven 3.9+
- IDE：IntelliJ IDEA
- 版本管理：Git（命令行或图形客户端均可）  
- 浏览器：Chrome / Edge / Firefox 任一  

> 建议：统一使用 JDK 17 + IntelliJ IDEA，方便课堂演示与排错。

## 2. 必备软件安装清单

### 2.1 JDK 安装与验证

1. 前往官网（如 [Adoptium](https://adoptium.net/zh-CN/temurin/releases)、[Bellsoft](https://bell-sw.com/pages/downloads/#jdk-17-lts)、[Zulu](https://www.azul.com/downloads/?package=jdk#zulu) 等）下载 JDK 17 安装，配置环境变量。  
2. 完成后在终端/命令行输入：

```bash
java -version
```

3. 看到版本号为 `17.x` 即为成功。

### 2.2 IntelliJ IDEA 安装

1. 下载 IntelliJ IDEA Community 或 Ultimate。  
2. 安装完成后：
   - 配置 `JDK 17` 为默认 Project SDK；  
   - 确认 Maven、Git 插件处于启用状态（一般默认开启）。  

### 2.3 Git 安装与配置

1. 安装 Git（官网或应用商店）。  
2. 第一次使用时配置用户名和邮箱：

```bash
git config --global user.name "你的姓名"
git config --global user.email "你的邮箱"
```

3. 验证安装：

```bash
git --version
```

### 2.4 Maven 单独安装

如不使用 IDEA 内置 Maven，则需单独安装 Maven 3.9+：

1. [下载 Maven](https://maven.apache.org/download.cgi)，并配置环境变量 `MAVEN_HOME` 与 `PATH`。  
2. 在终端执行 `mvn -v` ，输出中能看到 Maven 版本号即为成功。

3. [配置 Maven 阿里云镜像](https://developer.aliyun.com/mirror/maven/)。

## 3. AI 工具账号准备

本课程需要至少 1 个大模型账号：

- 国际：Claude、OpenAI（GPT）等  
- 国内：豆包、通义千问、Kimi、智谱清言 GLM、MiniMax 等  

课堂建议：

- 尽量提前注册自己的账号（如受限，可 2–3 人共用一个）。  

最低要求：  
> 上课时，每位学生至少能使用一种大模型（网页或客户端均可）。

## 4. 课程 Git 仓库准备

### 4.1 教师侧

- 准备一个远程仓库（Gitee/GitHub 均可），例如：  
  - 主分支：`main`  
  - 示例项目目录：`examples/week01/springboot-hello`  
- 将课堂示例代码、文档模板、实验任务单统一放入该仓库，方便学生 `clone` 和参考。

### 4.2 学生侧

- 每位学生一个个人仓库（或在统一课程仓库的个人分支/目录）：  
  - 建议在仓库根目录按周建立子目录，例如：  

```text
week01/
  docs/
  springboot-hello/
  screenshots/
week02/
...
```

- 第一周要求：
  - 在仓库中创建 `week01` 目录；  
  - 所有第一周代码与文档放入该目录。  

## 5. 检查清单（上课前）

### 5.1 教师自查

- [ ] 演示机器安装 JDK 17 + IDEA + Git  
- [ ] 能从零创建并运行一个 Spring Boot Demo  
- [ ] 能访问至少一个大模型网页并正常对话  
- [ ] 课程示例仓库已创建，并可 `git clone`  

### 5.2 学生自查（在 week01-课时1 实验中引导完成）

- [ ] 本机已安装 JDK（确认 `java -version` 可用）  
- [ ] 本机已安装 Git（确认 `git --version` 可用）  
- [ ] 已安装并能打开 IntelliJ IDEA  
- [ ] 至少能登录一个大模型网页，并发送简单请求  

> 第一周课堂中，教师与助教将根据本清单逐项检查，并帮助学生解决环境问题。

