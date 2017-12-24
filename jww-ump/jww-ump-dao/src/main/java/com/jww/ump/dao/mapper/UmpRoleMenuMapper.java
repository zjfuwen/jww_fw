package com.jww.ump.dao.mapper;

import com.jww.common.core.base.BaseMapper;
import com.jww.ump.model.UmpRoleMenuModel;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 角色授权表 Mapper 接口
 * </p>
 *
 * @author wanyong
 * @since 2017-12-17
 */
public interface UmpRoleMenuMapper extends BaseMapper<UmpRoleMenuModel> {

    List<Long> selectMenuIdListByRoleId(@Param("roleId") Long roleId);
}
