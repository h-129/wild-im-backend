package cn.okcl.im.controller;

import cn.okcl.im.common.Result;
import cn.okcl.im.model.dto.UserRegisterDto;
import cn.okcl.im.service.ImUserService;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 认证控制器
 */
@RestController
@RequestMapping("/auth")
@Slf4j
public class AuthController {
    @Resource
    private ImUserService imUserService;

    /**
     * 用户注册
     *
     * @param userRegisterDto 用户注册dto
     * @return 注册结果
     */
    @PostMapping("/register")
    public Result<Boolean> register(@Valid @RequestBody UserRegisterDto userRegisterDto) {
        log.info("用户注册: {}", userRegisterDto);
        imUserService.register(userRegisterDto);
        return Result.success(true);
    }
}
