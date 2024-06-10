package com.example.rental.security;


import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.security.SecureRandom;

/**
 * 配置类，用于设置和提供BCrypt密码编码器的参数。
 */
@Configuration
@Data
public class PasswordConfig {
    @Value("${encoder.ctype.strength}")
    private  int strength;

    @Value("${encoder.ctype.secret}")
    private  String secret;

    @Bean
    public  BCryptPasswordEncoder bCryptPasswordEncoder() {
        SecureRandom secureRandom = new SecureRandom(secret.getBytes());
        return new BCryptPasswordEncoder(strength, secureRandom);
    }

    //测试代码
    public static void main(String[] args) {
        BCryptPasswordEncoder bCryptPasswordEncoder = new PasswordConfig().bCryptPasswordEncoder();
        System.out.println(bCryptPasswordEncoder.encode("123456"));
        System.out.println(bCryptPasswordEncoder.encode("123456"));
        System.out.println(bCryptPasswordEncoder.matches("123456",
                "$2a$06$0c/ndr5FZ87vzmlIVJdUd.sEtG0neP5cgvsSljCBQWxDCemLzp5cq"));
    }
}


