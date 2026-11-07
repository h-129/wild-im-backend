package cn.okcl.im.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 用户状态枚举
 */
@Getter
@AllArgsConstructor
public enum AccountStatusEnum {
    NORMAL("NORMAL", "正常"), // 正常状态
    DISABLED("DISABLED", "禁用"); // 禁用状态
    /**
     * 状态码
     */
    @EnumValue // 用于数据库存储
    @JsonValue // 用于JSON序列化
    private final String code;
    /**
     * 状态描述
     */
    private final String desc;
}
