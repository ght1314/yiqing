package com.demo.exception;

import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.web.bind.annotation.*;

/**
 * 权限不足，未获得授权异常统一处理类
 */
@RestControllerAdvice
public class UnathcException {
    @ExceptionHandler(UnauthorizedException.class)
    public String handleShiroException(Exception e)
    {
        return "没有权限";
    }

    @ExceptionHandler(AuthorizationException.class)
    public String authorizationExceptionException(Exception e)
    {
        return "权限认证失败";
    }
}
