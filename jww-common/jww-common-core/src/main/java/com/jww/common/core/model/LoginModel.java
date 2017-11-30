package com.jww.common.core.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 使用一段文字进行描述
 *
 * @author waner
 * @create 2017-11-30
 **/
@ToString
public class LoginModel {
    /**
     * 用户名
     */
    @Getter
    @Setter
    private String account;

    /**
     * 密码
     */
    @Getter
    @Setter
    private String password;
}
