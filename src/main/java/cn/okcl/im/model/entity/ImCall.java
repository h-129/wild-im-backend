package cn.okcl.im.model.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

/**
 * 音视频通话记录表
 *
 * @TableName im_call
 */
@TableName(value = "im_call")
@Data
public class ImCall implements Serializable {
    @Serial
    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
    /**
     * 主键ID
     */
    @TableId(type = IdType.AUTO)
    private Long id;
    /**
     * 呼叫发起方
     */
    private Long fromUserId;
    /**
     * 呼叫接听方
     */
    private Long targetId;
    /**
     * 通话类型 (VOICE:语音, VIDEO:视频)
     */
    private String type;
    /**
     * 挂断/结束时间
     */
    private Date endTime;
    /**
     * 逻辑删除标识 (0:未删除, 大于0:已删除，值为id)
     */
    @TableLogic(value = "is_deleted", delval = "1")
    private Long isDeleted;
    /**
     * 创建时间(呼叫时间)
     */
    private Date createTime;
    /**
     * 更新时间
     */
    private Date updateTime;
}