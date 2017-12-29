package com.jww.ump.rpc.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.jww.common.core.annotation.DistributedLock;
import com.jww.common.core.base.BaseServiceImpl;
import com.jww.ump.dao.mapper.SysRoleMapper;
import com.jww.ump.dao.mapper.SysUserMapper;
import com.jww.ump.dao.mapper.SysUserRoleMapper;
import com.jww.ump.model.SysRoleModel;
import com.jww.ump.model.SysUserModel;
import com.jww.ump.model.SysUserRoleModel;
import com.jww.ump.rpc.api.SysUserService;
import com.xiaoleilu.hutool.lang.Assert;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 用户管理服务实现
 *
 * @author wanyong
 * @date 2017/11/17 16:43
 */
@Slf4j
@Service("sysUserService")
public class SysUserServiceImpl extends BaseServiceImpl<SysUserMapper, SysUserModel> implements SysUserService {

    @Autowired
    private SysUserMapper sysUserMapper;
    @Autowired
    private SysRoleMapper sysRoleMapper;
    @Autowired
    private SysUserRoleMapper sysUserRoleMapper;

    @Override
    public SysUserModel queryByAccount(String account) {
        log.info("SysUserServiceImpl->findByAccount->account:" + account);
        SysUserModel sysUserModel = new SysUserModel();
        sysUserModel.setAccount(account);
        sysUserModel.setEnable(1);
        EntityWrapper<SysUserModel> entityWrapper = new EntityWrapper<>(sysUserModel);
        return super.selectOne(entityWrapper);
    }

    @Override
    public Page<SysUserModel> queryListPage(Page<SysUserModel> page) {
        log.info("SysUserServiceImpl->findListPage->page:" + page.toString());
        log.info("SysUserServiceImpl->findListPage->page->condition:" + JSON.toJSONString(page.getCondition()));
        String searchKey = page.getCondition()==null? null :page.getCondition().get("searchKey").toString();
        List<SysUserModel> list =  sysUserMapper.selectPage(page,searchKey);
        page.setRecords(list);
        return page;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    @CacheEvict(value = "data", allEntries = true)
    public boolean delBatchByIds(List<Long> ids) {
        List<SysUserModel> sysUserModelList = new ArrayList<>(5);
        for (Long id : ids) {
            SysUserModel sysUserModel = new SysUserModel();
            sysUserModel.setId(id);
            sysUserModel.setIsDel(1);

            sysUserModelList.add(sysUserModel);

            SysUserRoleModel sysUserRoleModel = new SysUserRoleModel();
            sysUserRoleModel.setIsDel(1);
            sysUserRoleModel.setUpdateTime(new Date());
            sysUserRoleModel.setUpdateBy(sysUserModel.getCreateBy());
            EntityWrapper<SysUserRoleModel> wrapper = new EntityWrapper<>();
            wrapper.eq("user_id",sysUserModel.getId());
            sysUserRoleMapper.update(sysUserRoleModel,wrapper);
        }
        return super.updateBatchById(sysUserModelList);
    }

    @Override
    public List<SysRoleModel> queryRoles(Long deptId){
        Assert.notNull(deptId);
        EntityWrapper<SysRoleModel> entityWrapper = new EntityWrapper<>();
        entityWrapper.eq("dept_id",deptId);
        return sysRoleMapper.selectList(entityWrapper);
    }

    @Override
    @CachePut(value = "data")
    @Transactional(rollbackFor = Exception.class)
    @DistributedLock
    public SysUserModel add(SysUserModel sysUserModel) {
        sysUserModel.setCreateTime(new Date());
        sysUserModel.setUpdateTime(new Date());
        if (super.insert(sysUserModel)) {
            insertUserRole(sysUserModel);
            return sysUserModel;
        }
        return null;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean modifyUser(SysUserModel sysUserModel){
        boolean result = false;
        EntityWrapper<SysUserRoleModel> wrapper = new EntityWrapper<>();
        wrapper.eq("user_id",sysUserModel.getId());
        sysUserRoleMapper.delete(wrapper);
        insertUserRole(sysUserModel);
        result = super.updateById(sysUserModel);

        return result;
    }

    @Override
    public List<SysUserRoleModel> queryUserRoles(Long userId){
        Assert.notNull(userId);
        log.info("SysUserServiceImpl->queryUserRoles->userId:" + userId);
        EntityWrapper<SysUserRoleModel> wrapper = new EntityWrapper<>();
        wrapper.eq("user_id",userId);
        return sysUserRoleMapper.selectList(wrapper);
    }

    @Override
    public SysUserModel queryOne(Long id){
        log.info("SysUserServiceImpl->queryOne->id:" + id);
        SysUserModel sysUserModel = sysUserMapper.selectOne(id);
        return sysUserModel;
    }

    private void insertUserRole(SysUserModel sysUserModel){
        if(sysUserModel.getRole()!=null && sysUserModel.getRole().length!=0){
            for(Long roleId : sysUserModel.getRole()){
                SysUserRoleModel sysUserRoleModel = new SysUserRoleModel();
                sysUserRoleModel.setUserId(sysUserModel.getId());
                sysUserRoleModel.setCreateTime(new Date());
                sysUserRoleModel.setUpdateTime(new Date());
                sysUserRoleModel.setCreateBy(sysUserModel.getCreateBy());
                sysUserRoleModel.setUpdateBy(sysUserModel.getCreateBy());
                sysUserRoleModel.setRoleId(roleId);
                sysUserRoleMapper.insert(sysUserRoleModel);
            }
        }
    }

}
