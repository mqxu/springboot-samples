package top.mqxu.hello.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.mqxu.hello.vo.ResultVO;

/**
 * Hello接口控制器
 * @author moqi
 */
@RestController
@RequestMapping("/api")
public class HelloController {

    /**
     * 欢迎接口
     * @return 统一格式的响应结果
     */
    @GetMapping("/hello")
    public ResultVO<String> hello() {
        return new ResultVO<>("Hello Spring Boot 555");
    }
}