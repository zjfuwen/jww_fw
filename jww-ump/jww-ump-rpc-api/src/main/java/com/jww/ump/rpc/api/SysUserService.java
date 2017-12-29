package com.jww.ump.rpc.api;

import com.baomidou.mybatisplus.plugins.Page;
import com.jww.common.core.base.BaseService;
import com.jww.ump.model.SysRoleModel;
import com.jww.ump.model.SysUserModel;
import com.jww.ump.model.SysUserRoleModel;

import java.util.List;

/**
 * 用户管理服务
 *
 * @author wanyong
 * @date 2017/11/17 16:43
 */
public interface SysUserService extends BaseService<SysUserModel> {

    /**
     * 根据账号查找用户
     *
     * @param account 账号
     * @return SysUserModel
     * @author wanyong
     * @date 2017-12-05 12:48
     */
    SysUserModel queryByAccount(String account);

    /**
     * 分页查找所有用户
     *
     * @param page 分页对象
     * @return Page<SysUserModel>
     * @author wanyong
     * @date 2017/12/4 14:45
     */
    Page<SysUserModel> queryListPage(Page<SysUserModel> page);

    /**
     * 根据ID集合批量删除
     *
     * @param ids 用户ID集合
     * @return boolean
     * @author wanyong
     * @date 2017-12-05 19:50
     */
    boolean delBatchByIds(List<Long> ids);

    /**
     * 根据部门ID查询角色
     *
     * @param deptId 部门ID
     * @return java.util.List<com.jww.ump.model.SysRoleModel>
     * @author RickyWang
     * @date 17/12/25 15:47:20
     */
    List<SysRoleModel> queryRoles(Long deptId);

    /**
     * 根据用户查询用户角色关系
     *
     * @param userId 用户ID
     * @return java.util.List<com.jww.ump.model.SysUserRoleModel>
     * @author RickyWang
     * @date 17/12/25 21:20:55
     */
    List<SysUserRoleModel> queryUserRoles(Long userId);

    /**
     * 根据用户ID查询
     *
     * @param id 用户ID
     * @return SysUserModel
     * @author wanyong
     * @date 2017-12-27 12:09
     */
    SysUserModel queryOne(Long id);

    /**
     * 修改
     *
     * @param sysUserModel 用户实体
     * @return boolean
     * @author wanyong
     * @date 2017-12-27 12:09
     */
    boolean modifyUser(SysUserModel sysUserModel);
}
