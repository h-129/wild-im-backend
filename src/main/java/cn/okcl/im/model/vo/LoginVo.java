package cn.okcl.im.model.vo;

import lombok.Data;

/**
 * 登录VO
 */
@Data
public class LoginVo {
    /**
     * 访问令牌
     */
    private String accessToken;
    /**
     * 刷新令牌
     */
    private String refreshToken;
}
