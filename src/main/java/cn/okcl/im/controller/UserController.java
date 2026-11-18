package cn.okcl.im.controller;

import cn.okcl.im.common.Result;
import cn.okcl.im.model.vo.UserInfoVo;
import cn.okcl.im.service.ImUserService;
import cn.okcl.im.utils.UserHolder;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户控制器
 */
@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {
    @Resource
    private ImUserService imUserService;

    /**
     * 获取用户信息
     *
     * @return 用户信息结果
     */
    @GetMapping("/info")
    public Result<UserInfoVo> info() {
        //获取当前登录用户id
        Long userId = UserHolder.getUserId();
        log.info("当前登录用户：{}", userId);
        UserInfoVo userInfoVo = imUserService.getUserInfo(userId);
        return Result.success(userInfoVo);
    }
}
