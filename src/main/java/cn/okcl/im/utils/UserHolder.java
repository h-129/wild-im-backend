package cn.okcl.im.utils;

/**
 * 用户持有器
 * 用于在请求处理过程中存储和获取用户信息
 */
public class UserHolder {
    private static final ThreadLocal<Long> tl = new ThreadLocal<>();

    /**
     * 获取当前用户 ID
     *
     * @return 用户 ID
     */
    public static Long getUserId() {
        return tl.get();
    }

    /**
     * 设置当前用户 ID
     *
     * @param userId 用户 ID
     */
    public static void setUserId(Long userId) {
        tl.set(userId);
    }

    /**
     * 清除当前用户 ID
     */
    public static void clear() {
        tl.remove();
    }
}
