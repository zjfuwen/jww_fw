package com.jww.ump.rpc.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.jww.common.core.base.BaseServiceImpl;
import com.jww.ump.dao.mapper.UmpUserMapper;
import com.jww.ump.model.UmpUserModel;
import com.jww.ump.rpc.api.UmpUserService;
import com.xiaoleilu.hutool.util.ObjectUtil;
import com.xiaoleilu.hutool.util.StrUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 用户管理服务实现
 *
 * @author wanyong
 * @date 2017/11/17 16:43
 */
@Slf4j
@Service("umpUserService")
public class UmpUserServiceImpl extends BaseServiceImpl<UmpUserMapper, UmpUserModel> implements UmpUserService {

    @Autowired
    private UmpUserMapper umpUserMapper;

    @Override
    public UmpUserModel queryByAccount(String account) {
        log.info("UmpUserServiceImpl->findByAccount->account:" + account);
        UmpUserModel umpUserModel = new UmpUserModel();
        umpUserModel.setAccount(account);
        umpUserModel.setEnable(1);
        EntityWrapper<UmpUserModel> entityWrapper = new EntityWrapper<>(umpUserModel);
        return super.selectOne(entityWrapper);
    }

    @Override
    public Page<UmpUserModel> queryListPage(Page<UmpUserModel> page) {
        log.info("UmpUserServiceImpl->findListPage->page:" + page.toString());
        log.info("UmpUserServiceImpl->findListPage->page->condition:" + JSON.toJSONString(page.getCondition()));
        String searchKey = page.getCondition()==null? null :page.getCondition().get("searchKey").toString();
        List<UmpUserModel> list =  umpUserMapper.selectPage(page,searchKey);
        page.setRecords(list);
        return page;
    }

    @Override
    public boolean delBatchByIds(List<Long> ids) {
        List<UmpUserModel> umpUserModelList = new ArrayList<>(5);
        for (Long id : ids) {
            UmpUserModel umpUserModel = new UmpUserModel();
            umpUserModel.setId(id);
            umpUserModel.setIsDel(1);

            umpUserModelList.add(umpUserModel);
        }
        return super.updateBatchById(umpUserModelList);
    }

}
