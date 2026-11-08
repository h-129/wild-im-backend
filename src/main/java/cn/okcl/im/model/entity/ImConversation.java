package cn.okcl.im.model.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

/**
 * 会话表
 *
 * @TableName im_conversation
 */
@TableName(value = "im_conversation")
@Data
public class ImConversation implements Serializable {
    @Serial
    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
    /**
     * 主键ID
     */
    @TableId(type = IdType.AUTO)
    private Long id;
    /**
     * 会话类型 (PRIVATE:单聊, GROUP:群聊)
     */
    private String type;
    /**
     * 最后一条消息ID
     */
    private Long latestMessageId;
    /**
     * 会话状态 (DEFAULT:正常)
     */
    private String status;
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