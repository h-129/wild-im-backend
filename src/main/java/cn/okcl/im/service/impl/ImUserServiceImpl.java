package cn.okcl.im.service.impl;


import cn.okcl.im.enums.ResultCodeEnum;
import cn.okcl.im.exception.BusinessException;
import cn.okcl.im.mapper.ImUserMapper;
import cn.okcl.im.model.dto.UserLoginDto;
import cn.okcl.im.model.dto.UserRegisterDto;
import cn.okcl.im.model.entity.ImUser;
import cn.okcl.im.model.vo.LoginVo;
import cn.okcl.im.service.ImUserService;
import cn.okcl.im.utils.JwtUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.spring.service.impl.ServiceImpl;
import jakarta.annotation.Resource;
import org.springframework.beans.BeanUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * @author 20846
 * @description 针对表【im_user(用户表)】的数据库操作Service实现
 * @createDate 2026-11-09 00:02:09
 */
@Service
public class ImUserServiceImpl extends ServiceImpl<ImUserMapper, ImUser> implements ImUserService {
    @Resource
    private ImUserMapper imUserMapper;
    @Resource
    private PasswordEncoder passwordEncoder;
    @Resource
    private JwtUtil jwtUtil;

    /**
     * 注册用户
     *
     * @param userRegisterDto 注册用户DTO
     * @return 是否注册成功
     */
    @Override
    public boolean register(UserRegisterDto userRegisterDto) {
        if (ObjectUtils.isEmpty(userRegisterDto)) {
            throw new BusinessException(ResultCodeEnum.PARAM_ERROR, "注册用户信息不能为空");
        }
        String username = userRegisterDto.getUsername();
        String password = userRegisterDto.getPassword();
        //判断用户名是否已经存在
        LambdaQueryWrapper<ImUser> isExitUser = new LambdaQueryWrapper<ImUser>()
                .eq(ImUser::getUsername, username);
        ImUser imUser = imUserMapper.selectOne(isExitUser);
        if (!ObjectUtils.isEmpty(imUser)) {
            throw new BusinessException(ResultCodeEnum.USER_ALREADY_EXIST);
        }
        //进行账号注册 加密密码
        String password_db = passwordEncoder.encode(password);
        ImUser user = new ImUser();
        //将DTO中的属性复制到实体类中
        BeanUtils.copyProperties(userRegisterDto, user);
        user.setPassword(password_db);
        return imUserMapper.insert(user) > 0;
    }

    /**
     * 用户登录
     *
     * @param userLoginDto 用户登录DTO
     * @return 登录结果
     */
    @Override
    public LoginVo login(UserLoginDto userLoginDto) {
        String username = userLoginDto.getUsername();
        String password = userLoginDto.getPassword();
        //判断用户名是否存在
        LambdaQueryWrapper<ImUser> isExitUser = new LambdaQueryWrapper<ImUser>()
                .eq(ImUser::getUsername, username);
        ImUser imUser = imUserMapper.selectOne(isExitUser);
        if (ObjectUtils.isEmpty(imUser)) {
            throw new BusinessException(ResultCodeEnum.USER_NOT_EXIST);
        }
        //判断密码是否正确
        if (!passwordEncoder.matches(password, imUser.getPassword())) {
            throw new BusinessException(ResultCodeEnum.USER_PASSWORD_ERROR);
        }
        //登录成功
        LoginVo loginVo = new LoginVo();
        //生成访问令牌和刷新令牌
        Map<String, Object> claims = new HashMap<>();
        claims.put("userId", imUser.getId());
        claims.put("username", username);
        String accessToken = jwtUtil.generateToken(claims);
        loginVo.setAccessToken(accessToken);
        return loginVo;
    }
}




