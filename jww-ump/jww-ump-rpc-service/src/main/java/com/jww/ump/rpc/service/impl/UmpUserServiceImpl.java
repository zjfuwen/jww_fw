package com.jww.ump.rpc.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.jww.common.core.base.BaseServiceImpl;
import com.jww.ump.dao.mapper.UmpUserMapper;
import com.jww.ump.model.UmpUserModel;
import com.jww.ump.rpc.api.UmpUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * 用户管理服务实现
 *
 * @author wanyong
 * @date 2017/11/17 16:43
 */
@Slf4j
@Service("umpUserService")
public class UmpUserServiceImpl extends BaseServiceImpl<UmpUserMapper, UmpUserModel> implements UmpUserService {

    @Override
    public UmpUserModel findByAccount(String account) {
        log.info("UmpUserServiceImpl->findByAccount->account:" + account);
        UmpUserModel umpUserModel = new UmpUserModel();
        umpUserModel.setAccount(account);
        umpUserModel.setEnable(1);
        EntityWrapper<UmpUserModel> entityWrapper = new EntityWrapper<>(umpUserModel);
        return super.selectOne(entityWrapper);
    }

    @Override
    public Page<UmpUserModel> findListPage(Page<UmpUserModel> page) {
        log.info("UmpUserServiceImpl->findListPage->page:" + page.toString());
        UmpUserModel umpUserModel = new UmpUserModel();
        umpUserModel.setEnable(1);
        EntityWrapper<UmpUserModel> entityWrapper = new EntityWrapper<>(umpUserModel);
        return super.selectPage(page, entityWrapper);
    }
}
