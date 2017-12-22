package com.jww.ump.rpc.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.jww.common.core.base.BaseServiceImpl;
import com.jww.common.core.exception.BusinessException;
import com.jww.ump.dao.mapper.UmpRoleMapper;
import com.jww.ump.model.UmpRoleMenuModel;
import com.jww.ump.model.UmpRoleModel;
import com.jww.ump.rpc.api.UmpRoleMenuService;
import com.jww.ump.rpc.api.UmpRoleService;
import com.xiaoleilu.hutool.util.ObjectUtil;
import com.xiaoleilu.hutool.util.StrUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
@Service("umpRoleService")
public class UmpRoleServiceImpl extends BaseServiceImpl<UmpRoleMapper, UmpRoleModel> implements UmpRoleService {

    @Autowired
    private UmpRoleMapper umpRoleMapper;

    @Autowired
    private UmpRoleMenuService umpRoleMenuService;

    @Override
    public Page<UmpRoleModel> queryListPage(Page<UmpRoleModel> page) {
        UmpRoleModel umpRoleModel = new UmpRoleModel();
        umpRoleModel.setIsDel(0);
        EntityWrapper<UmpRoleModel> entityWrapper = new EntityWrapper<>(umpRoleModel);
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

        return page.setRecords(umpRoleMapper.selectRoleList(page, entityWrapper));
    }

    @Override
    public UmpRoleModel add(UmpRoleModel umpRoleModel) throws BusinessException {
        // 根据角色名称和部门检查是否存在相同的角色
        UmpRoleModel checkModel = new UmpRoleModel();
        checkModel.setIsDel(0);
        checkModel.setRoleName(umpRoleModel.getRoleName());
        checkModel.setDeptId(umpRoleModel.getDeptId());
        EntityWrapper<UmpRoleModel> entityWrapper = new EntityWrapper<>(checkModel);
        if (ObjectUtil.isNotNull(super.selectOne(entityWrapper))) {
            throw new BusinessException("已存在相同名称的角色");
        }
        UmpRoleModel result = super.add(umpRoleModel);
        if (result != null) {
            List<UmpRoleMenuModel> umpRoleMenuModelList = new ArrayList<>(5);
            for (Long menuId : umpRoleModel.getMenuIdList()) {
                UmpRoleMenuModel umpRoleMenuModel = new UmpRoleMenuModel();
                umpRoleMenuModel.setRoleId(umpRoleModel.getId());
                umpRoleMenuModel.setMenuId(menuId);
                umpRoleMenuModel.setPermission("123");
                umpRoleMenuModel.setCreateBy(umpRoleModel.getCreateBy());
                umpRoleMenuModel.setUpdateBy(umpRoleModel.getUpdateBy());
                umpRoleMenuModelList.add(umpRoleMenuModel);
            }
            umpRoleMenuService.insertBatch(umpRoleMenuModelList);
        }
        return result;
    }
}
