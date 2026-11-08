package cn.okcl.im.model.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

/**
 * 聊天消息表
 *
 * @TableName im_message
 */
@TableName(value = "im_message")
@Data
public class ImMessage implements Serializable {
    @Serial
    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
    /**
     * 主键ID
     */
    @TableId(type = IdType.AUTO)
    private Long id;
    /**
     * 所属会话ID
     */
    private Long conversationId;
    /**
     * 发送方用户ID
     */
    private Long fromUserId;
    /**
     * 接收方用户ID(单聊)
     */
    private Long targetUserId;
    /**
     * 群组ID(群聊)
     */
    private Long groupId;
    /**
     * 文本内容
     */
    private String content;
    /**
     * 消息类型 (TEXT, IMAGE, VOICE, VIDEO, FILE, WITHDRAW等)
     */
    private String type;
    /**
     * 附加信息(JSON格式)
     */
    private String extra;
    /**
     * 音视频时长(秒)
     */
    private Integer duration;
    /**
     * 文件名
     */
    private String fileName;
    /**
     * 文件大小(Byte)
     */
    private Long fileSize;
    /**
     * 文件拓展名类型
     */
    private String fileType;
    /**
     * 文件下载/访问地址
     */
    private String fileUrl;
    /**
     * 缩略图Base64或地址
     */
    private String thumbUrl;
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