package cn.okcl.im.model.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户会话关联表
 *
 * @TableName im_user_conversation
 */
@TableName(value = "im_user_conversation")
@Data
public class ImUserConversation implements Serializable {
    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
    /**
     * 主键ID
     */
    @TableId(type = IdType.AUTO)
    private Long id;
    /**
     * 用户ID
     */
    private Long userId;
    /**
     * 会话ID
     */
    private Long conversationId;
    /**
     * 会话类型
     */
    private String conversationType;
    /**
     * 目标ID (群组ID或对方用户ID)
     */
    private Long targetId;
    /**
     * 状态
     */
    private String status;
    /**
     * 会话备注
     */
    private String remark;
    /**
     * 用户在群中的昵称
     */
    private String nickname;
    /**
     * 角色 (MEMBER:普通成员, ADMIN:管理员, OWNER:群主)
     */
    private String role;
    /**
     * 当前会话未读消息数
     */
    private Integer unreadMessageCount;
    /**
     * 加入时间
     */
    private Date joinTime;
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