package com.jww.ump.dao.mapper;

import com.baomidou.mybatisplus.plugins.Page;
import com.jww.common.core.base.BaseMapper;
import com.jww.ump.model.UmpUserModel;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author wanyong
 * @description: 用户Mapper
 * @date 2017/11/17 15:51
 */
public interface UmpUserMapper extends BaseMapper<UmpUserModel> {
    public List<UmpUserModel> selectPage(Page<UmpUserModel> page, @Param("searchKey") String searchKey);
    public UmpUserModel selectOne(@Param("id") Long id);
}
