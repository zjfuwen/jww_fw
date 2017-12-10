package com.jww.ump.rpc.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.jww.common.core.base.BaseServiceImpl;
import com.jww.ump.dao.mapper.UmpMenuMapper;
import com.jww.ump.model.UmpMenuModel;
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
        umpMenuModel.setEnable(1);
        EntityWrapper<UmpMenuModel> entityWrapper = new EntityWrapper<>(umpMenuModel);
        return super.selectList(entityWrapper);
    }

    @Override
    public List<UmpMenuModel> queryMenuTreeByUserId(Long userId) {
        List<UmpMenuModel> umpMenuModelList = umpMenuMapper.selectMenuTreeByUserId(userId);
        Map<Long, List<UmpMenuModel>> map = new HashMap<>(3);
        for (UmpMenuModel umpMenuModel : umpMenuModelList) {
            if (umpMenuModel != null && map.get(umpMenuModel.getParentId()) == null) {
                List<UmpMenuModel> menuBeans = new ArrayList<>();
                map.put(umpMenuModel.getParentId(), menuBeans);
            }
            map.get(umpMenuModel.getParentId()).add(umpMenuModel);
        }
        List<UmpMenuModel> result = new ArrayList<>();
        for (UmpMenuModel umpMenuModel : umpMenuModelList) {
            boolean flag = umpMenuModel != null && umpMenuModel.getParentId() == null || umpMenuModel.getParentId() == 0;
            if (flag) {
                umpMenuModel.setLeaf(0);
                umpMenuModel.setMenuBeans(getChildMenu(map, umpMenuModel.getId()));
                result.add(umpMenuModel);
            }
        }
        return result;
    }

    // 递归获取子菜单
    private List<UmpMenuModel> getChildMenu(Map<Long, List<UmpMenuModel>> map, Long id) {
        List<UmpMenuModel> menus = map.get(id);
        if (menus != null) {
            for (UmpMenuModel umpMenuModel : menus) {
                if (umpMenuModel != null) {
                    umpMenuModel.setMenuBeans(getChildMenu(map, umpMenuModel.getId()));
                }
            }
        }
        return menus;
    }
}
