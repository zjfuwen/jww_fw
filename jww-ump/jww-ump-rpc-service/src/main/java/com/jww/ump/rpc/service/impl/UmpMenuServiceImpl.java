package com.jww.ump.rpc.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.jww.common.core.annotation.DistributedLock;
import com.jww.common.core.base.BaseServiceImpl;
import com.jww.common.core.exception.BusinessException;
import com.jww.ump.dao.mapper.UmpMenuMapper;
import com.jww.ump.dao.mapper.UmpRoleMenuMapper;
import com.jww.ump.dao.mapper.UmpTreeMapper;
import com.jww.ump.model.UmpMenuModel;
import com.jww.ump.model.UmpTreeModel;
import com.jww.ump.rpc.api.UmpMenuService;
import com.xiaoleilu.hutool.util.ArrayUtil;
import com.xiaoleilu.hutool.util.CollectionUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.*;

/**
 * <p>
 * 菜单 服务实现类
 * </p>
 *
 * @author wanyong
 * @since 2017-11-29
 */
@Slf4j
@Service("umpMenuService")
public class UmpMenuServiceImpl extends BaseServiceImpl<UmpMenuMapper, UmpMenuModel> implements UmpMenuService {

    @Autowired
    private UmpMenuMapper umpMenuMapper;

    @Autowired
    private UmpTreeMapper umpTreeMapper;

    @Autowired
    private UmpRoleMenuMapper umpRoleMenuMapper;

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
    public Page<UmpMenuModel> queryListPage(Page<UmpMenuModel> page) {
        UmpMenuModel menu = new UmpMenuModel();
        menu.setEnable(1);
        EntityWrapper<UmpMenuModel> wrapper = new EntityWrapper<>(menu);
        page.setCondition(null);
        return super.selectPage(page, wrapper);
    }

    @Override
    public List<UmpTreeModel> queryMenuTreeByUserId(Long userId) {
        List<UmpMenuModel> umpMenuModelList = umpMenuMapper.selectMenuTreeByUserId(userId);
        return convertTreeData(umpMenuModelList, null);
    }

    @Override
    public List<UmpTreeModel> queryFuncMenuTree() {
        List<UmpMenuModel> umpMenuModelList = queryList();
        return convertTreeData(umpMenuModelList, null);
    }

    @Override
    public List<UmpTreeModel> queryFuncMenuTree(Long roleId) {
        List<UmpMenuModel> umpMenuModelList = queryList();
        List<Long> menuIdList = umpRoleMenuMapper.selectMenuIdListByRoleId(roleId);
        System.out.println("menuIdList:" + JSON.toJSONString(menuIdList));
        return convertTreeData(umpMenuModelList, menuIdList.toArray());
    }

    @Override
    public List<UmpTreeModel> queryTree(Long id) {
        List<UmpTreeModel> umpTreeModelList = umpTreeMapper.selectMenuTree(id);
        List<UmpTreeModel> list = UmpTreeModel.getTree(umpTreeModelList);
        return list;
    }

    @Override
    @DistributedLock
    public Boolean delete(Long id) {
        //查询是否有子菜单，如果有则返回false，否则允许删除
        EntityWrapper<UmpMenuModel> entityWrapper = new EntityWrapper<UmpMenuModel>();
        UmpMenuModel umpMenuModel = new UmpMenuModel();
        umpMenuModel.setParentId((Long) id);
        umpMenuModel.setIsDel(0);
        entityWrapper.setEntity(umpMenuModel);
        List<UmpMenuModel> childs = super.selectList(entityWrapper);
        if (CollectionUtil.isNotEmpty(childs)) {
            log.error("删除菜单[id:{}]失败，请先删除子菜单", id);
            throw new BusinessException("删除菜单失败，请先删除子菜单");
        }
        umpMenuModel = new UmpMenuModel();
        umpMenuModel.setId(id);
        umpMenuModel.setIsDel(1);
        umpMenuModel = super.modifyById(umpMenuModel);
        if (umpMenuModel != null) {
            return true;
        }
        return false;
    }

    @Override
    @CacheEvict(value = "data",allEntries = true)
    public Integer deleteBatch(Long[] ids) {
        int succ = 0;
        for (Long id : ids) {
            Boolean res = false;
            try {
                res = this.delete(id);
            } catch (Exception e) {
                log.error("删除菜单失败，id:{}", id, e);
            }
            if (res) {
                succ++;
            }
            log.debug("删除菜单完成，id:{}，执行结果：{}", id, res);
        }
        return succ;
    }

    @Override
    @CachePut(value = "data")
    @DistributedLock(value = "#umpMenuModel.getParentId()")
    public UmpMenuModel add(UmpMenuModel umpMenuModel) {
        return super.add(umpMenuModel);
    }

    /**
     * 获取树模型结构数据
     *
     * @param umpMenuModelList
     * @param checkedMenuIds
     * @return List<UmpTreeModel>
     * @author wanyong
     * @date 2017-12-19 10:55
     */
    private List<UmpTreeModel> convertTreeData(List<UmpMenuModel> umpMenuModelList, Object[] checkedMenuIds) {
        Map<Long, List<UmpTreeModel>> map = new HashMap<>(3);
        for (UmpMenuModel umpMenuModel : umpMenuModelList) {
            if (umpMenuModel != null && map.get(umpMenuModel.getParentId()) == null) {
                List<UmpTreeModel> children = new ArrayList<>();
                map.put(umpMenuModel.getParentId(), children);
            }
            map.get(umpMenuModel.getParentId()).add(convertTreeModel(umpMenuModel, checkedMenuIds));
        }
        List<UmpTreeModel> result = new ArrayList<>();
        for (UmpMenuModel umpMenuModel : umpMenuModelList) {
            boolean flag = umpMenuModel != null && umpMenuModel.getParentId() == null || umpMenuModel.getParentId() == 0;
            if (flag) {
                UmpTreeModel umpTreeModel = convertTreeModel(umpMenuModel, checkedMenuIds);
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
     * @param checkedMenuIds
     * @return UmpTreeModel
     * @author wanyong
     * @date 2017-12-19 14:22
     */
    private UmpTreeModel convertTreeModel(UmpMenuModel umpMenuModel, Object[] checkedMenuIds) {
        UmpTreeModel umpTreeModel = new UmpTreeModel();
        umpTreeModel.setId(umpMenuModel.getId());
        umpTreeModel.setName(umpMenuModel.getMenuName());
        umpTreeModel.setIcon(umpMenuModel.getIconcls());
        umpTreeModel.setSpread(umpMenuModel.getExpand() == 1);
        umpTreeModel.setHref(umpMenuModel.getRequest());
        umpTreeModel.setChecked(checkedMenuIds != null && ArrayUtil.contains(checkedMenuIds, umpMenuModel.getId()));
        umpTreeModel.setDisabled(false);
        return umpTreeModel;
    }
}
