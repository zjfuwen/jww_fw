package com.jww.ump.dao.mapper;

import com.jww.common.core.base.BaseMapper;
import com.jww.ump.model.UmpMenuModel;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 菜单 Mapper 接口
 * </p>
 *
 * @author wanyong
 * @since 2017-11-29
 */
public interface UmpMenuMapper extends BaseMapper<UmpMenuModel> {

    /**
     * 根据用户ID查询菜单树
     *
     * @param userId
     * @return List<UmpMenuModel>
     * @author wanyong
     * @date 2017-12-03 00:51
     */
    List<UmpMenuModel> selectMenuTreeByUserId(@Param("userId") Long userId);
}
