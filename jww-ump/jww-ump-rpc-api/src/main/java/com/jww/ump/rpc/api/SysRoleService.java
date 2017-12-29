package com.jww.ump.rpc.api;

import com.baomidou.mybatisplus.plugins.Page;
import com.jww.common.core.base.BaseService;
import com.jww.ump.model.SysRoleModel;

import java.util.List;

/**
 * <p>
 * 角色信息表 服务类
 * </p>
 *
 * @author wanyong
 * @since 2017-12-17
 */
public interface SysRoleService extends BaseService<SysRoleModel> {

    /**
     * 分页查找所有角色
     *
     * @param page
     * @return Page<SysRoleModel>
     * @author wanyong
     * @date 2017/12/4 14:45
     */
    Page<SysRoleModel> queryListPage(Page<SysRoleModel> page);


    /**
     * 新增角色
     *
     * @param sysRoleModel
     * @return SysRoleModel
     * @author wanyong
     * @date 2017-12-19 15:37
     */
    @Override
    SysRoleModel add(SysRoleModel sysRoleModel);

    /**
     * 根据角色ID修改
     *
     * @param sysRoleModel
     * @return SysRoleModel
     * @author wanyong
     * @date 2017-12-24 14:41
     */
    @Override
    SysRoleModel modifyById(SysRoleModel sysRoleModel);

    /**
     * 查询部门下所有角色
     *
     * @param deptId
     * @return List<SysRoleModel>
     * @author shadj
     * @date 2017/12/29 17:31
     */
    List<SysRoleModel> queryRoles(Long deptId);
}
