package com.jww.ump.dao.mapper;

import com.jww.ump.model.SysTreeModel;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 树 Mapper 接口
 * </p>
 *
 * @author wangyuxin
 * @since 2017-12-14
 */
public interface SysTreeMapper extends com.baomidou.mybatisplus.mapper.BaseMapper<SysTreeModel> {
    List<SysTreeModel> selectDeptTree(@Param("id") Long id);

    List<SysTreeModel> selectMenuTree(@Param("id") Long id, @Param("menuType") Integer menuType);
}
