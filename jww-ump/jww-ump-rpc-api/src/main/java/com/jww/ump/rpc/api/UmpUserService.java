package com.jww.ump.rpc.api;

import com.jww.common.core.base.BaseService;
import com.jww.ump.model.UmpUserModel;

/**
 * 用户管理服务
 *
 * @author wanyong
 * @date 2017/11/17 16:43
 */
public interface UmpUserService extends BaseService<UmpUserModel> {

    public UmpUserModel findByAccount(String account);
}
