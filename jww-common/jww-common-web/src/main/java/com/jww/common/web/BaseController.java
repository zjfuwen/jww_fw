package com.jww.common.web;

import com.jww.common.web.util.WebUtil;
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
