package cn.okcl.im.handler;

import cn.okcl.im.common.Result;
import cn.okcl.im.enums.ResultCodeEnum;
import cn.okcl.im.exception.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 全局异常处理类
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    /**
     * 运行时异常处理方法
     *
     * @param e 异常对象
     * @return 处理结果
     */
    @ExceptionHandler(value = Exception.class)
    public Result<?> runtimeExceptionHandler(Exception e) {
        log.error("运行时异常", e);
        return Result.error(ResultCodeEnum.FAILED);
    }

    /**
     * 业务异常处理方法
     *
     * @param e 业务异常对象
     * @return 处理结果
     */
    @ExceptionHandler(value = BusinessException.class)
    public Result<?> businessExceptionHandler(BusinessException e) {
        log.error("业务异常", e);
        return Result.error(e.getCode(), e.getMessage());
    }

    /**
     * 捕获 @Valid / @Validated 校验失败抛出的异常
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Result<Void> handleValidationException(MethodArgumentNotValidException e) {
        // 获取所有的校验错误结果
        BindingResult bindingResult = e.getBindingResult();
        // 获取第一个错误字段的提示信息message
        FieldError fieldError = bindingResult.getFieldError();
        String errorMessage = fieldError != null ? fieldError.getDefaultMessage() : ResultCodeEnum.PARAM_ERROR.getMsg();
        log.error("参数校验异常: {}", errorMessage);
        return Result.error(ResultCodeEnum.PARAM_ERROR, errorMessage);
    }
}
