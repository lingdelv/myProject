//package com.example.rental.security;
//
//import cn.hutool.core.util.StrUtil;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Component;
//
//@Component
//public class CustomerPasswordEncoder implements PasswordEncoder {
//
//
//
//    @Override
//    public String encode(CharSequence rawPassword) {
//
//        return rawPassword.toString();
//    }
//
//    @Override
//    public boolean matches(CharSequence rawPassword, String encodedPassword) {
////        String rPassword = passwordConfig.bCryptPasswordEncoder().encode(rawPassword);
//        return StrUtil.equals(rawPassword.toString(), encodedPassword);
//    }
//}
