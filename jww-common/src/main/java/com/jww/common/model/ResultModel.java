package com.jww.common.model;

import lombok.AllArgsConstructor;

/**
 * @author wanyong
 * @description: 返回结果类
 * @date 2017/11/9 23:45
 */
@AllArgsConstructor
public class ResultModel<T> {

    /**
     * 状态码
     */
    public int code;

    /**
     * 描述
     */
    public String message;

    /**
     * 数据结果集
     */
    public T data;
}
