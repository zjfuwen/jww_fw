package com.jww.ump.rpc.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.jww.common.core.base.BaseServiceImpl;
import com.jww.ump.dao.mapper.UmpMenuMapper;
import com.jww.ump.model.UmpMenuModel;
import com.jww.ump.model.UmpTreeModel;
import com.jww.ump.rpc.api.UmpMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 菜单 服务实现类
 * </p>
 *
 * @author wanyong
 * @since 2017-11-29
 */
@Service("umpMenuService")
public class UmpMenuServiceImpl extends BaseServiceImpl<UmpMenuMapper, UmpMenuModel> implements UmpMenuService {

    @Autowired
    private UmpMenuMapper umpMenuMapper;

    @Override
    public List<UmpMenuModel> queryList() {
        UmpMenuModel umpMenuModel = new UmpMenuModel();
        // 状态为：启用
        umpMenuModel.setEnable(1);
        // 是否删除：否
        umpMenuModel.setIsDel(0);
        EntityWrapper<UmpMenuModel> entityWrapper = new EntityWrapper<>(umpMenuModel);
        entityWrapper.orderBy(" parent_id, sort_no ", true);
        return super.selectList(entityWrapper);
    }

    @Override
    public List<UmpTreeModel> queryMenuTreeByUserId(Long userId) {
        List<UmpMenuModel> umpMenuModelList = umpMenuMapper.selectMenuTreeByUserId(userId);
        return convertTreeData(umpMenuModelList);
    }

    @Override
    public List<UmpTreeModel> queryFuncMenuTree() {
        List<UmpMenuModel> umpMenuModelList = queryList();
        return convertTreeData(umpMenuModelList);
    }

    /**
     * 获取树模型结构数据
     *
     * @param umpMenuModelList
     * @return List<UmpTreeModel>
     * @author wanyong
     * @date 2017-12-19 10:55
     */
    private List<UmpTreeModel> convertTreeData(List<UmpMenuModel> umpMenuModelList) {
        Map<Long, List<UmpTreeModel>> map = new HashMap<>(3);
        for (UmpMenuModel umpMenuModel : umpMenuModelList) {
            if (umpMenuModel != null && map.get(umpMenuModel.getParentId()) == null) {
                List<UmpTreeModel> children = new ArrayList<>();
                map.put(umpMenuModel.getParentId(), children);
            }
            map.get(umpMenuModel.getParentId()).add(convertTreeModel(umpMenuModel));
        }
        List<UmpTreeModel> result = new ArrayList<>();
        for (UmpMenuModel umpMenuModel : umpMenuModelList) {
            boolean flag = umpMenuModel != null && umpMenuModel.getParentId() == null || umpMenuModel.getParentId() == 0;
            if (flag) {
                UmpTreeModel umpTreeModel = convertTreeModel(umpMenuModel);
                umpTreeModel.setChildren(getChild(map, umpMenuModel.getId()));
                result.add(umpTreeModel);
            }
        }
        return result;
    }

    /**
     * 递归获取子树模型结构数据
     *
     * @param map
     * @param id
     * @return List<UmpTreeModel>
     * @author wanyong
     * @date 2017-12-19 10:56
     */
    private List<UmpTreeModel> getChild(Map<Long, List<UmpTreeModel>> map, Long id) {
        List<UmpTreeModel> treeModelList = map.get(id);
        if (treeModelList != null) {
            for (UmpTreeModel treeModel : treeModelList) {
                if (treeModel != null) {
                    treeModel.setChildren(getChild(map, treeModel.getId()));
                }
            }
        }
        return treeModelList;
    }

    /**
     * 把菜单模型对象转换成树模型对象
     *
     * @param umpMenuModel
     * @return UmpTreeModel
     * @author wanyong
     * @date 2017-12-19 14:22
     */
    private UmpTreeModel convertTreeModel(UmpMenuModel umpMenuModel) {
        UmpTreeModel umpTreeModel = new UmpTreeModel();
        umpTreeModel.setId(umpMenuModel.getId());
        umpTreeModel.setName(umpMenuModel.getMenuName());
        umpTreeModel.setIcon(umpMenuModel.getIconcls());
        umpTreeModel.setSpread(umpMenuModel.getExpand() == 1);
        umpTreeModel.setHref(umpMenuModel.getRequest());
        umpTreeModel.setChecked(false);
        umpTreeModel.setDisabled(false);
        return umpTreeModel;
    }
}
