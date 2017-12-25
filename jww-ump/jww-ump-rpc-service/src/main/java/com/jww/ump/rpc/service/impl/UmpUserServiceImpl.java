package com.jww.ump.rpc.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.jww.common.core.base.BaseServiceImpl;
import com.jww.ump.dao.mapper.UmpRoleMapper;
import com.jww.ump.dao.mapper.UmpUserMapper;
import com.jww.ump.dao.mapper.UmpUserRoleMapper;
import com.jww.ump.model.UmpRoleModel;
import com.jww.ump.model.UmpUserModel;
import com.jww.ump.model.UmpUserRoleModel;
import com.jww.ump.rpc.api.UmpRoleService;
import com.jww.ump.rpc.api.UmpUserService;
import com.xiaoleilu.hutool.lang.Assert;
import com.xiaoleilu.hutool.util.ObjectUtil;
import com.xiaoleilu.hutool.util.StrUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
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
    @Autowired
    private UmpRoleMapper umpRoleMapper;
    @Autowired
    private UmpUserRoleMapper umpUserRoleMapper;

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
    @Transactional(rollbackFor = Exception.class)
    public boolean delBatchByIds(List<Long> ids) {
        List<UmpUserModel> umpUserModelList = new ArrayList<>(5);
        for (Long id : ids) {
            UmpUserModel umpUserModel = new UmpUserModel();
            umpUserModel.setId(id);
            umpUserModel.setIsDel(1);

            umpUserModelList.add(umpUserModel);

            UmpUserRoleModel umpUserRoleModel = new UmpUserRoleModel();
            umpUserRoleModel.setIsDel(1);
            umpUserRoleModel.setUpdateTime(new Date());
            umpUserRoleModel.setUpdateBy(umpUserModel.getCreateBy());
            EntityWrapper<UmpUserRoleModel> wrapper = new EntityWrapper<>();
            wrapper.eq("user_id",umpUserModel.getId());
            umpUserRoleMapper.update(umpUserRoleModel,wrapper);
        }
        return super.updateBatchById(umpUserModelList);
    }

    @Override
    public List<UmpRoleModel> queryRoles(Long deptId){
        Assert.notNull(deptId);
        EntityWrapper<UmpRoleModel> entityWrapper = new EntityWrapper<>();
        entityWrapper.eq("dept_id",deptId);
        return umpRoleMapper.selectList(entityWrapper);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public UmpUserModel add(UmpUserModel umpUserModel) {
        umpUserModel.setCreateTime(new Date());
        umpUserModel.setUpdateTime(new Date());
        if (super.insert(umpUserModel)) {
            insertUserRole(umpUserModel);
            return umpUserModel;
        }
        return null;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean modifyUser(UmpUserModel umpUserModel){
        boolean result = false;
        EntityWrapper<UmpUserRoleModel> wrapper = new EntityWrapper<>();
        wrapper.eq("user_id",umpUserModel.getId());
        umpUserRoleMapper.delete(wrapper);
        insertUserRole(umpUserModel);
        result = super.updateById(umpUserModel);

        return result;
    }

    @Override
    public List<UmpUserRoleModel> queryUserRoles(Long userId){
        Assert.notNull(userId);
        log.info("UmpUserServiceImpl->queryUserRoles->userId:" + userId);
        EntityWrapper<UmpUserRoleModel> wrapper = new EntityWrapper<>();
        wrapper.eq("user_id",userId);
        return umpUserRoleMapper.selectList(wrapper);
    }

    @Override
    public UmpUserModel queryOne(Long id){
        log.info("UmpUserServiceImpl->queryOne->id:" + id);
        UmpUserModel umpUserModel = umpUserMapper.selectOne(id);
        return umpUserModel;
    }

    private void insertUserRole(UmpUserModel umpUserModel){
        if(umpUserModel.getRole()!=null && umpUserModel.getRole().length!=0){
            for(Long roleId : umpUserModel.getRole()){
                UmpUserRoleModel umpUserRoleModel = new UmpUserRoleModel();
                umpUserRoleModel.setUserId(umpUserModel.getId());
                umpUserRoleModel.setCreateTime(new Date());
                umpUserRoleModel.setUpdateTime(new Date());
                umpUserRoleModel.setCreateBy(umpUserModel.getCreateBy());
                umpUserRoleModel.setUpdateBy(umpUserModel.getCreateBy());
                umpUserRoleModel.setRoleId(roleId);
                umpUserRoleMapper.insert(umpUserRoleModel);
            }
        }
    }

}
