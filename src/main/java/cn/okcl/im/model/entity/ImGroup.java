package cn.okcl.im.model.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

/**
 * 群组表
 *
 * @TableName im_group
 */
@TableName(value = "im_group")
@Data
public class ImGroup implements Serializable {
    @Serial
    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
    /**
     * 主键ID
     */
    @TableId(type = IdType.AUTO)
    private Long id;
    /**
     * 群名称
     */
    private String name;
    /**
     * 发起人ID
     */
    private Long createUserId;
    /**
     * 群状态 (DEFAULT:正常)
     */
    private String status;
    /**
     * 群头像
     */
    private String avatar;
    /**
     * 群公告
     */
    private String notice;
    /**
     * 关联的群聊会话ID
     */
    private Long conversationId;
    /**
     * 逻辑删除标识
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