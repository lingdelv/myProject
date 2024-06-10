package com.example.rental.security;

import com.alibaba.fastjson.JSON;
import com.example.rental.utils.Result;
import com.example.rental.utils.ResultCode;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Component
public class LoginFailHandler implements AuthenticationFailureHandler {
    /**
     * 处理认证失败的情况。
     * 当用户尝试登录但认证失败时，此方法将被调用。它根据不同的异常类型向用户返回相应的错误信息。
     *
     * @param request  HTTP请求对象
     * @param response HTTP响应对象
     * @param exception 认证失败时抛出的异常
     * @throws IOException 如果操作输入/输出流时发生错误
     * @throws ServletException 如果发生与Servlet相关的错误
     */
    @Override
    public void onAuthenticationFailure(HttpServletRequest request,
                                        HttpServletResponse response,
                                        AuthenticationException exception) throws IOException, ServletException {
        // 设置响应类型为JSON，字符集为UTF-8
        response.setContentType("application/json;charset=utf-8");
        // 获取响应的输出流
        ServletOutputStream outputStream = response.getOutputStream();

        // 默认的错误代码和消息
        int code = ResultCode.ERROR;
        String msg = null;

        // 根据不同的异常类型设置错误代码和消息
        if (exception instanceof AccountExpiredException){
            code = ResultCode.UNAUTHENTICATED;
            msg = "账户过期";
        } else if (exception instanceof BadCredentialsException) {
            code = ResultCode.UNAUTHENTICATED;
            msg = "账户或密码错误";
        } else if (exception instanceof CredentialsExpiredException) {
            code = ResultCode.UNAUTHENTICATED;
            msg = "密码过期";
        } else if (exception instanceof DisabledException) {
            code = ResultCode.UNAUTHORIZED;
            msg = "账户被禁用";
        } else if (exception instanceof LockedException) {
            code = ResultCode.UNAUTHORIZED;
            msg = "账户被锁定";
        } else if (exception instanceof InternalAuthenticationServiceException) {
            code = ResultCode.UNAUTHENTICATED;
            msg = "账户不存在";
        } else if (exception instanceof CustomerAuthenticationException){
            code = ResultCode.UNAUTHORIZED;
            msg = exception.getMessage();
        }
        else {
            msg = "登录失败";
        }

        // 将错误结果转换为JSON字符串
        String result = JSON.toJSONString(Result.fail().setCode(code).setMessage(msg));
        // 将JSON字符串写入输出流
        outputStream.write(result.getBytes(StandardCharsets.UTF_8));
        // 刷新输出流
        outputStream.flush();
        // 关闭输出流
        outputStream.close();
    }

}
