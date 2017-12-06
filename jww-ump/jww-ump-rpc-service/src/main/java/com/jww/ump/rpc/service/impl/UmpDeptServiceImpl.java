package com.jww.ump.rpc.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.jww.common.core.base.BaseServiceImpl;
import com.jww.ump.dao.mapper.UmpDeptMapper;
import com.jww.ump.model.UmpDept;
import com.jww.ump.model.UmpUserModel;
import com.jww.ump.rpc.api.UmpDeptService;
import com.xiaoleilu.hutool.util.ObjectUtil;
import com.xiaoleilu.hutool.util.StrUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @Title:
 * @Description:
 * @Author: Ricky Wang
 * @Date: 17/12/1 14:49:30
 */
@Service("umpDeptService")
@Slf4j
public class UmpDeptServiceImpl extends BaseServiceImpl<UmpDeptMapper, UmpDept> implements UmpDeptService{

    @Override
    public Page<UmpDept> queryListPage(Page<UmpDept> page) {
        log.info("UmpDeptServiceImpl->queryListPage->page:" + page.toString());
        log.info("UmpDeptServiceImpl->queryListPage->page->condition:" + JSON.toJSONString(page.getCondition()));
        UmpDept umpDept = new UmpDept();
//        umpDept.setEnable(1);
        EntityWrapper<UmpDept> entityWrapper = new EntityWrapper<UmpDept>(umpDept);
        if (ObjectUtil.isNotNull(page.getCondition())) {
            entityWrapper.like("dept_name",page.getCondition().get("dept_name").toString());
        }
        page.setCondition(null);

        return super.selectPage(page, entityWrapper);
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
}
