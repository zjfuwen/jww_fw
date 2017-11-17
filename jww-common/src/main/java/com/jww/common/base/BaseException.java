package com.jww.common.base;

import com.jww.common.Constants;

/**
 * @author wanyong
 * @description: 统一异常基类
 * @date 2017/11/9 23:02
 */
public abstract class BaseException extends RuntimeException {

    public BaseException() {
    }

    public BaseException(Throwable ex) {
        super(ex);
    }

    public BaseException(String message) {
        super(message);
    }

    public BaseException(String message, Throwable ex) {
        super(message, ex);
    }

    /**
     * @return ResultCodeEnum 错误码
     * @description: 获取错误码
     * @author wanyong
     * @date 2017/11/10 09:38
     */
    public abstract Constants.ResultCodeEnum getCode();

}