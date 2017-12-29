package com.jww.ump.dao.mapper;

import com.baomidou.mybatisplus.plugins.Page;
import com.jww.common.core.base.BaseMapper;
import com.jww.ump.model.SysUserModel;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 用户表 Mapper 接口
 *
 * @author wanyong
 * @date 2017/11/17 15:51
 */
public interface SysUserMapper extends BaseMapper<SysUserModel> {

    /**
     * 根据关键字分页查询
     *
     * @param page      分页对象
     * @param searchKey 关键字
     * @return List<SysUserModel>
     * @author wanyong
     * @date 2017-12-27 12:06
     */
    List<SysUserModel> selectPage(Page<SysUserModel> page, @Param("searchKey") String searchKey);

    /**
     * 根据用户ID查询
     *
     * @param id 用户ID
     * @return SysUserModel
     * @author wanyong
     * @date 2017-12-27 12:07
     */
    SysUserModel selectOne(@Param("id") Long id);
}
