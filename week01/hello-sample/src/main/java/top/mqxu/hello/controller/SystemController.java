package top.mqxu.hello.controller;

import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.mqxu.hello.service.SystemInfoService;
import top.mqxu.hello.vo.ResultVO;

import java.util.Map;

/**
 * System 接口控制器
 *
 * @author mqxu
 * @date 2026/3/5
 * @description SystemController
 **/
@RestController
@RequestMapping("/api/system")
public class SystemController {

    @Resource
    private SystemInfoService systemInfoService;

    @GetMapping("/info")
    public ResultVO<Map<String, Object>> getSystemInfo() {
        Map<String, Object> systemInfo = systemInfoService.getSystemBasicInfo();
        return ResultVO.success("system info query success", systemInfo);
    }
}