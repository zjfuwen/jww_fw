package com.jww.ump.rpc.api;

import com.baomidou.mybatisplus.plugins.Page;
import com.jww.common.core.base.BaseService;
import com.jww.ump.model.UmpUserModel;

import java.util.List;
import java.util.Map;

/**
 * 用户管理服务
 *
 * @author wanyong
 * @date 2017/11/17 16:43
 */
public interface UmpUserService extends BaseService<UmpUserModel> {

    /**
     * 根据账号查找用户
     *
     * @param account
     * @return UmpUserModel
     * @author wanyong
     * @date 2017-12-05 12:48
     */
    UmpUserModel findByAccount(String account);

    /**
     * 分页查找所有用户
     *
     * @param page
     * @return Page<UmpUserModel>
     * @author wanyong
     * @date 2017/12/4 14:45
     */
    Page<UmpUserModel> findListPage(Page<UmpUserModel> page);
}
