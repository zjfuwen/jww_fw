package com.jww.common.exception;

import com.jww.common.constant.Constants;

/**
 * @author wanyong
 * @description: 业务异常类
 * @date 2017/11/10 11:24
 */
public class BusinessException extends BaseException {

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
