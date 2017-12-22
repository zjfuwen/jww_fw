package com.jww.common.core.exception;

import com.jww.common.core.Constants;

/**
 * 业务异常类
 *
 * @author wanyong
 * @date 2017/11/10 11:24
 */
public class BusinessException extends BaseException {

    public BusinessException() {
        super();
    }

    public BusinessException(Throwable ex) {
        super(ex);
    }

    public BusinessException(String message) {
        super(message);
    }

    public BusinessException(String message, Throwable ex) {
        super(message, ex);
    }

    @Override
    public Constants.ResultCodeEnum getCode() {
        return Constants.ResultCodeEnum.INTERNAL_SERVER_ERROR;
    }
}
