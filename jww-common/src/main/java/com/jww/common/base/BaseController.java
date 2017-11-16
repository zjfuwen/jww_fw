package com.jww.common.base;

import com.jww.common.util.WebUtil;
import lombok.extern.slf4j.Slf4j;

/**
 * @author wanyong
 * @description: TODO
 * @date 2017/11/9 23:45
 */
@Slf4j
public abstract class BaseController {

    /**
     * 获取当前用户Id
     */
    protected Long getCurrUser() {
        return WebUtil.getCurrentUser();
    }
}
