package cn.okcl.im.interceptor;

import cn.okcl.im.constant.HttpConstants;
import cn.okcl.im.enums.ResultCodeEnum;
import cn.okcl.im.exception.BusinessException;
import cn.okcl.im.utils.JwtUtil;
import cn.okcl.im.utils.UserHolder;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

/**
 * 登录拦截器
 * 用于检查用户是否已登录
 */
@Slf4j
@Component
public class LoginInterceptor implements HandlerInterceptor {
    @Resource
    private JwtUtil jwtUtil;

    /**
     * 在请求处理之前进行调用
     *
     * @param request  HTTP请求对象，包含客户端发送的请求信息
     * @param response HTTP响应对象，用于向客户端发送响应
     * @param handler  处理器对象，即将要执行的Controller方法
     * @return boolean 返回true表示继续执行后续拦截器和目标方法，返回false表示中断执行
     */
    @Override
    public boolean preHandle(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull Object handler) {
        // 非Controller方法，直接放行
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }
        String uri = request.getRequestURI();
        log.debug("拦截到uri:{}", uri);
        // 检查用户是否已登录
        String header = request.getHeader(HttpConstants.HEADER_AUTHORIZATION);
        if (header == null) {
            throw new BusinessException(ResultCodeEnum.UNAUTHORIZED);
        }
        // 从请求头中提取token 移除Bearer "
        String token = header.replace(HttpConstants.HEADER_AUTHORIZATION_BEARER, "");
        // 验证token 并且将token存入上下文中
        Long userId = jwtUtil.getUserId(token);
        UserHolder.setUserId(userId);
        return true;
    }

    /**
     * 在整个请求处理完成之后调用，包括渲染视图之后
     * 主要用于资源清理工作
     *
     * @param request  HTTP请求对象，包含客户端发送的请求信息
     * @param response HTTP响应对象，用于向客户端发送响应
     * @param handler  处理器对象，即已执行的Controller方法
     * @param ex       请求处理过程中抛出的异常，如果没有异常则为null
     */
    @Override
    public void afterCompletion(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull Object handler, @Nullable Exception ex) {
        log.debug("请求处理完成");
        // 清理上下文
        UserHolder.clear();
    }
}
