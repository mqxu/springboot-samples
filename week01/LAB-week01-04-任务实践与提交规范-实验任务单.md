# 实验任务单：任务实践与提交规范（week01-课时4）

## 一、实验目标

1. 通过基础版或挑战版任务，完成至少一个可运行接口。  
2. 按课程要求组织和提交“规格文档 + 代码 + 截图”。  
3. 初步体验“文档 → AI 生成代码 → 运行验证”的完整闭环。  

## 二、任务简介

本课时提供两个难度档次任务，可**自主选择**：

### 📚 基础版任务：Hello 接口

- 目标：完成并完善 `GET /api/hello` 接口。  
- 要求：
  - 规格文档：`hello-spec.md` 完整、清晰；  
  - 代码：`HelloController` + `ResultVO` 正常编译；  
  - 运行：访问 `/api/hello` 返回正确 JSON。  

### 🚀 挑战版任务：系统健康检查 + 系统信息 + 环境适配

- 目标：在基础基础上，开发系统健康检查接口及扩展接口。  
- 必做：`GET /api/health`，返回项目名称、版本号、当前服务器时间、运行状态。  
- 选做（至少完成 1 项）：
  - `GET /api/system/info`：返回应用信息；  
  - `GET /api/system/env`：返回部分安全的环境变量；  
  - `GET /api/system/metrics`：返回 JVM 内存、线程数等指标。  
- 要求：
  - 引入多环境配置（dev/test/prod）；  
  - 具有简单的全局异常处理（如非法访问时返回统一错误格式）。  

> 详细需求可由教师在课堂 PPT 中补充，学生也可自行扩展。  

## 三、通用实验步骤

无论选择哪个难度，推荐遵循同一流程：

1. **选择任务难度**：基础版或挑战版。  
2. **编写或完善规格文档**：  
   - 基础版：完善 `hello-spec.md`；  
   - 挑战版：新增 `health-spec.md`，可选 `system-info-spec.md`（可自拟名称）。  
3. **使用 AI 工具生成或补全 Controller 代码**。  
4. **集成进 `springboot-hello` 项目并运行验证**。  
5. **整理提交材料**：文档 + 代码 + 截图。  

## 四、目录与文件组织规范

建议目录结构如下：

```text
week01/
  docs/
    hello-spec.md
    health-spec.md        # 若完成挑战版
    system-info-spec.md   # 若完成挑战版扩展
  springboot-hello/
    src/main/java/...     # 项目代码
  screenshots/
    hello.png
    health.png
    system-info.png
```

### 文档要求

- 每个接口对应一份规格文档（可多个接口合在一份文档中，章节清晰即可）。  
- 文档结构建议使用“初级模板”：
  - 核心目标  
  - 业务规则  
  - 技术约束  
  - 输入输出  
  - 验收标准  

### 代码要求

- 所有 Controller 类放在统一包下（如 `controller`）。  
- 类命名清晰（如 `HelloController`、`HealthController`、`SystemInfoController`）。  
- 代码能编译通过，无明显错误。  

### 截图要求

- 至少 1 张截图，展示浏览器或 Postman 访问接口的返回结果。  
- 文件命名能体现接口含义（如 `hello.png`、`health.png`）。  

## 五、具体操作提示

### 步骤 1：完善/编写规格文档

以挑战版“健康检查接口”为例，可参考如下结构（节选）：

```markdown
# 系统健康检查接口规格文档

## 1. 核心目标
- 提供一个系统健康检查接口，方便快速确认服务是否正常运行。

## 2. 业务规则
- 接口路径：GET /api/health
- 返回字段：
  - appName：应用名称
  - version：应用版本
  - time：当前服务器时间
  - status：运行状态（UP/DOWN）

## 3. 技术约束
- 使用 Spring Boot 3.x
- 统一返回类型 ResultVO
...
```

### 步骤 2：使用 AI 生成或修改代码

参考此前的 Prompt，将对应规格文档粘贴给大模型，让其：

- 生成新的 Controller；  
- 或在现有 Controller 中增加方法；  
- 必要时生成/修改统一返回类。  

### 步骤 3：运行并验证

1. 启动 `springboot-hello` 项目。  
2. 通过浏览器或 Postman 访问：
   - `http://localhost:8080/api/hello`  
   - `http://localhost:8080/api/health` 等（若实现挑战版）  
3. 若返回结果与规格文档不一致，记录差异（后续周会用到“文档迭代 + AI 重写”流程）。  

## 六、提交要求

本课时结束后，要求每位同学至少提交：

1. **至少一份规格文档**（对应自己选择的任务难度）：  
   - 基础版：`hello-spec.md`  （必做）
   - 挑战版：`health-spec.md`（选做），可选 `system-info-spec.md`  
2. **对应接口的 Controller 代码**；  
3. **至少一张接口访问截图**。  

## 七、评分与难度系数说明

- 基础版：满分 100，难度系数 1.0  
- 挑战版：满分 100，最终得分 = 原分 × 1.5（最高 150）  

本课时主要关注：

- 是否完成了**至少一档任务**；  
- 文档与代码是否对应、一致；  
- 是否按规范组织了目录与提交材料。  

## 八、自动化检查建议（CI 示例）

教师可在 CI 中增加基础接口探活与返回体校验：

```bash
cd week01/springboot-hello
mvn -q -DskipTests=true spring-boot:run &
PID=$!
sleep 25

echo "检查 /api/hello 接口..."
RESP_HELLO=$(curl -s http://localhost:8080/api/hello || true)

echo "检查 /api/health 接口（如存在）..."
RESP_HEALTH=$(curl -s http://localhost:8080/api/health || true)

kill $PID || true

echo "$RESP_HELLO" | grep -q "code" && echo "$RESP_HELLO" | grep -q "data" && echo "Hello 接口检查通过" || echo "Hello 接口可能未按要求实现"

if [ -n "$RESP_HEALTH" ]; then
  echo "$RESP_HEALTH" | grep -q "status" && echo "Health 接口存在且返回包含 status 字段" || echo "Health 接口存在但结构可能不符合预期"
else
  echo "未检测到 Health 接口响应（可能未实现或路径不同）"
fi
```

- 通过日志，教师可快速看到哪些学生至少完成了基础接口，哪些尝试了挑战版任务。  

