package com.jww.ump.rpc.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.jww.ump.dao.mapper.UmpAuthorizeMapper;
import com.jww.ump.dao.mapper.UmpMenuMapper;
import com.jww.ump.model.UmpMenuModel;
import com.jww.ump.rpc.api.UmpAuthorizeService;
import com.xiaoleilu.hutool.collection.CollUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 权限业务处理实现类
 *
 * @author wanyong
 * @create 2017-11-29
 **/
@Service("umpAuthorizeService")
public class UmpAuthorizeServiceImpl implements UmpAuthorizeService {

    @Autowired
    private UmpAuthorizeMapper umpAuthorizeMapper;
    @Autowired
    private UmpMenuMapper umpMenuMapper;

    @Override
    public List<String> queryPermissionsByUserId(Long userId) {
        //如果是超级管理员，则查询所有权限code
        if (userId == 1) {
            List<String> permissions = new ArrayList<String>();
            List<UmpMenuModel> list = umpMenuMapper.selectList(new EntityWrapper<UmpMenuModel>());
            if (CollUtil.isNotEmpty(list)) {
                for (UmpMenuModel umpMenuModel : list) {
                    permissions.add(umpMenuModel.getPermission());
                }
            }
            return permissions;
        }
        return umpAuthorizeMapper.selectPermissionsByUserId(userId);
    }
}
