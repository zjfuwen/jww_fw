package com.jww.ump.rpc.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.jww.common.core.base.BaseServiceImpl;
import com.jww.ump.dao.mapper.UmpMenuMapper;
import com.jww.ump.model.UmpMenuModel;
import com.jww.ump.rpc.api.UmpMenuService;
import org.springframework.stereotype.Service;

import java.util.List;

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

    @Override
    public List<UmpMenuModel> findList() {
        UmpMenuModel umpMenuModel = new UmpMenuModel();
        umpMenuModel.setEnable(1);
        EntityWrapper<UmpMenuModel> entityWrapper = new EntityWrapper<>(umpMenuModel);
        return super.selectList(entityWrapper);
    }
}
