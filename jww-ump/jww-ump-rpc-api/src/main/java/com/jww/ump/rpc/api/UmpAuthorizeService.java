package com.jww.ump.rpc.api;

import com.jww.common.core.base.BaseService;
import com.jww.ump.model.UmpMenuModel;

import java.util.List;

/**
 * 使用一段文字进行描述
 *
 * @author waner
 * @create 2017-11-29
 **/
public interface UmpAuthorizeService {
    public List<String> findPermissionByUserId(Long userId);
}
