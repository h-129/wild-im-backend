package cn.okcl.im.model.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

/**
 * 用户注册dto
 */
@Data
public class UserRegisterDto {
    @NotEmpty(message = "用户名不能为空")
    @Size(min = 6, max = 16, message = "账号长度必须是6-16位字符")
    @Pattern(regexp = "^[a-zA-Z0-9]+$", message = "账号只能是数字、字母组合")
    private String username;
    @NotEmpty(message = "密码不能为空")
    @Size(min = 8, max = 20, message = "密码必长度必须是8-20位字符")
    @Pattern(regexp = "^[a-zA-Z0-9~!@#$^&*()_+\\-=.,]+$", message = "密码必须是数字、字母、特殊字符~!@#$^&*()_+-=.,")
    private String password;
    @NotEmpty(message = "昵称不能为空")
    @Size(min = 1, max = 10, message = "昵称长度必须是1-10位字符")
    private String nickname;

    //重写nickname的set方法实现去除前后空格
    public void setNickname(String nickname) {
        if (nickname != null) {
            //去除空格
            this.nickname = nickname.trim();
        } else {
            this.nickname = null;
        }
    }
}
