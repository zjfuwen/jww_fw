package com.jww.ump.rpc.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.jww.common.core.base.BaseServiceImpl;
import com.jww.common.core.exception.BusinessException;
import com.jww.ump.dao.mapper.SysRoleMapper;
import com.jww.ump.model.SysRoleMenuModel;
import com.jww.ump.model.SysRoleModel;
import com.jww.ump.rpc.api.SysRoleMenuService;
import com.jww.ump.rpc.api.SysRoleService;
import com.xiaoleilu.hutool.lang.Assert;
import com.xiaoleilu.hutool.util.ObjectUtil;
import com.xiaoleilu.hutool.util.StrUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 角色信息表 服务实现类
 * </p>
 *
 * @author wanyong
 * @since 2017-12-17
 */
@Service("sysRoleService")
public class SysRoleServiceImpl extends BaseServiceImpl<SysRoleMapper, SysRoleModel> implements SysRoleService {

    @Autowired
    private SysRoleMapper sysRoleMapper;

    @Autowired
    private SysRoleMenuService sysRoleMenuService;

    @Override
    public Page<SysRoleModel> queryListPage(Page<SysRoleModel> page) {
        SysRoleModel sysRoleModel = new SysRoleModel();
        sysRoleModel.setIsDel(0);
        EntityWrapper<SysRoleModel> entityWrapper = new EntityWrapper<>(sysRoleModel);
        if (ObjectUtil.isNotNull(page.getCondition())) {
            StringBuilder conditionSql = new StringBuilder();
            Map<String, Object> paramMap = page.getCondition();
            String deptId = "dept_id";
            paramMap.forEach((k, v) -> {
                if (StrUtil.isNotBlank(v + "")) {
                    if (deptId.equals(k)) {
                        conditionSql.append(k + " = " + v + " AND ");
                    } else {
                        conditionSql.append(k + " like '%" + v + "%' AND ");
                    }
                }
            });
            entityWrapper.and(StrUtil.removeSuffix(conditionSql.toString(), "AND "));
        }
        page.setCondition(null);

        return page.setRecords(sysRoleMapper.selectRoleList(page, entityWrapper));
    }

    @Override
    public SysRoleModel add(SysRoleModel sysRoleModel) {
        // 根据角色名称和部门检查是否存在相同的角色
        SysRoleModel checkModel = new SysRoleModel();
        checkModel.setIsDel(0);
        checkModel.setRoleName(sysRoleModel.getRoleName());
        checkModel.setDeptId(sysRoleModel.getDeptId());
        EntityWrapper<SysRoleModel> entityWrapper = new EntityWrapper<>(checkModel);
        if (ObjectUtil.isNotNull(super.selectOne(entityWrapper))) {
            throw new BusinessException("已存在相同名称的角色");
        }
        SysRoleModel result = super.add(sysRoleModel);
        if (result != null) {
            sysRoleMenuService.insertBatch(getRoleMenuListByMenuIds(sysRoleModel, sysRoleModel.getMenuIdList()));
        }
        return result;
    }


    @Override
    @CacheEvict(value = "data")
    @Transactional(rollbackFor = Exception.class)
    public SysRoleModel modifyById(SysRoleModel sysRoleModel) {
        SysRoleModel result = super.modifyById(sysRoleModel);
        SysRoleMenuModel sysRoleMenuModel = new SysRoleMenuModel();
        sysRoleMenuModel.setRoleId(sysRoleModel.getId());
        EntityWrapper<SysRoleMenuModel> entityWrapper = new EntityWrapper<>(sysRoleMenuModel);
        // 关系数据相对不重要，直接数据库删除
        sysRoleMenuService.delete(entityWrapper);
        sysRoleMenuService.insertBatch(getRoleMenuListByMenuIds(sysRoleModel, sysRoleModel.getMenuIdList()));
        return result;
    }

    /**
     * 根据角色实体和角色对应的菜单ID集合获取角色菜单实体集合
     *
     * @param sysRoleModel
     * @param menuIds
     * @return
     * @author wanyong
     * @date 2017-12-24 14:49
     */
    private List<SysRoleMenuModel> getRoleMenuListByMenuIds(SysRoleModel sysRoleModel, List<Long> menuIds) {
        List<SysRoleMenuModel> sysRoleMenuModelList = new ArrayList<>(5);
        for (Long menuId : menuIds) {
            SysRoleMenuModel sysRoleMenuModel = new SysRoleMenuModel();
            sysRoleMenuModel.setRoleId(sysRoleModel.getId());
            sysRoleMenuModel.setMenuId(menuId);
            // sysRoleMenuModel.setPermission("123");
            // 取sysRoleModel的修改人作为sysRoleMenuModel的创建人
            sysRoleMenuModel.setCreateBy(sysRoleModel.getUpdateBy());
            sysRoleMenuModel.setUpdateBy(sysRoleModel.getUpdateBy());
            sysRoleMenuModelList.add(sysRoleMenuModel);
        }
        return sysRoleMenuModelList;
    }

    @Override
    public List<SysRoleModel> queryRoles(Long deptId) {
        Assert.notNull(deptId);
        EntityWrapper<SysRoleModel> entityWrapper = new EntityWrapper<>();
        entityWrapper.eq("dept_id", deptId);
        return sysRoleMapper.selectList(entityWrapper);
    }
}
