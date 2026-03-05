package top.mqxu.hello.controller;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.mqxu.hello.vo.ResultVO;

import java.time.Instant;

/**
 * Health 接口控制器
 * @author mqxu
 * @date 2026/3/5
 * @description HealthController
 **/
@RestController
@RequestMapping("/api")
public class HealthController {

    // 读取当前激活的环境配置
    @Value("${spring.profiles.active:dev}")
    private String env;

    @GetMapping("/health")
    public ResultVO<HealthResponse> healthCheck() {
        HealthResponse response = new HealthResponse();
        response.setStatus("UP");
        response.setEnv(env);
        response.setTimestamp(Instant.now().toEpochMilli());
        return ResultVO.success("service is healthy", response);
    }

    // 健康检查响应数据模型
    @Data
    public static class HealthResponse {
        private String status;
        private String env;
        private Long timestamp;
    }
}
