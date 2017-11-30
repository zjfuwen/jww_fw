package com.jww.common.core.exception;

import com.jww.common.core.Constants;

/**
 * @author wanyong
 * @description: 登录异常类
 * @date 2017/11/10 11:24
 */
public class LoginException extends BaseException {

    public LoginException(Throwable ex) {
        super(ex);
    }

    public LoginException(String message) {
        super(message);
    }

    public LoginException(String message, Throwable ex) {
        super(message, ex);
    }

    @Override
    public Constants.ResultCodeEnum getCode() {
        return Constants.ResultCodeEnum.LOGIN_FAIL;
    }
}
