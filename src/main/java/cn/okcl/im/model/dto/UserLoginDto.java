package cn.okcl.im.model.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

/**
 * 用户登录DTO
 */
@Data
public class UserLoginDto {
    /**
     * 用户名
     */
    @NotEmpty(message = "用户名不能为空")
    private String username;
    /**
     * 密码
     */
    @NotEmpty(message = "密码不能为空")
    private String password;
}
