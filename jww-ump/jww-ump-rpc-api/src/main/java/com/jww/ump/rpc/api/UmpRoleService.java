package com.jww.ump.rpc.api;

import com.baomidou.mybatisplus.plugins.Page;
import com.jww.common.core.base.BaseService;
import com.jww.ump.model.UmpRoleModel;

/**
 * <p>
 * 角色信息表 服务类
 * </p>
 *
 * @author wanyong
 * @since 2017-12-17
 */
public interface UmpRoleService extends BaseService<UmpRoleModel> {

    /**
     * 分页查找所有角色
     *
     * @param page
     * @return Page<UmpRoleModel>
     * @author wanyong
     * @date 2017/12/4 14:45
     */
    Page<UmpRoleModel> queryListPage(Page<UmpRoleModel> page);


    /**
     * 新增角色
     *
     * @param umpRoleModel
     * @return UmpRoleModel
     * @author wanyong
     * @date 2017-12-19 15:37
     */
    @Override
    UmpRoleModel add(UmpRoleModel umpRoleModel);

    /**
     * 根据角色ID修改
     *
     * @param umpRoleModel
     * @return UmpRoleModel
     * @author wanyong
     * @date 2017-12-24 14:41
     */
    @Override
    UmpRoleModel modifyById(UmpRoleModel umpRoleModel);

}
