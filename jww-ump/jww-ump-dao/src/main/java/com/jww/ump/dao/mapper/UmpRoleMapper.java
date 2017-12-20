package com.jww.ump.dao.mapper;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.jww.common.core.base.BaseMapper;
import com.jww.ump.model.UmpRoleModel;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 角色信息表 Mapper 接口
 * </p>
 *
 * @author wanyong
 * @since 2017-12-17
 */
public interface UmpRoleMapper extends BaseMapper<UmpRoleModel> {

    List<UmpRoleModel> selectRoleList(Pagination page, @Param("ew") Wrapper<UmpRoleModel> wrapper);
}
