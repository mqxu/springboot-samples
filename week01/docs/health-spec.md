# Health 接口规格文档

## 1. 核心目标

- 开发健康检查与系统信息相关接口，包含基础健康状态检查、系统信息查询能力，适配不同运行环境（开发/测试/生产），用于全面验证 Spring Boot 项目的运行状态、系统资源占用及环境配置是否正常。

## 2. 业务规则

### 2.1 基础健康检查接口（/api/health）

- 接口路径：GET /api/health
- 无需请求参数
- 返回 JSON，包含 code、msg、data 三个字段
- code 固定为 200，表示服务健康检查成功
- msg 固定为 "service is healthy"
- data 为对象，包含基础健康信息：
  - status：固定值 "UP"
  - env：当前运行环境（dev/test/prod）
  - timestamp：接口响应时间戳（毫秒）

### 2.2 系统信息接口（/api/system/*）

#### 2.2.1 系统基础信息（/api/system/info）

- 接口路径：GET /api/system/info
- 无需请求参数
- 返回 JSON，包含 code、msg、data 三个字段
- code 固定为 200，表示查询成功
- msg 固定为 "system info query success"
- data 为对象，包含：
  - javaVersion：JDK 版本
  - springBootVersion：Spring Boot 版本
  - osName：操作系统名称
  - osArch：操作系统架构
  - serverPort：服务运行端口

#### 2.2.2 系统资源占用（/api/system/metrics）

- 接口路径：GET /api/system/metrics
- 无需请求参数
- 返回 JSON，包含 code、msg、data 三个字段
- code 固定为 200，表示查询成功
- msg 固定为 "system metrics query success"
- data 为对象，包含：
  - cpuUsage：CPU 使用率（百分比，保留两位小数）
  - memoryUsage：内存使用率（百分比，保留两位小数）
  - maxMemory：JVM 最大可用内存（MB）
  - usedMemory：JVM 已使用内存（MB）

## 3. 技术约束

- 使用 Spring Boot 3.5.11
- 使用 Java 17
- 端口使用默认 8080
- 返回类型统一使用包装类 ResultVO<T>（T 为对应数据类型）
- 环境配置通过 application-{env}.yml 区分，默认加载 application-dev.yml
- 系统资源信息通过 Java Management Extensions (JMX) 或 Spring Boot Actuator 实现采集

## 4. 输入输出

### 4.1 输入

- 所有接口均无请求体，无查询参数
- 环境标识通过 Spring Boot 激活配置文件指定（如 --spring.profiles.active=prod）

### 4.2 输出

#### 4.2.1 /api/health 成功示例

```JSON
{
  "code": 200,
  "msg": "service is healthy",
  "data": {
    "status": "UP",
    "env": "dev",
    "timestamp": 1718987654321
  }
}
```

#### 4.2.2 /api/system/info 成功示例

```JSON
{
  "code": 200,
  "msg": "system info query success",
  "data": {
    "javaVersion": "17.0.8",
    "springBootVersion": "3.5.11",
    "osName": "Windows 11",
    "osArch": "amd64",
    "serverPort": 8080
  }
}
```

#### 4.2.3 /api/system/metrics 成功示例

```JSON
{
  "code": 200,
  "msg": "system metrics query success",
  "data": {
    "cpuUsage": 15.67,
    "memoryUsage": 45.23,
    "maxMemory": 4096.00,
    "usedMemory": 1851.56
  }
}
```

## 5. 验收标准

### 5.1 基础健康检查接口

- 项目启动后，访问 /api/health 返回 HTTP 状态码 200
- 返回 JSON 包含 code、msg、data 字段，且 code=200、msg="service is healthy"
- data 中的 env 与当前激活的 Spring 配置文件一致，timestamp 为有效时间戳

### 5.2 系统信息接口

- 访问 /api/system/info 返回 HTTP 状态码 200，data 包含所有指定的系统基础信息字段，且字段值非空、符合实际环境
- 访问 /api/system/metrics 返回 HTTP 状态码 200，data 中的 CPU/内存使用率数值在合理范围（0-100），内存数值符合 JVM 实际配置
- 切换不同运行环境（dev/test/prod）后，/api/health 返回的 env 字段值同步更新
- 所有接口返回的 JSON 结构符合 ResultVO 包装类定义，无字段缺失或格式错误