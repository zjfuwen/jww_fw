package com.jww.ump.rpc.api;

import com.baomidou.mybatisplus.plugins.Page;
import com.jww.common.core.base.BaseService;
import com.jww.ump.model.UmpMenuModel;
import com.jww.ump.model.UmpTreeModel;

import java.io.Serializable;
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
    List<UmpMenuModel> queryList();

    /**
     * 分页查询所有菜单
     *
     * @param page
     * @return Page<UmpMenuModel>
     * @author shadj
     * @date 2017/12/18 13:52
     */
    Page<UmpMenuModel> queryListPage(Page<UmpMenuModel> page);

    /**
     * 根据用户ID查找菜单树（包含目录和菜单，不包含按钮）
     *
     * @param userId
     * @return List<UmpMenuModel>
     * @author wanyong
     * @date 2017-12-03 00:56
     */
    List<UmpTreeModel> queryMenuTreeByUserId(Long userId);

    /**
     * 查找功能菜单树（包含目录、菜单和按钮）
     *
     * @return List<TreeModel>
     * @author wanyong
     * @date 2017-12-19 11:14
     */
    List<UmpTreeModel> queryFuncMenuTree();

    /**
     * 根据角色ID查找功能菜单树（包含目录、菜单和按钮）
     *
     * @param roleId
     * @return List<TreeModel>
     * @author wanyong
     * @date 2017-12-19 11:14
     */
    List<UmpTreeModel> queryFuncMenuTree(Long roleId);

    /**
     * 查询菜单树，供页面选择父菜单使用，过滤自己及子菜单
     *
     * @param  id
     * @return  List<UmpTreeModel>
     * @author shadj
     * @date 2017/12/22 22:59
     */
    List<UmpTreeModel> queryTree(Long id);
}
