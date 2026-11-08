package cn.okcl.im.model.entity;

import cn.okcl.im.enums.AccountStatusEnum;
import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

/**
 * 用户表
 *
 * @TableName im_user
 */
@TableName(value = "im_user")
@Data
public class ImUser implements Serializable {
    @Serial
    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
    /**
     * 主键ID
     */
    @TableId(type = IdType.AUTO)
    private Long id;
    /**
     * 账号
     */
    private String username;
    /**
     * 密码(Spring Security Bcrypt)
     */
    private String password;
    /**
     * 真实姓名
     */
    private String realName;
    /**
     * 昵称
     */
    private String nickname;
    /**
     * 性别 (0:未知, 1:男, 2:女)
     */
    private Integer sex;
    /**
     * 省
     */
    private String province;
    /**
     * 市
     */
    private String city;
    /**
     * 国家
     */
    private String country;
    /**
     * 头像
     */
    private String avatar;
    /**
     * 手机号
     */
    private String phoneNumber;
    /**
     * 身份证
     */
    private String idCard;
    /**
     * 状态 (DEFAULT:正常, DISABLED:禁用)
     */
    private AccountStatusEnum status;
    /**
     * 地址
     */
    private String address;
    /**
     * 逻辑删除标识 (0:未删除, 大于0:已删除，值为id)
     */
    @TableLogic(value = "0", delval = "1")
    private Long isDeleted;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 更新时间
     */
    private Date updateTime;
}