package cn.okcl.im.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * 密码加密配置
 */
@Configuration
public class PasswordConfig {
    /**
     * 密码加密器
     *
     * @return 密码加密器实例
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        //使用BCrypt强哈希算法加密密码，确保密码存储在数据库时是安全的
        return new BCryptPasswordEncoder();
    }
}
