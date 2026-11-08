package cn.okcl.im.model.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

/**
 * 联系人表
 *
 * @TableName im_contact
 */
@TableName(value = "im_contact")
@Data
public class ImContact implements Serializable {
    @Serial
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
     * 联系人(好友)ID
     */
    private Long contactUserId;
    /**
     * 状态 (DEFAULT:正常, BLACK:拉黑)
     */
    private String status;
    /**
     * 联系人备注
     */
    private String remark;
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