package com.jww.ump.rpc.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.jww.common.core.base.BaseServiceImpl;
import com.jww.common.core.exception.BusinessException;
import com.jww.common.core.model.PageModel;
import com.jww.ump.dao.mapper.UmpDeptMapper;
import com.jww.ump.dao.mapper.UmpTreeMapper;
import com.jww.ump.model.UmpDeptModel;
import com.jww.ump.model.UmpMenuModel;
import com.jww.ump.model.UmpTreeModel;
import com.jww.ump.rpc.api.UmpDeptService;
import com.xiaoleilu.hutool.lang.Assert;
import com.xiaoleilu.hutool.util.ObjectUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
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
    @Cacheable(value = "data")
    public UmpDeptModel queryOne(Long id){
        log.info("UmpDeptServiceImpl->queryOne->id:" + id);
        UmpDeptModel umpDeptModel = umpDeptMapper.selectOne(id);
        return umpDeptModel;
    }

    @Override
    public boolean delBatchByIds(Long[] ids) {
        int subDeptCount = querySubDeptCount(ids);
        if(subDeptCount > 0 ){
            throw new BusinessException("必须先删除子部门");
        }
        List<UmpDeptModel> list = new ArrayList<>();
        for(Long id : ids){
            UmpDeptModel umpdeptModel = new UmpDeptModel();
            umpdeptModel.setId(id);
            umpdeptModel.setIsDel(1);
            list.add(umpdeptModel);
        }
        return super.updateBatchById(list);
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

    @Override
    public boolean delDept(Long id){
        int subDeptCount = querySubDeptCount(id);
        if(subDeptCount > 0){
            throw new BusinessException("必须先删除子部门");
        }
        boolean result = false;
        UmpDeptModel umpDeptModel = new UmpDeptModel();
        umpDeptModel.setId(id);
        umpDeptModel.setIsDel(1);
        umpDeptModel = super.modifyById(umpDeptModel);
        if(umpDeptModel!=null){
            result = true;
        }
        return result;
    }

    @Override
    public List<UmpDeptModel> querySubDept(Long id){
        Assert.notNull(id);
        EntityWrapper<UmpDeptModel> wrapper = new EntityWrapper<>();
        wrapper.eq("parent_id",id);
        wrapper.eq("is_del",0);
        List<UmpDeptModel> list = umpDeptMapper.selectList(wrapper);
        return list;
    }

    @Override
    public int querySubDeptCount(Long id){
        Assert.notNull(id);
        EntityWrapper<UmpDeptModel> wrapper = new EntityWrapper<>();
        wrapper.eq("parent_id",id);
        wrapper.eq("is_del",0);
        return umpDeptMapper.selectCount(wrapper);
    }

    @Override
    public int querySubDeptCount(Long[] ids){
        Assert.notNull(ids);
        EntityWrapper<UmpDeptModel> wrapper = new EntityWrapper<>();
        wrapper.in("parent_id",ids);
        wrapper.eq("is_del",0);
        return umpDeptMapper.selectCount(wrapper);
    }
}
