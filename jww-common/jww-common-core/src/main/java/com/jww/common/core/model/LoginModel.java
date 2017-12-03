package com.jww.common.core.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

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
    @NotBlank(message = "账号不能为空")
    @Size(min = 4, max = 20, message = "账号长度必须在4至20之间")
    private String account;

    /**
     * 密码
     */
    @Getter
    @Setter
    @NotBlank(message = "密码不能为空")
    @Size(min = 6, max = 50, message = "密码长度必须在6至50之间")
    private String password;
}
