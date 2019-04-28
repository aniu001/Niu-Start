package com.rjwl.api.common.exception;

import com.rjwl.api.common.enums.JsonCodeEnum;
import com.rjwl.api.common.json.JsonResp;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.AuthorizationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 全局异常处理,所有的异常都放在这里进行处理,无需在每个地方try catch
 *
 * @author aniu
 */

@RestControllerAdvice
public class ExceptionAdvice {

    private static final Logger LOGGER = LogManager.getLogger(ExceptionAdvice.class);

    /**
     * 信息无法读取
     *
     * @param e
     * @return
     */

    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public JsonResp<?> handleHttpMessageNotReadableException(Exception e) {
        e.printStackTrace();
        return new JsonResp(JsonCodeEnum.UNABLE_READ);
    }

    /**
     * 处理参数异常
     *
     * @param e
     * @return
     */

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public JsonResp<?> handleMethodArgumentNotValidException(Exception e) {
        return new JsonResp(JsonCodeEnum.ARGUMENT_NOT_VALID);
    }

    /**
     * 处理自定义异常
     *
     * @param e
     * @return
     */

    @ExceptionHandler(IException.class)
    @ResponseStatus(HttpStatus.EXPECTATION_FAILED)
    public JsonResp<?> handleIException(IException e) {
        return new JsonResp(JsonCodeEnum.MIONE_EXCEPTION);
    }

    /**
     * 数学计算错误
     *
     * @param e
     * @return
     */

    @ExceptionHandler(ArithmeticException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public JsonResp<?> handleArithmeticException(ArithmeticException e) {
        return new JsonResp(JsonCodeEnum.SERVER_EXCEPTION);
    }

    /**
     * 登陆错误
     *
     * @param e
     * @return
     */

    @ExceptionHandler(AuthenticationException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public JsonResp<?> handleAuthenticationException(AuthenticationException e) {
        LOGGER.debug(e);
        if (e instanceof IncorrectCredentialsException) {
            return new JsonResp(JsonCodeEnum.INCORRECT_CREDENTIALS);
        }
        if (e instanceof UnknownAccountException) {
            return new JsonResp(JsonCodeEnum.UNKNOWN_ACCOUNT);
        }
        return new JsonResp(JsonCodeEnum.LOGIN_FAIL);
    }

    /**
     * 没有权限——shiro
     *
     * @param e
     * @return
     */

    @ExceptionHandler(AuthorizationException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public JsonResp<?> handleAuthorizationException(AuthorizationException e) {
        return new JsonResp(JsonCodeEnum.NO_AUTHORIZATION);
    }

    /**
     * 未拦截错误处理
     *
     * @param e
     * @return
     */
    @ExceptionHandler(Exception.class)
    public JsonResp<?> handleAllException(Exception e) {
        LOGGER.debug(e);
        return new JsonResp(JsonCodeEnum.SERVER_EXCEPTION);
    }
}
