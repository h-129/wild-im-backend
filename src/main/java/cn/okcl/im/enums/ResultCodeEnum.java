package cn.okcl.im.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ResultCodeEnum {
    // ================== 基础通用状态 ==================
    SUCCESS(200, "操作成功"),
    FAILED(500, "系统开小差了，请稍后再试"),

    // ================== 客户端异常状态 ==================
    PARAM_ERROR(400, "请求参数校验失败"),
    UNAUTHORIZED(401, "未登录或 Token 已过期"),
    FORBIDDEN(403, "没有权限访问该资源"),
    NOT_FOUND(404, "请求的资源不存在"),

    // ================== IM 业务专属状态 ==================
    USER_NOT_EXIST(10001, "用户不存在"),
    USER_PASSWORD_ERROR(10002, "账号或密码错误"),
    ACCOUNT_DISABLED(10003, "当前账号已被冻结或封禁"),
    USER_ALREADY_EXIST(10004, "该用户名已被注册"),
    MSG_SEND_FAILED(20001, "消息发送失败"),
    MSG_ILLEGAL_CONTENT(20002, "包含违规内容"),
    GROUP_NOT_EXIST(30001, "群组不存在或已解散"),
    GROUP_MEMBER_FULL(30002, "群成员已满");
    /**
     * 状态码
     */
    private final Integer code;
    /**
     * 提示消息
     */
    private final String msg;
}
