package cn.okcl.im.exception;

import cn.okcl.im.enums.ResultCodeEnum;
import lombok.Getter;

/**
 * 自定义业务异常类
 */
@Getter
public class BusinessException extends RuntimeException {
    private final Integer code;

    /**
     * 构造方法
     *
     * @param resultCodeEnum 结果码枚举类
     */
    public BusinessException(ResultCodeEnum resultCodeEnum) {
        super(resultCodeEnum.getMsg());
        this.code = resultCodeEnum.getCode();
    }

    /**
     * 构造方法
     *
     * @param resultCodeEnum 结果码枚举类
     * @param message        自定义异常消息
     */
    public BusinessException(ResultCodeEnum resultCodeEnum, String message) {
        super(message);
        this.code = resultCodeEnum.getCode();
    }

    /**
     * 构造方法
     *
     * @param code    自定义状态码
     * @param message 自定义异常消息
     */
    public BusinessException(Integer code, String message) {
        super(message);
        this.code = code;
    }
}
