package com.jww.ump.rpc.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.jww.common.core.annotation.DistributedLock;
import com.jww.common.core.base.BaseServiceImpl;
import com.jww.common.core.exception.BusinessException;
import com.jww.ump.dao.mapper.SysDeptMapper;
import com.jww.ump.dao.mapper.SysTreeMapper;
import com.jww.ump.model.SysDeptModel;
import com.jww.ump.model.SysTreeModel;
import com.jww.ump.rpc.api.SysDeptService;
import com.xiaoleilu.hutool.lang.Assert;
import com.xiaoleilu.hutool.util.ObjectUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Author: Ricky Wang
 * @Date: 17/12/1 14:49:30
 */
@Service("sysDeptService")
@Slf4j
public class SysDeptServiceImpl extends BaseServiceImpl<SysDeptMapper, SysDeptModel> implements SysDeptService {

    @Autowired
    private SysDeptMapper sysDeptMapper;
    @Autowired
    private SysTreeMapper sysTreeMapper;

    @Override
    @CachePut(value = "data")
    @DistributedLock(value = "#sysDeptModel.getParentId()")
    public SysDeptModel addDept(SysDeptModel sysDeptModel) {
        EntityWrapper<SysDeptModel> wrapper = new EntityWrapper<>();

        wrapper.eq("parent_id", sysDeptModel.getParentId());
        wrapper.eq("dept_name", sysDeptModel.getDeptName());
        wrapper.eq("is_del", 0);
        List<SysDeptModel> deptModelList = sysDeptMapper.selectList(wrapper);
        if (ObjectUtil.isNotNull(super.selectOne(wrapper))) {
            throw new BusinessException("同级部门名称不能重复");
        }
        sysDeptModel.setUnitId(Long.valueOf(1));
        Date now = new Date();
        sysDeptModel.setCreateTime(now);
        sysDeptModel.setUpdateTime(now);
        if (sysDeptModel.getParentId() == null) {
            sysDeptModel.setParentId(0L);
        }
        return super.add(sysDeptModel);
    }

    @Override
    public boolean modifyDept(SysDeptModel sysDeptModel) {
        return super.updateById(sysDeptModel);
    }

    @Override
    public Page<SysDeptModel> queryListPage(Page<SysDeptModel> page) {
        log.info("SysDeptServiceImpl->queryListPage->page:" + page.toString());
        log.info("SysDeptServiceImpl->queryListPage->page->condition:" + JSON.toJSONString(page.getCondition()));
        String deptName = page.getCondition() == null ? null : page.getCondition().get("dept_name").toString();
        List<SysDeptModel> list = sysDeptMapper.selectPage(page, deptName);
        page.setRecords(list);
        return page;
    }

    @Override
    @Cacheable(value = "data")
    public SysDeptModel queryOne(Long id) {
        log.info("SysDeptServiceImpl->queryOne->id:" + id);
        SysDeptModel sysDeptModel = sysDeptMapper.selectOne(id);
        return sysDeptModel;
    }

    @Override
    @CacheEvict(value = "data", allEntries = true)
    public Integer deleteBatch(Long[] ids) {
        int succ = 0;
        for (Long id : ids) {
            Boolean res = false;
            try {
                res = this.delDept(id);
            } catch (Exception e) {
                log.error("删除部门失败，id:{}", id, e);
            }
            if (res) {
                succ++;
            }
            log.debug("删除部门完成，id:{}，执行结果：{}", id, res);
        }
        return succ;
    }

    @Override
    public List<SysTreeModel> queryTree() {
        return this.queryTree(null);
    }

    ;

    @Override
    public List<SysTreeModel> queryTree(Long id) {
        List<SysTreeModel> sysTreeModelList = sysTreeMapper.selectDeptTree(id);
        List<SysTreeModel> list = SysTreeModel.getTree(sysTreeModelList);
        return list;
    }

    @Override
    @DistributedLock
    public boolean delDept(Long id) {
        int subDeptCount = querySubDeptCount(id);
        if (subDeptCount > 0) {
            throw new BusinessException("必须先删除子部门");
        }
        boolean result = false;
        SysDeptModel sysDeptModel = new SysDeptModel();
        sysDeptModel.setId(id);
        sysDeptModel.setIsDel(1);
        sysDeptModel = super.modifyById(sysDeptModel);
        if (sysDeptModel != null) {
            result = true;
        }
        return result;
    }

    @Override
    public List<SysDeptModel> querySubDept(Long id) {
        Assert.notNull(id);
        EntityWrapper<SysDeptModel> wrapper = new EntityWrapper<>();
        wrapper.eq("parent_id", id);
        wrapper.eq("is_del", 0);
        List<SysDeptModel> list = sysDeptMapper.selectList(wrapper);
        return list;
    }

    @Override
    public int querySubDeptCount(Long id) {
        Assert.notNull(id);
        EntityWrapper<SysDeptModel> wrapper = new EntityWrapper<>();
        wrapper.eq("parent_id", id);
        wrapper.eq("is_del", 0);
        return sysDeptMapper.selectCount(wrapper);
    }

    @Override
    public int querySubDeptCount(Long[] ids) {
        Assert.notNull(ids);
        EntityWrapper<SysDeptModel> wrapper = new EntityWrapper<>();
        wrapper.in("parent_id", ids);
        wrapper.eq("is_del", 0);
        return sysDeptMapper.selectCount(wrapper);
    }
}
