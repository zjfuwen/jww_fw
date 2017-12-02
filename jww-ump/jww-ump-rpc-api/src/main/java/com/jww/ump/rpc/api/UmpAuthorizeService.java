package com.jww.ump.rpc.api;

import com.jww.common.core.base.BaseService;
import com.jww.ump.model.UmpMenuModel;
import com.jww.ump.model.UmpUserMenuModel;

import java.util.List;
import java.util.Map;

/**
 * 使用一段文字进行描述
 *
 * @author waner
 * @create 2017-11-29
 **/
public interface UmpAuthorizeService {

    /**
     * 根据用户ID获取权限
     *
     * @param userId
     * @return List<String>
     * @author wanyong
     * @date 2017-12-02 22:49
     */
    public List<String> findPermissionByUserId(Long userId);
}
