package cn.okcl.im.model.vo;

import lombok.Data;

/**
 * 用户信息VO
 */
@Data
public class UserInfoVo {
    private Long id;
    private String username;
    private String nickname;
    private Integer sex;
    private String avatar;
}
