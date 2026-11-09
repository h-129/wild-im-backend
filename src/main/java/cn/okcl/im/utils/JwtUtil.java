package cn.okcl.im.utils;

import cn.okcl.im.properties.JwtProperties;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Map;

/**
 * JWT 工具类
 */
@Component
public class JwtUtil {
    // 静态变量供 static 方法使用
    private static JwtProperties staticJwtProperties;
    @Resource
    private JwtProperties jwtProperties;

    /**
     * 根据 Map 载荷与动态过期时间生成 Token
     */
    public String generateToken(Map<String, Object> claims) {
        long expireMillis = staticJwtProperties.getExpire();

        Algorithm algorithm = Algorithm.HMAC256(staticJwtProperties.getSecret());

        return JWT.create()
                .withIssuer(staticJwtProperties.getIssuer())
                .withPayload(claims)
                .withExpiresAt(new Date(System.currentTimeMillis() + expireMillis))
                .sign(algorithm);
    }

    /**
     * 验证并解析 JWT Token
     *
     * @param token JWT Token
     * @return 解析后的 JWT 对象
     */
    public DecodedJWT verifyToken(String token) {
        Algorithm algorithm = Algorithm.HMAC256(staticJwtProperties.getSecret());
        JWTVerifier verifier = JWT.require(algorithm)
                .withIssuer(staticJwtProperties.getIssuer())
                .build();
        return verifier.verify(token);
    }

    /**
     * 从 Token 中提取用户 ID
     *
     * @param token JWT Token
     * @return 用户 ID
     */
    public Long getUserId(String token) {
        return verifyToken(token).getClaim("userId").asLong();
    }

    /**
     * 从 Token 中提取用户名
     *
     * @param token JWT Token
     * @return 用户名
     */
    public String getUsername(String token) {
        return verifyToken(token).getClaim("username").asString();
    }

    /**
     * 初始化 JWT 配置
     */
    @PostConstruct
    public void init() {
        staticJwtProperties = jwtProperties;
    }
}
