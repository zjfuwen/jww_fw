package com.jww.ump.dao.mapper;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.jww.common.core.base.BaseMapper;
import com.jww.ump.model.SysRoleModel;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 角色信息表 Mapper 接口
 *
 * @author wanyong
 * @date 2017-12-17
 */
public interface SysRoleMapper extends BaseMapper<SysRoleModel> {

    /**
     * 分页查询
     *
     * @param page    分页实体
     * @param wrapper wrapper条件
     * @return List<SysRoleModel>
     * @author wanyong
     * @date 2017-12-27 12:03
     */
    List<SysRoleModel> selectRoleList(Pagination page, @Param("ew") Wrapper<SysRoleModel> wrapper);
}
