package com.example.rental.security;


import jakarta.annotation.Resource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Resource
    private LoginFailHandler loginFailHandler;

    @Resource
    private LoginSuccessHandler loginSuccessHandler;

    @Resource
    private CustomerAccessDeniedHandler customerAccessDeniedHandler;

    @Resource
    private CustomerAnonymousEntryPoint customerAnonymousEntryPoint;

    @Resource
    private CustomerUserDetailsService customerUserDetailsService;

    @Resource
    private VerifyTokenFilter verifyTokenFilter;

    /**
     * 配置安全过滤链
     *
     * 该方法配置了Spring Security的HTTP安全设置，用于保护应用程序的REST API。
     * 它定义了登录处理URL、成功和失败的处理器，以及会话管理策略。
     * 此外，它还配置了请求授权规则、异常处理和跨域请求支持。
     *
     * @param http Security配置对象，用于构建安全过滤链
     * @return 构建后的SecurityFilterChain对象
     * @throws Exception 如果配置过程中出现错误
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        //登入前过滤配置
        http.addFilterBefore(verifyTokenFilter, UsernamePasswordAuthenticationFilter.class);

        // 配置表单登录
        // 登录处理URL被设置为"rental/user/login"，成功和失败的处理器分别被设置为loginSuccessHandler和loginFailHandler
        http.formLogin(formLogin -> formLogin
                .loginProcessingUrl("/rental/user/login")
                .successHandler(loginSuccessHandler)
                .failureHandler(loginFailHandler)
        )
        // 配置会话管理
        // 设置会话创建策略为STATELESS，意味着不维护客户端会话
        .sessionManagement(
                sessionManagement -> sessionManagement
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        )
        // 配置请求授权
        // 允许所有访问"rental/user/login"的请求，其他任何请求都需要身份验证
        .authorizeHttpRequests(
                request -> request
                        .requestMatchers("/rental/user/login").permitAll()
                        .anyRequest().authenticated()
        )
        // 配置异常处理
        // 设置未授权访问的处理入口为customerAnonymousEntryPoint，访问被拒绝时的处理器为customerAccessDeniedHandler
        .exceptionHandling(
                exceptionHandling -> exceptionHandling
                        .accessDeniedHandler(customerAccessDeniedHandler)
                        .authenticationEntryPoint(customerAnonymousEntryPoint)

        )
        // 配置跨域请求支持，默认设置
        .cors(Customizer.withDefaults())
        // 禁用CSRF保护
        .csrf(csrf -> csrf.disable())
        // 设置用户详情服务为customerUserDetailsService
        .userDetailsService(customerUserDetailsService);

        // 返回构建后的SecurityFilterChain对象
        return http.build();
    }


        //旧版本写法
        /*http.formLogin()
                .loginProcessingUrl("rental/user/login")
                .successHandler(loginSuccessHandler)
                .failureHandler(loginFailHandler)
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeHttpRequests()
                .requestMatchers("rental/user/login")
                .permitAll()
                .anyRequest().authenticated()
                .and()
                .exceptionHandling()
                .authenticationEntryPoint(customerAnonymousEntryPoint)
                .accessDeniedHandler(customerAccessDeniedHandler)
                .cors()
                .csrf().disable()
                .userDetailsService(customerUserDetailsService);
        return http.build();
    }*/
}
