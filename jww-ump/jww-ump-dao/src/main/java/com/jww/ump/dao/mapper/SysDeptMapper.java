package com.jww.ump.dao.mapper;

import com.baomidou.mybatisplus.plugins.Page;
import com.jww.common.core.base.BaseMapper;
import com.jww.ump.model.SysDeptModel;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 部门 Mapper 接口
 *
 * @author wanyong
 * @date 2017-11-25
 */
public interface SysDeptMapper extends BaseMapper<SysDeptModel> {

    /**
     * 分页查询
     * @param page 分页参数
     * @param deptName 部门名称
     * @return List<SysDeptModel>
     * @author wanyong
     * @date 2017-12-27 11:57
     */
    List<SysDeptModel> selectPage(Page<SysDeptModel> page, @Param("deptName") String deptName);

    /**
     * 根据部门ID查询
     *
     * @param id 部门ID
     * @return SysDeptModel
     * @author wanyong
     * @date 2017-12-27 11:58
     */
    SysDeptModel selectOne(@Param("id") Long id);
}
