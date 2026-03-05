package top.mqxu.hello.service.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringBootVersion;
import org.springframework.stereotype.Service;
import top.mqxu.hello.service.SystemInfoService;

import java.util.HashMap;
import java.util.Map;

/**
 * @author mqxu
 * @date 2026/3/5
 * @description SystemInfoServiceImpl
 **/
@Service
public class SystemInfoServiceImpl implements SystemInfoService {

    @Value("${server.port:8080}")
    private Integer serverPort;

    @Override
    public Map<String, Object> getSystemBasicInfo() {
        Map<String, Object> info = new HashMap<>();
        // JDK版本
        info.put("javaVersion", System.getProperty("java.version"));
        // SpringBoot版本
        info.put("springBootVersion", SpringBootVersion.getVersion());
        // 操作系统名称
        info.put("osName", System.getProperty("os.name"));
        // 操作系统架构
        info.put("osArch", System.getProperty("os.arch"));
        // 服务端口
        info.put("serverPort", serverPort);
        return info;
    }
}
