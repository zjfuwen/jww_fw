package com.jww.common.core.model;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * 令牌
 *
 * @author wanyong
 * @date 2017/11/23 22:14
 */
public class TokenModel implements Serializable {
    private static final long serialVersionUID = 8238083454490855723L;

    @Getter
    @Setter
    private String value;

    @Getter
    @Setter
    private Long time;
}
