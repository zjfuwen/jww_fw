package com.jww.ump.rpc.api;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.jww.common.core.base.BaseService;
import com.jww.ump.model.UmpMenuModel;

import java.util.List;

/**
 * <p>
 * 菜单 服务类
 * </p>
 *
 * @author wanyong
 * @since 2017-11-29
 */
public interface UmpMenuService extends BaseService<UmpMenuModel> {

    /**
     * 查找所有菜单
     *
     * @return List<UmpMenuModel>
     * @author wanyong
     * @date 2017-12-02 13:59
     */
    public List<UmpMenuModel> queryList();

    /**
     * 根据用户ID查找菜单树
     *
     * @param userId
     * @return List<UmpMenuModel>
     * @author wanyong
     * @date 2017-12-03 00:56
     */
    public List<UmpMenuModel> queryMenuTreeByUserId(Long userId);
}
