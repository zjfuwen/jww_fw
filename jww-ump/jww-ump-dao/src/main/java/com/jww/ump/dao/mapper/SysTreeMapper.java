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

    /**
     * 查询部门树
     *
     * @param id
     * @return java.util.List<com.jww.ump.model.SysTreeModel>
     * @author RickyWang
     * @date 18/1/1 15:10:01
     */
    List<SysTreeModel> selectDeptTree(@Param("id") Long id);

    /**
     * 查询菜单树
     *
     * @param id
     * @param menuType
     * @return java.util.List<com.jww.ump.model.SysTreeModel>
     * @author RickyWang
     * @date 18/1/1 15:10:21
     */
    List<SysTreeModel> selectMenuTree(@Param("id") Long id, @Param("menuType") Integer menuType);
}
