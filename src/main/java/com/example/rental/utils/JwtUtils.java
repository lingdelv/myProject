package com.example.rental.utils;


import cn.hutool.core.date.DateTime;
import cn.hutool.jwt.JWT;
import cn.hutool.jwt.JWTPayload;
import cn.hutool.jwt.JWTUtil;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class JwtUtils {
    // 密钥
    public static final String SECRET_KEY = "teacher_shi";

    // 过期时间
    public static final long EXPIRE_TIME = 1000L * 60 * 60 * 5;

    public static String createToken(Map<String, Object> payload) {
        DateTime now = DateTime.now();
        DateTime expire = new DateTime(now.getTime() + EXPIRE_TIME);

        //设置签发时间
        payload.put(JWTPayload.ISSUED_AT, now);
        //设置过期时间
        payload.put(JWTPayload.EXPIRES_AT, expire);
        //设置生效时间,确保token在生效后可以使用
        payload.put(JWTPayload.NOT_BEFORE, now);
        return JWTUtil.createToken(payload, SECRET_KEY.getBytes());
    }

    /**
     * 解析JWT令牌并返回载荷。
     *
     * 本函数接收一个JWT令牌字符串作为输入，验证令牌的有效性和合法性。如果令牌无效或已过期，
     * 将抛出运行时异常。如果令牌有效，将返回令牌的载荷部分。
     *
     * @param token 待解析的JWT令牌字符串。
     * @return 解析后的JWT载荷对象。
     * @throws RuntimeException 如果令牌验证失败或令牌已过期，抛出此异常。
     */
    public static JWTPayload parseToken(String token) {
        // 解析JWT令牌
        JWT jwt = JWTUtil.parseToken(token);

        // 验证JWT令牌的签名，确保令牌未被篡改
        if (!jwt.setKey(SECRET_KEY.getBytes()).verify()){
            throw new RuntimeException("token验证失败");
        }

        // 检查JWT令牌是否在有效期内
        if (!jwt.validate(0)){
            throw new RuntimeException("token已过期");
        }

        // 返回JWT令牌的载荷部分
        return jwt.getPayload();
    }


}
