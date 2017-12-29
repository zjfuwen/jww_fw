package com.jww.ump.dao.mapper;

import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 权限mapper
 *
 * @author wanyong
 * @date 2017-11-29
 **/
public interface SysAuthorizeMapper {

    /**
     * 根据用户ID获取用户权限集合
     *
     * @param userId 用户ID
     * @return List<String>
     * @author wanyong
     * @date 2017-12-27 11:55
     */
    List<String> selectPermissionsByUserId(@Param("userId") Long userId);
}
