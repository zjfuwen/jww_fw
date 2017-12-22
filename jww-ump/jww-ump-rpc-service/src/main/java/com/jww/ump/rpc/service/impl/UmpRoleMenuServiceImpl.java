package com.jww.ump.rpc.service.impl;

import com.jww.common.core.base.BaseServiceImpl;
import com.jww.ump.dao.mapper.UmpRoleMenuMapper;
import com.jww.ump.model.UmpRoleMenuModel;
import com.jww.ump.rpc.api.UmpRoleMenuService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 角色授权表 服务实现类
 * </p>
 *
 * @author wanyong
 * @since 2017-12-17
 */
@Service("UmpRoleMenuService")
public class UmpRoleMenuServiceImpl extends BaseServiceImpl<UmpRoleMenuMapper, UmpRoleMenuModel> implements UmpRoleMenuService {
}
