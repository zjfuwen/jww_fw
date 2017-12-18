package com.jww.ump.rpc.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.jww.common.core.base.BaseServiceImpl;
import com.jww.common.core.model.PageModel;
import com.jww.ump.dao.mapper.UmpDeptMapper;
import com.jww.ump.dao.mapper.UmpTreeMapper;
import com.jww.ump.model.UmpDeptModel;
import com.jww.ump.model.UmpMenuModel;
import com.jww.ump.model.UmpTreeModel;
import com.jww.ump.rpc.api.UmpDeptService;
import com.xiaoleilu.hutool.util.ObjectUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @Author: Ricky Wang
 * @Date: 17/12/1 14:49:30
 */
@Service("umpDeptService")
@Slf4j
public class UmpDeptServiceImpl extends BaseServiceImpl<UmpDeptMapper, UmpDeptModel> implements UmpDeptService {

    @Autowired
    private UmpDeptMapper umpDeptMapper;
    @Autowired
    private UmpTreeMapper umpTreeMapper;

    @Override
    public boolean addDept(UmpDeptModel umpDeptModel){
        return super.insert(umpDeptModel);
    }

    @Override
    public boolean modifyDept(UmpDeptModel umpDeptModel){
        return super.updateById(umpDeptModel);
    }

    @Override
    public Page<UmpDeptModel> queryListPage(Page<UmpDeptModel> page) {
        log.info("UmpDeptServiceImpl->queryListPage->page:" + page.toString());
        log.info("UmpDeptServiceImpl->queryListPage->page->condition:" + JSON.toJSONString(page.getCondition()));
        String dept_name = page.getCondition()==null? null :page.getCondition().get("dept_name").toString();
        List<UmpDeptModel> list =  umpDeptMapper.selectPage(page,dept_name);
        page.setRecords(list);
        return page;
    }

    @Override
    public UmpDeptModel queryOne(Long id){
        log.info("UmpDeptServiceImpl->queryOne->id:" + id);
        UmpDeptModel umpDeptModel = umpDeptMapper.selectOne(id);
        return umpDeptModel;
    }

    @Override
    public boolean delBatchByIds(List<Long> ids) {
        return super.deleteBatchIds(ids);
    }

    @Override
    public List<UmpTreeModel> queryTree(){
        return this.queryTree(null);
    };

    @Override
    public List<UmpTreeModel> queryTree(Long id){
        List<UmpTreeModel> umpTreeModelList = umpTreeMapper.selectDeptTree(id);
        List<UmpTreeModel> list  = UmpTreeModel.getTree(umpTreeModelList);
        return list;
    }
}
