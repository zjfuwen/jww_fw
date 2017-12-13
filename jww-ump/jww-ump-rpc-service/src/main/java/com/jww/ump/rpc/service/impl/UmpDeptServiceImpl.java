package com.jww.ump.rpc.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.jww.common.core.base.BaseServiceImpl;
import com.jww.common.core.model.PageModel;
import com.jww.ump.dao.mapper.UmpDeptMapper;
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

//    @Override
//    public Page<UmpDeptModel> queryListPage(Page<UmpDeptModel> page) {
//        log.info("UmpDeptServiceImpl->queryListPage->page:" + page.toString());
//        log.info("UmpDeptServiceImpl->queryListPage->page->condition:" + JSON.toJSONString(page.getCondition()));
//        UmpDeptModel umpDept = new UmpDeptModel();
////        umpDept.setEnable(1);
//        EntityWrapper<UmpDeptModel> entityWrapper = new EntityWrapper<UmpDeptModel>(umpDept);
//        if (ObjectUtil.isNotNull(page.getCondition())) {
//            entityWrapper.like("dept_name", page.getCondition().get("dept_name").toString());
//        }
//        page.setCondition(null);
//
//        return super.selectPage(page, entityWrapper);
//    }

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
    public boolean delBatchByIds(List<Long> ids) {
//        List<UmpDept> umpDeptList = new ArrayList<>(5);
//        for (Long id : ids) {
//            UmpDept umpDept = new UmpDept();
//            umpDept.setId(id);
//            umpDept.setEnable(0);
//            umpDeptList.add(umpDept);
//        }
//        return super.updateBatchById(umpDeptList);
        return super.deleteBatchIds(ids);
    }

    @Override
    public UmpTreeModel queryTree(){
        List<UmpDeptModel> deptModelList = super.selectList(null);
        Map<Long,List<UmpTreeModel>> map = new TreeMap<>();
        List<UmpTreeModel> umpTreeModelList = new ArrayList<>();
        UmpTreeModel rootNode = new UmpTreeModel();
        for(UmpDeptModel umpDeptModel : deptModelList){
            UmpTreeModel umpTreeModel = new UmpTreeModel();
            umpTreeModel.setId(umpDeptModel.getId());
            umpTreeModel.setParentId(umpDeptModel.getParentId());
            umpTreeModel.setName(umpDeptModel.getDeptName());
            umpTreeModelList.add(umpTreeModel);
            if(umpTreeModel.getParentId().equals(0L)){
                rootNode.setId(umpTreeModel.getId());
                rootNode.setName(umpTreeModel.getName());
            }
        }
        rootNode = UmpTreeModel.constructTree(rootNode, umpTreeModelList, 0);
        return rootNode;
    }
}
