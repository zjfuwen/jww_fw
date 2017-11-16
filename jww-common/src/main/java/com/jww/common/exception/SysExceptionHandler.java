package com.jww.common.exception;

import com.jww.common.base.BaseException;
import com.jww.common.model.ResultModel;
import com.jww.common.util.ResultUtil;
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

    /**
     * 处理自定义异常
     */
    @ExceptionHandler(BaseException.class)
    public ResultModel exceptionHandler(BaseException e) {
        return ResultUtil.fail(e.getCode());
    }
}
