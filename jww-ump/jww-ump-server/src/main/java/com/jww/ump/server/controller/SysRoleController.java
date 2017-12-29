package com.jww.ump.server.controller;

import com.jww.common.core.Constants;
import com.jww.common.core.exception.BusinessException;
import com.jww.common.core.model.PageModel;
import com.jww.common.web.BaseController;
import com.jww.common.web.model.ResultModel;
import com.jww.common.web.util.ResultUtil;
import com.jww.ump.model.SysDeptModel;
import com.jww.ump.model.SysRoleModel;
import com.jww.ump.rpc.api.SysDeptService;
import com.jww.ump.rpc.api.SysRoleService;
import com.jww.ump.server.annotation.SysLogOpt;
import com.xiaoleilu.hutool.lang.Assert;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

/**
 * <p>
 * 角色信息表 前端控制器
 * </p>
 *
 * @author wanyong
 * @since 2017-11-17
 */
@RestController
@RequestMapping("/role")
public class SysRoleController extends BaseController {

    @Autowired
    private SysRoleService sysRoleService;

    @Autowired
    private SysDeptService sysDeptService;

    /**
     * 根据角色ID查询角色
     *
     * @param roleId
     * @return ResultModel<SysRoleModel>
     * @author wanyong
     * @date 2017-12-05 13:35
     */
    @ApiOperation(value = "查询角色", notes = "根据角色主键ID查询角色")
    @ApiImplicitParam(name = "id", value = "角色ID", required = true, dataType = "Long")
    @PostMapping("/query")
    @RequiresPermissions("sys:role:read")
    public ResultModel<SysRoleModel> query(@RequestBody Long roleId) {
        Assert.notNull(roleId);
        SysRoleModel sysRoleModel = sysRoleService.queryById(roleId);
        SysDeptModel sysDeptModel = sysDeptService.queryById(sysRoleModel.getDeptId());
        sysRoleModel.setDeptName(sysDeptModel.getDeptName());
        return ResultUtil.ok(sysRoleModel);
    }

    @PostMapping("/listPage")
    @RequiresPermissions("sys:role:read")
    public ResultModel queryListPage(@RequestBody PageModel pageModel) {
        pageModel = (PageModel<SysRoleModel>) sysRoleService.queryListPage(pageModel);
        return ResultUtil.ok(pageModel);
    }

    @PostMapping("/add")
    @RequiresPermissions("sys:role:add")
    @SysLogOpt(module = "角色管理", value = "角色新增", operationType = Constants.LogOptEnum.ADD)
    public ResultModel add(@Valid @RequestBody SysRoleModel sysRoleModel) {
        sysRoleModel.setCreateBy(getCurrUser());
        sysRoleModel.setUpdateBy(getCurrUser());
        return ResultUtil.ok(sysRoleService.add(sysRoleModel));
    }

    @PostMapping("/modify")
    @RequiresPermissions("sys:role:update")
    @SysLogOpt(module = "角色管理", value = "角色修改", operationType = Constants.LogOptEnum.MODIFY)
    public ResultModel modify(@Valid @RequestBody SysRoleModel sysRoleModel) {
        sysRoleModel.setUpdateBy(getCurrUser());
        sysRoleService.modifyById(sysRoleModel);
        return ResultUtil.ok();
    }

    /**
     * 根据角色ID集合批量删除
     *
     * @param ids
     * @return ResultModel
     * @author wanyong
     * @date 2017-12-23 02:46
     */
    @PostMapping("/delBatchByIds")
    @RequiresPermissions("sys:role:delete")
    @SysLogOpt(module = "角色管理", value = "角色批量删除", operationType = Constants.LogOptEnum.DELETE)
    public ResultModel delBatchByIds(@RequestBody List<Long> ids) {
        if (ids.size() == 0) {
            throw new BusinessException("角色ID集合不能为空");
        }
        return ResultUtil.ok(sysRoleService.delBatchByIds(new SysRoleModel(), ids));
    }
}

