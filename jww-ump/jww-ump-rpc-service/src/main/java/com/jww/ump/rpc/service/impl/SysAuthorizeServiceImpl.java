package com.jww.ump.rpc.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.jww.ump.dao.mapper.SysAuthorizeMapper;
import com.jww.ump.dao.mapper.SysMenuMapper;
import com.jww.ump.model.SysMenuModel;
import com.jww.ump.rpc.api.SysAuthorizeService;
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
@Service("sysAuthorizeService")
public class SysAuthorizeServiceImpl implements SysAuthorizeService {

    @Autowired
    private SysAuthorizeMapper umpAuthorizeMapper;
    @Autowired
    private SysMenuMapper sysMenuMapper;

    @Override
    public List<String> queryPermissionsByUserId(Long userId) {
        //如果是超级管理员，则查询所有权限code
        if (userId == 1) {
            List<String> permissions = new ArrayList<String>();
            List<SysMenuModel> list = sysMenuMapper.selectList(new EntityWrapper<SysMenuModel>());
            if (CollUtil.isNotEmpty(list)) {
                for (SysMenuModel umpMenuModel : list) {
                    permissions.add(umpMenuModel.getPermission());
                }
            }
            return permissions;
        }
        return umpAuthorizeMapper.selectPermissionsByUserId(userId);
    }
}
