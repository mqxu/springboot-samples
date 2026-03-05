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

- 使用 Spring Boot 3.5.11
- 使用 Java 17
- 端口使用默认 8081
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