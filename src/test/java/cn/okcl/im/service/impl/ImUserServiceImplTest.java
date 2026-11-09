package cn.okcl.im.service.impl;

import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootTest
class ImUserServiceImplTest {
    @Resource
    private PasswordEncoder passwordEncoder;

    @Test
    void register() {
        //重置密码
        String password = "a12345678..";
        String encodedPassword = passwordEncoder.encode(password);
        System.out.println(encodedPassword);
    }
}