package cn.okcl.im.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * JWT 配置类
 */
@Data
@Component
@ConfigurationProperties(prefix = "jwt")
public class JwtProperties {
    /**
     * 密钥
     */
    private String secret;
    /**
     * 签发人
     */
    private String issuer;
    /**
     * 默认过期时间（毫秒）
     */
    private long expire;
}
