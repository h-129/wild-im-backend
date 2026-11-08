package cn.okcl.im.model.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

/**
 * 系统消息与申请表
 *
 * @TableName im_system_message
 */
@TableName(value = "im_system_message")
@Data
public class ImSystemMessage implements Serializable {
    @Serial
    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
    /**
     * 主键ID
     */
    @TableId(type = IdType.AUTO)
    private Long id;
    /**
     * 发起方ID
     */
    private Long fromUserId;
    /**
     * 目标用户ID
     */
    private Long targetUserId;
    /**
     * 相关群组ID
     */
    private Long groupId;
    /**
     * 处理状态 (PENDING:待处理, ACCEPTED:已同意, REJECTED:已拒绝)
     */
    private String status;
    /**
     * 系统消息类型 (FRIEND_REQUEST, GROUP_INVITE等)
     */
    private String type;
    /**
     * 消息内容或申请理由
     */
    private String content;
    /**
     * 附加信息(JSON格式)
     */
    private String extra;
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