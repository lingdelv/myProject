package com.example.rental.config;

import com.example.rental.utils.Result;
import com.example.rental.utils.ResultCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 全局异常处理类，用于捕获并处理应用程序中抛出的异常。
 * 使用@RestControllerAdvice注解标识该类为异常处理的全局控制器。
 * 使用@Slf4j注解引入日志门面，用于记录异常信息。
 */
@RestControllerAdvice
@Slf4j
public class GlobalException {

    /**
     * 捕获所有类型的异常。
     * 使用@ExceptionHandler注解指定该方法处理所有抛出的Exception异常。
     * 当捕获到异常时，记录异常信息到日志，并返回一个包含异常信息的失败结果。
     *
     * @param e 抛出的异常对象，用于记录和处理异常信息。
     * @return 返回一个Result对象，表示处理异常后的结果，此处设置为失败结果并携带异常信息。
     */
    @ExceptionHandler(Exception.class)
    public Result handleException(Exception e) {
        log.error("异常信息：{}", e.getMessage());
        log.error("异常堆栈：{}", e.toString());
        return Result.fail().setMessage(e.getMessage());
    }

}

