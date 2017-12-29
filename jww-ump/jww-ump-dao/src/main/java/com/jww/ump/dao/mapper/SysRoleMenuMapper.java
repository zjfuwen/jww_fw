package com.jww.ump.dao.mapper;

import com.jww.common.core.base.BaseMapper;
import com.jww.ump.model.SysRoleMenuModel;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 角色授权表 Mapper 接口
 *
 * @author wanyong
 * @date 2017-12-17
 */
public interface SysRoleMenuMapper extends BaseMapper<SysRoleMenuModel> {

    /**
     * 根据角色ID查询对应菜单ID集合
     *
     * @param roleId 角色ID
     * @return List<Long>
     * @author wanyong
     * @date 2017-12-27 12:05
     */
    List<Long> selectMenuIdListByRoleId(@Param("roleId") Long roleId);
}
