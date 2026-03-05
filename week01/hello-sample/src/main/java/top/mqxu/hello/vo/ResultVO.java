package top.mqxu.hello.vo;

import lombok.Data;

/**
 * 通用返回结果封装类
 * @param <T> 数据类型
 */
@Data
public class ResultVO<T> {
    /**
     * 响应状态码
     */
    private Integer code;

    /**
     * 响应消息
     */
    private String msg;

    /**
     * 响应数据
     */
    private T data;

    /**
     * 成功响应构造方法
     * @param data 响应数据
     */
    public ResultVO(T data) {
        this.code = 200;
        this.msg = "success";
        this.data = data;
    }
}