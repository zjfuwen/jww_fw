package com.jww.ump.rpc.api;

import java.util.List;

/**
 * 使用一段文字进行描述
 *
 * @author waner
 * @create 2017-11-29
 **/
public interface SysAuthorizeService {

    /**
     * 根据用户ID获取权限
     *
     * @param userId
     * @return List<String>
     * @author wanyong
     * @date 2017-12-02 22:49
     */
    List<String> queryPermissionsByUserId(Long userId);
}
