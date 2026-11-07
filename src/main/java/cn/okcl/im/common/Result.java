package cn.okcl.im.common;

import cn.okcl.im.enums.ResultCodeEnum;
import lombok.Data;

import java.io.Serializable;

/**
 * 响应结果类
 *
 * @param <T> 数据类型
 */
@Data
public class Result<T> implements Serializable {
    private Integer code;
    private String msg;
    private T data;

    // 私有构造方法, 防止外部实例化
    private Result() {
    }

    /**
     * 构建响应结果
     *
     * @param code 状态码
     * @param msg  提示消息
     * @param data 数据
     * @param <T>  数据类型
     * @return 响应结果
     */
    private static <T> Result<T> build(Integer code, String msg, T data) {
        Result<T> result = new Result<>();
        result.setCode(code);
        result.setMsg(msg);
        result.setData(data);
        return result;
    }

    /**
     * 构建响应结果
     *
     * @param resultCodeEnum 业务枚举类
     * @param data           数据
     * @param <T>            数据类型
     * @return 响应结果
     */
    private static <T> Result<T> build(ResultCodeEnum resultCodeEnum, T data) {
        return build(resultCodeEnum.getCode(), resultCodeEnum.getMsg(), data);
    }

    public static <T> Result<T> success() {
        return build(ResultCodeEnum.SUCCESS, null);
    }

    public static <T> Result<T> success(T data) {
        return build(ResultCodeEnum.SUCCESS, data);
    }

    public static <T> Result<T> error() {
        return build(ResultCodeEnum.FAILED, null);
    }

    public static <T> Result<T> error(ResultCodeEnum resultCodeEnum) {
        return build(resultCodeEnum, null);
    }

    public static <T> Result<T> error(String msg) {
        return build(ResultCodeEnum.FAILED.getCode(), msg, null);
    }

    public static <T> Result<T> error(ResultCodeEnum resultCodeEnum, String msg) {
        return build(resultCodeEnum.getCode(), msg, null);
    }
}
