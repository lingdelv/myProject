package com.example.rental.security;


import com.alibaba.fastjson.JSON;
import com.example.rental.utils.Result;
import com.example.rental.utils.ResultCode;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * 匿名用户无权限访问处理器
 */
@Component
public class CustomerAnonymousEntryPoint implements AuthenticationEntryPoint {
    /**
     * 处理认证失败的情况。
     * 当用户尝试访问需要身份验证的资源但未提供有效凭证时，会调用此方法。
     * 它的目的是向客户端发送一个表示认证失败的响应。
     *
     * @param request  当前的HTTP请求对象，用于获取请求信息。
     * @param response 当前的HTTP响应对象，用于向客户端发送响应。
     * @param authException 认证异常对象，包含关于认证失败的详细信息。
     * @throws IOException 如果在处理响应时发生I/O错误。
     * @throws ServletException 如果在处理响应时发生Servlet相关错误。
     */
    @Override
    public void commence(HttpServletRequest request,
                         HttpServletResponse response,
                         AuthenticationException authException) throws IOException, ServletException {
        // 设置响应的类型为JSON，确保客户端可以以适当的方式解析响应。
        response.setContentType("application/json;charset=utf-8");

        // 获取响应的输出流，用于向客户端发送数据。
        ServletOutputStream out = response.getOutputStream();

        // 构建一个表示认证失败的JSON对象，包括错误代码和消息。
        String result = JSON.toJSONString(Result.fail().setCode(ResultCode.UNAUTHORIZED).setMessage("匿名用户无权访问"));

        // 将JSON对象转换为字节数组并写入输出流。
        out.write(result.getBytes(StandardCharsets.UTF_8));

        // 刷新输出流，确保所有数据都被写入。
        out.flush();

        // 关闭输出流，释放相关资源。
        out.close();
    }

}
