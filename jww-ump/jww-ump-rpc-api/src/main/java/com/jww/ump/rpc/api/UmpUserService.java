package com.jww.ump.rpc.api;

import com.baomidou.mybatisplus.plugins.Page;
import com.jww.common.core.base.BaseService;
import com.jww.ump.model.UmpRoleModel;
import com.jww.ump.model.UmpUserModel;
import com.jww.ump.model.UmpUserRoleModel;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;

import java.util.List;
import java.util.Map;

/**
 * 用户管理服务
 *
 * @author wanyong
 * @date 2017/11/17 16:43
 */
public interface UmpUserService extends BaseService<UmpUserModel> {

    /**
     * 根据账号查找用户
     *
     * @param account
     * @return UmpUserModel
     * @author wanyong
     * @date 2017-12-05 12:48
     */
    UmpUserModel queryByAccount(String account);

    /**
     * 分页查找所有用户
     *
     * @param page
     * @return Page<UmpUserModel>
     * @author wanyong
     * @date 2017/12/4 14:45
     */
    Page<UmpUserModel> queryListPage(Page<UmpUserModel> page);

    /**
     * 根据ID集合批量删除
     *
     * @param ids
     * @return boolean
     * @author wanyong
     * @date 2017-12-05 19:50
     */
    boolean delBatchByIds(List<Long> ids);

    /**
     * 根据部门ID查询角色
     *
     * @param deptId
     * @return java.util.List<com.jww.ump.model.UmpRoleModel>
     * @author RickyWang
     * @date 17/12/25 15:47:20
     */
    public List<UmpRoleModel> queryRoles(Long deptId);

    /**
     * 根据用户查询用户角色关系
     *
     * @param userId
     * @return java.util.List<com.jww.ump.model.UmpUserRoleModel>
     * @author RickyWang
     * @date 17/12/25 21:20:55
     */
    public List<UmpUserRoleModel> queryUserRoles(Long userId);

    public UmpUserModel queryOne(Long id);

    public boolean modifyUser(UmpUserModel umpUserModel);
}
