package top.mqxu.hello.vo;

import lombok.Data;

/**
 * 通用返回结果封装类
 *
 * @param <T> 数据类型
 * @author moqi
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
     * 响应成功
     *
     * @param data 响应数据
     * @param <T>  数据类型
     * @return 响应结果
     */
    public static <T> ResultVO<T> success(T data) {
        ResultVO<T> resultVO = new ResultVO<>();
        resultVO.setCode(200);
        resultVO.setMsg("success");
        resultVO.setData(data);
        return resultVO;
    }

    public static <T> ResultVO<T> success(String msg, T data) {
        ResultVO<T> resultVO = new ResultVO<>();
        resultVO.setCode(200);
        resultVO.setMsg(msg);
        resultVO.setData(data);
        return resultVO;
    }


    /**
     * 响应失败
     *
     * @param code 响应状态码
     * @param msg  响应消息
     * @param <T>  数据类型
     * @return 响应结果
     */
    public static <T> ResultVO<T> fail(Integer code, String msg) {
        ResultVO<T> resultVO = new ResultVO<>();
        resultVO.setCode(code);
        resultVO.setMsg(msg);
        return resultVO;
    }
}