package com.example.rental.security;

import cn.hutool.core.util.StrUtil;
import com.example.rental.utils.JwtUtils;
import com.example.rental.utils.RedisUtils;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;


import java.io.IOException;

@Component
public class VerifyTokenFilter extends OncePerRequestFilter {

    @Value("${request.login-url}")
    private String loginUrl;

    @Autowired
    private RedisUtils redisUtils;

    @Autowired
    private CustomerUserDetailsService customerUserDetailsService;

    @Autowired
    private LoginFailHandler loginFailHandler;


    /**
     * 处理过滤逻辑。核心功能是验证请求是否需要认证，如果需要，则进行认证处理。
     * 如果认证失败，调用登录失败处理器处理认证失败情况。
     *
     * @param request  HTTP请求对象，用于获取请求信息。
     * @param response HTTP响应对象，用于发送响应信息。
     * @param filterChain 过滤器链对象，用于继续或终止过滤链中的后续过滤器。
     * @throws ServletException 如果过滤过程中发生Servlet相关异常。
     * @throws IOException 如果过滤过程中发生IO相关异常。
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        // 获取请求的URL
        String url = request.getRequestURI();

        // 判断当前请求URL是否为登录页面URL，如果是，则不需要进行认证处理
        if(StrUtil.equals(url,loginUrl)){
            // 登录页面URL，不进行认证处理
        }else {
            try{
                // 对当前请求进行认证验证，检查是否包含有效的认证令牌
                validateToken(request,response);
            }
            catch (CustomerAuthenticationException e){
                // 认证失败时，调用登录失败处理器处理认证失败情况
                loginFailHandler.onAuthenticationFailure(request,response,e);
            }
        }
        // 继续过滤链的处理
        doFilter(request,response,filterChain);
    }


    private void validateToken(HttpServletRequest request,
                               HttpServletResponse response) throws CustomerAuthenticationException {
        //验证token
        String token = request.getHeader("token");
        if (StrUtil.isEmpty(token)){
            token = request.getParameter("token");
        }
        if (StrUtil.isEmpty(token)){
            throw new CustomerAuthenticationException("token为空");
        }
        //token 如果存在 需要和redis存储的token进行对比
        String tokenKey = "token:" + token;
        String redisToken = redisUtils.get(tokenKey);
        if (StrUtil.isEmpty(redisToken)){
            throw new CustomerAuthenticationException("token不存在");
        }
        if (!StrUtil.equals(token,redisToken)){
            throw new CustomerAuthenticationException("token失效");
        }
        String username = JwtUtils.parseToken(token)
                .getClaim("username").toString();

        if (StrUtil.isEmpty(username)){
            throw new CustomerAuthenticationException("token解析失败");
        }
        UserDetails userDetails = customerUserDetailsService.loadUserByUsername(username);
        if (userDetails == null){
            throw new CustomerAuthenticationException("用户不存在");
        }
        //创建并设置认证信息
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(userDetails,
                        null,userDetails.getAuthorities());
        authenticationToken.setDetails(new WebAuthenticationDetailsSource()
                .buildDetails(request));
        //将认证信息设置到SecurityContextHolder环境中
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
    }




}



