package com.jww.common.web.handler;

import com.fasterxml.jackson.databind.ser.Serializers;
import com.jww.common.constant.Constants;
import com.jww.common.exception.BaseException;
import com.jww.common.web.ResultModel;
import com.jww.common.web.util.ResultUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 异常处理器
 *
 * @author wanyong
 * @date 2017/11/12 16:07
 */
@Slf4j
@RestControllerAdvice
public class SysExceptionHandler {

    public SysExceptionHandler() {
        log.info("========== 初始化SysExceptionHandler ==========");
    }

    /**
     * 处理自定义异常
     */
    @ExceptionHandler(Exception.class)
    public ResultModel exceptionHandler(Exception e) {
        log.error(e.getMessage(), e);
        if (e instanceof BaseException) {
            BaseException baseException = (BaseException) e;
            return ResultUtil.fail(baseException.getCode());
        }
        return ResultUtil.fail(Constants.ResultCodeEnum.INTERNAL_SERVER_ERROR);
    }
}
