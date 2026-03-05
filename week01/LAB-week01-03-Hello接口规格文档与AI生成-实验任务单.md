# 实验任务单：Hello 接口规格文档与 AI 代码生成（week01-课时3）

## 一、实验目标

1. 掌握初级规格文档模板的基本结构与写法。  
2. 为 Hello 接口写出一份完整的规格文档。  
3. 使用大模型根据规格文档生成 Spring Boot Controller 代码，并集成到项目中。  

## 二、实验环境

- 已完成创建和能启动的 `springboot-hello` 项目（week01-课时2 实验产物）  
- 文档编辑工具（IDE 内置 Markdown 编辑器或任意文本编辑器）  
- 至少一种大模型工具（网页或客户端）  

## 三、实验背景

我们希望实现一个最基础的接口：

- 请求方式：`GET /api/hello`  
- 功能：返回一段欢迎语，格式为统一的 JSON：  

```json
{
  "code": 200,
  "msg": "success",
  "data": "Hello Spring Boot"
}
```

接口的所有信息，不再“直接写在代码里”，而是**先写在规格文档中**，再交给 AI 按照文档生成代码。

## 四、实验任务

### 任务 1：编写 Hello 接口规格文档

1. 在仓库中创建目录与文件：  

```text
week01/
  docs/
    hello-spec.md
```

2. 按照以下模板编写文档（可在此基础上修改具体内容）：

````markdown
# Hello 接口规格文档

## 1. 核心目标
- 开发一个 Hello 接口，返回统一格式的欢迎信息，用于验证 Spring Boot 项目的基本配置是否正确。

## 2. 业务规则
- 接口路径：GET /api/hello
- 无需请求参数
- 返回 JSON，包含 code、msg、data 三个字段
- code 固定为 200，表示成功
- msg 固定为 "success"
- data 为欢迎语字符串，例如："Hello Spring Boot"

## 3. 技术约束
- 使用 Spring Boot 3.x
- 使用 Java 17
- 端口使用默认 8080
- 返回类型使用统一包装类 ResultVO<String>

## 4. 输入输出

### 4.1 输入
- 无请求体，无查询参数

### 4.2 输出
- 成功示例：
```json
{
  "code": 200,
  "msg": "success",
  "data": "Hello Spring Boot"
}
```

## 5. 验收标准
- 项目能成功启动
- 访问 /api/hello 返回 HTTP 状态码 200
- 返回 JSON 结构中包含 code、msg、data 三个字段
- data 字段内容为非空字符串，表达欢迎信息
````

3. 可根据个人喜好对欢迎语或说明文字进行调整，但**结构必须完整**。  

### 任务 2：使用基础 Prompt 生成 Controller 代码

1. 打开你选择的大模型工具。  
2. 将以下 Prompt 粘贴到对话框中，并将 `hello-spec.md` 的内容替换到指定位置：

```text
基于以下规格文档，生成 Spring Boot 3.x 的 Controller 和 ResultVO 代码：

【规格文档开始】
（在这里粘贴 hello-spec.md 的全文）
【规格文档结束】

生成要求：
1. 创建一个 HelloController，使用 @RestController 注解，类上使用 @RequestMapping("/api")。
2. 提供 GET /hello 接口方法，方法名为 hello。
3. 返回类型为 ResultVO<String>。
4. 若项目中尚未定义 ResultVO 类，请一并生成 ResultVO 通用返回类代码。
5. 不需要解释，只输出 Java 代码。
```

3. 等待模型返回代码，将生成的 `HelloController` 和 `ResultVO` 代码复制保存。  

### 任务 3：将生成代码集成进项目

1. 在 `springboot-hello` 项目中：
   - 创建包 `com.example.springboothello.controller`（包名可按实际项目调整）；  
   - 新建类 `HelloController`，粘贴 AI 生成的 `HelloController` 代码；  
   - 若需要 `ResultVO` 类，在合适的包（如 `common` 或 `model`）中创建 `ResultVO` 类，粘贴对应代码。  

2. 确保：
   - 包名与类名符合项目实际情况；  
   - 没有明显的编译错误（IDE 中无红色下划线）。  

> 若出现编译错误：  
> - 可请 AI 帮忙修复（附上错误信息与当前代码）；  
> - 或在课堂上请教师/助教一起排查。  

### 任务 4：简单运行与验证

1. 启动 Spring Boot 项目。  
2. 访问 `http://localhost:8080/api/hello`，观察返回结果是否符合文档约定。  
3. 若不符合，记录实际返回内容与期望的差异。  

## 五、提交要求

- 必须提交：
  - `week01/docs/hello-spec.md`：完整的规格文档；  
  - `springboot-hello` 中的 `HelloController` 和 `ResultVO` 代码文件。  
- 建议提交：
  - `week01/screenshots/hello-api.png`：浏览器或 Postman 访问 `/api/hello` 的截图。  

## 六、评价方式

- **必达标准：**
  - 规格文档结构完整，包含 1–5 节；  
  - 文档中的业务规则、输入输出、验收标准描述清晰；  
  - 项目中存在 `HelloController` 类，且方法签名大致符合规格。  
- **加分点：**
  - 在文档中补充了更多验收标准（如响应时间要求、编码规范要求等）；  
  - 能指出 AI 生成代码与规格文档不一致的地方，并在课堂/备注中说明。  

## 七、自动化检查建议（CI 示例）

教师可在 CI 中对学生仓库进行基础检查：

1. 检查文档是否包含核心标题：

```bash
grep -q "## 1. 核心目标" week01/docs/hello-spec.md
grep -q "## 2. 业务规则" week01/docs/hello-spec.md
grep -q "## 3. 技术约束" week01/docs/hello-spec.md
grep -q "## 4. 输入输出" week01/docs/hello-spec.md
grep -q "## 5. 验收标准" week01/docs/hello-spec.md
```

2. 启动项目并请求接口，校验返回结构中是否包含 `code`、`msg`、`data` 字段：  

```bash
cd week01/springboot-hello
mvn -q -DskipTests=true spring-boot:run &
PID=$!
sleep 25
RESP=$(curl -s http://localhost:8080/api/hello || true)
kill $PID || true

echo "$RESP" | grep -q "code" && echo "$RESP" | grep -q "msg" && echo "$RESP" | grep -q "data"
```

- 若脚本通过，说明接口基本符合统一返回结构的要求。  

