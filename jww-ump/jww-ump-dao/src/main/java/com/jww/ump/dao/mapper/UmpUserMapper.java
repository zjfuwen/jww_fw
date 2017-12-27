package com.jww.ump.dao.mapper;

import com.baomidou.mybatisplus.plugins.Page;
import com.jww.common.core.base.BaseMapper;
import com.jww.ump.model.UmpUserModel;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 用户表 Mapper 接口
 *
 * @author wanyong
 * @date 2017/11/17 15:51
 */
public interface UmpUserMapper extends BaseMapper<UmpUserModel> {

    /**
     * 根据关键字分页查询
     *
     * @param page      分页对象
     * @param searchKey 关键字
     * @return List<UmpUserModel>
     * @author wanyong
     * @date 2017-12-27 12:06
     */
    List<UmpUserModel> selectPage(Page<UmpUserModel> page, @Param("searchKey") String searchKey);

    /**
     * 根据用户ID查询
     *
     * @param id 用户ID
     * @return UmpUserModel
     * @author wanyong
     * @date 2017-12-27 12:07
     */
    UmpUserModel selectOne(@Param("id") Long id);
}
