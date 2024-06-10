package com.example.rental.security;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.convert.NumberWithFormat;
import cn.hutool.core.date.DateTime;
import cn.hutool.jwt.JWTPayload;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.example.rental.entity.User;
import com.example.rental.utils.JwtUtils;
import com.example.rental.utils.RedisUtils;
import com.example.rental.utils.ResultCode;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

@Component
public class LoginSuccessHandler implements AuthenticationSuccessHandler {

    @Autowired
    private  RedisUtils redisUtils;



    /**
     * 处理认证成功的逻辑。
     * 当用户成功登录后，系统会调用此方法来处理后续的逻辑，例如生成JSON格式的响应，其中包含用户信息和JWT令牌。
     *
     * @param request  HTTP请求对象，用于获取请求相关的信息。
     * @param response HTTP响应对象，用于设置响应头和发送响应体。
     * @param authentication 成功认证后的认证对象，包含用户信息。
     * @throws IOException 如果在处理响应时发生IO错误。
     * @throws ServletException 如果在处理响应时发生Servlet相关错误。
     */
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {
        // 设置响应类型为JSON，方便前端解析。
        // 设置客户端响应类型json
        response.setContentType("application/json;charset=utf-8");

        // 从认证对象中提取用户信息。
        User user = (User) authentication.getPrincipal();

        // 准备JWT令牌的载荷信息。
        // 生成token的处理
        Map<String, Object> map = new HashMap<>();
        map.put("username",user.getUsername());
        map.put("userid",user.getId());

        // 生成JWT令牌。
        String token = JwtUtils.createToken(map);

        // 解析JWT令牌，获取过期时间。
        NumberWithFormat claim = (NumberWithFormat) JwtUtils.parseToken(token).getClaim(JWTPayload.EXPIRES_AT);
        long expireTime = Convert.toDate(claim).getTime();

        // 构建认证成功后返回对象，包含用户ID、令牌和过期时间。
        AuthenticationResult authenticationResult = new AuthenticationResult(user.getId(),
                ResultCode.SUCCESS, token, expireTime);

        // 将认证结果转换为JSON格式。
        // 获取一个结果
        String result = JSON.toJSONString(authenticationResult,
                SerializerFeature.DisableCircularReferenceDetect);

        // 获取输出流，向客户端发送响应。
        // 获取输出流
        ServletOutputStream outputStream = response.getOutputStream();
        outputStream.write(result.getBytes(StandardCharsets.UTF_8));
        outputStream.flush();
        outputStream.close();

        // 将生成的JWT令牌存储到Redis中，并设置过期时间。
        // 把token存到redis中，设置过期时间
        String tokenKey = "token:"+token;
        long nowTime = DateTime.now().getTime();
        redisUtils.set(tokenKey,token,(expireTime-nowTime)/1000);
    }

}
