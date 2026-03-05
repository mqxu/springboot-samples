# 实验任务单：Spring Boot 项目创建与运行（week01-课时2）

## 一、实验目标

1. 能在 IntelliJ IDEA 中从零创建一个 Spring Boot 项目。  
2. 能成功启动项目并看到控制台启动日志。  
3. 为后续接入 Controller、Service 等代码做好基础准备。  

## 二、实验环境

- IntelliJ IDEA（Community 或 Ultimate）  
- JDK 17  
- 课程 Git 仓库地址

## 三、实验任务

### 任务 1：创建 Spring Boot 项目

1. 打开 IntelliJ IDEA，点击 `New Project`。  
2. 选择 `Spring Initializr`（若无该选项，可使用本地模板或由教师提供骨架工程）。  
3. 填写项目信息（可参考）：  
   - Group：`com.example`  
   - Artifact：`springboot-hello`  
   - Name：`springboot-hello`  
   - Language：`Java`  
   - Packaging：`jar`  
   - Java：`17`  
4. 依赖选择：
   - 勾选 `Spring Web`。  
5. 点击 `Finish`，等待项目导入和依赖下载完成。  

> 若校园网络限制依赖下载，可使用教师准备好的本地 Maven 仓库或示例项目。

### 任务 2：了解项目结构

1. 在 IDEA 的 Project 视图中，观察并记录以下内容：
   - 启动类位置（通常为 `src/main/java/.../SpringbootHelloApplication.java`）；  
   - `src/main/resources/application.properties` 或 `application.yml` 的位置；  
   - `pom.xml`（或 `build.gradle`）中的关键信息（Spring Boot 版本、依赖列表）。  
2. 在 `week01` 目录下新建文档：`project-structure-notes.md`，简要说明：
   - 启动类的包名与类名；  
   - `resources` 目录主要用来放什么；  
   - 你对当前项目结构的直观理解（2–3 句话）。  

### 任务 3：运行项目

1. 在 IDEA 中打开启动类（带有 `@SpringBootApplication` 注解的类）。  
2. 点击左侧绿色运行按钮，或右键选择 `Run 'SpringbootHelloApplication'`。  
3. 在控制台中确认：
   - 日志最后几行出现类似：  
     `Started SpringbootHelloApplication in X.XXX seconds`；  
   - 日志中无明显红色错误堆栈。  
4. 使用浏览器打开：`http://localhost:8080`  
   - 若看到默认 Whitelabel Error Page 或 404 等页面，说明服务已正常启动（暂未定义 Controller）。  

5. 截图保存为：`week01/screenshots/hello-run.png`：
   - 建议包含 IDEA 控制台成功启动日志或浏览器访问结果画面。  

### 任务 4：初始化 Git 并提交

1. 在项目根目录执行：

```bash
git init
git add .
git commit -m "chore: init springboot-hello project"
```

2. 关联远程仓库并推送：

```bash
git remote add origin <你的远程地址>
git push -u origin main
```

> 如暂时无法使用 Git，可在本地完成，课后再统一配置。  

## 四、提交要求

- 代码：  
  - 将 `springboot-hello` 项目放入仓库的 `week01/` 目录下，例如：  

```text
week01/
  springboot-hello/
  project-structure-notes.md
  screenshots/
    hello-run.png
```

- 文档：  
  - `week01/project-structure-notes.md`：简要说明项目结构。  
- 截图：  
  - `week01/screenshots/hello-run.png`：项目成功启动的证据。  

## 五、评价方式

- **必达标准：**
  - 项目能在本地成功启动一次；  
  - 提交了项目结构说明文档和启动截图。  
- **加分点：**
  - 在 `project-structure-notes.md` 中对某些结构提出了疑问或自己的理解；  
  - 已完成 Git 初始化与首次提交。  

## 六、自动化检查建议（CI 示例）

教师可在课程仓库 CI 中对学生仓库进行如下检查：

```bash
cd week01/springboot-hello
mvn -q -DskipTests=true spring-boot:run &
PID=$!
sleep 25
STATUS=$(curl -s -o /dev/null -w "%{http_code}" http://localhost:8080)
kill $PID || true

if [ "$STATUS" = "200" ] || [ "$STATUS" = "404" ]; then
  echo "Spring Boot 服务启动成功（HTTP 状态码：$STATUS）"
else
  echo "Spring Boot 服务启动可能失败（HTTP 状态码：$STATUS）"
  exit 1
fi
```

- 若能成功获取到 200/404 等响应，即认为服务已正常启动。  
- 教师可在 CI 报告中快速看到哪些同学项目未能启动。  

