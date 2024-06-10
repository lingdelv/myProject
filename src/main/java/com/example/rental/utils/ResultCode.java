package com.example.rental.utils;

public class ResultCode {
    /**
     * 常量SUCCESS表示请求处理成功的状态码。
     * 当服务器成功处理了客户端的请求时，可以返回此状态码。
     */
    public static final Integer SUCCESS = 200;

    /**
     * 常量ERROR表示请求处理出错的状态码。
     * 当服务器在处理请求时发生内部错误或无法处理请求时，可以返回此状态码。
     */
    public static final Integer ERROR = 500;

    /**
     * 常量UNAUTHORIZED表示请求未授权的状态码。
     * 当客户端试图访问受保护的资源而没有提供有效的身份验证时，服务器可以返回此状态码。
     */
    public static final Integer UNAUTHORIZED = 401;

    /**
     * 常量FORBIDDEN表示请求被禁止的状态码。
     * 当客户端没有足够的权限访问请求的资源时，服务器可以返回此状态码。
     */
    public static final Integer FORBIDDEN = 403;

    /**
     * 常量UNAUTHENTICATED表示请求需要进行用户验证的状态码。
     * 此状态码表示客户端已进行身份验证，但请求仍需要进一步的用户验证。
     */
    public static final Integer UNAUTHENTICATED = 402;

}
