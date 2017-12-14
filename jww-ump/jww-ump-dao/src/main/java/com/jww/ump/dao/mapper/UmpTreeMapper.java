package com.jww.ump.dao.mapper;

import com.jww.ump.model.UmpTreeModel;
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
public interface UmpTreeMapper extends com.baomidou.mybatisplus.mapper.BaseMapper<UmpTreeModel>{
    public List<UmpTreeModel> selectDeptTree(@Param("id") Long id);
}
