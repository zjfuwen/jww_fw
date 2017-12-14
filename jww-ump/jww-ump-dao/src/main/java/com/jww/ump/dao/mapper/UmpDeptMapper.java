package com.jww.ump.dao.mapper;

import com.baomidou.mybatisplus.plugins.Page;
import com.jww.common.core.base.BaseMapper;
import com.jww.ump.model.UmpDeptModel;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 部门 Mapper 接口
 * </p>
 *
 * @author wanyong
 * @since 2017-11-25
 */
public interface UmpDeptMapper extends BaseMapper<UmpDeptModel> {
    public List<UmpDeptModel> selectPage(Page<UmpDeptModel> page,@Param("dept_name") String dept_name);
    public UmpDeptModel selectOne(@Param("id") Long id);
}
