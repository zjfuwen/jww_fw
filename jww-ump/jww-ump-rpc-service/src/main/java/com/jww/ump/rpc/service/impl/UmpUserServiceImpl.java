package com.jww.ump.rpc.service.impl;

import com.jww.common.base.BaseService;
import com.jww.common.base.BaseServiceImpl;
import com.jww.ump.dao.mapper.UmpUserMapper;
import com.jww.ump.model.UmpUserModel;
import com.jww.ump.rpc.api.UmpUserService;
import org.springframework.stereotype.Service;

/**
 * @author wanyong
 * @description: 用户管理服务实现
 * @date 2017/11/17 16:43
 */
@Service("umpUserService")
public class UmpUserServiceImpl extends BaseServiceImpl<UmpUserMapper, UmpUserModel> implements UmpUserService {

}
