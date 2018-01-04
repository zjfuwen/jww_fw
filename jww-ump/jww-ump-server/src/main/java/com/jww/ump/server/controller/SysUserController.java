package com.jww.ump.server.controller;

import com.jww.common.core.Constants;
import com.jww.common.core.exception.BusinessException;
import com.jww.common.core.model.PageModel;
import com.jww.common.core.util.SecurityUtil;
import com.jww.common.web.BaseController;
import com.jww.common.web.model.ResultModel;
import com.jww.common.web.util.ResultUtil;
import com.jww.common.web.util.WebUtil;
import com.jww.ump.model.SysUserModel;
import com.jww.ump.model.SysUserRoleModel;
import com.jww.ump.rpc.api.SysUserService;
import com.jww.ump.server.annotation.SysLogOpt;
import com.xiaoleilu.hutool.lang.Assert;
import com.xiaoleilu.hutool.util.ObjectUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;

/**
 * 用户管理控制器
 *
 * @author wanyong
 * @date 2017/11/17 00:22
 */
@Slf4j
@Api(value = "用户管理", description = "用户管理")
@RestController
@RequestMapping("/user")
public class SysUserController extends BaseController {

    @Autowired
    private SysUserService sysUserService;

    /**
     * 根据用户ID查询用户
     *
     * @param id
     * @return ResultModel<SysUserModel>
     * @author wanyong
     * @date 2017-12-05 13:35
     */
    @ApiOperation(value = "查询用户", notes = "根据用户主键ID查询用户")
    @ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "Long")
    @GetMapping("/query/{id}")
    @RequiresPermissions("sys:user:read")
    public ResultModel query(@PathVariable(value = "id") Long id) {
        Assert.notNull(id);
        SysUserModel sysUserModel = sysUserService.queryOne(id);
        return ResultUtil.ok(sysUserModel);
    }

    /**
     * 分页查询用户列表
     *
     * @param pageModel 分页实体
     * @return ResultModel
     * @author wanyong
     * @date 2017/12/2 14:31
     */
    @ApiOperation(value = "分页查询用户列表", notes = "根据分页参数查询用户列表")
    @PostMapping("/listPage")
    @RequiresPermissions("sys:user:read")
    public ResultModel queryListPage(@RequestBody PageModel pageModel) {
        return ResultUtil.ok(sysUserService.queryListPage(pageModel));
    }

    /**
     * 新增用户
     *
     * @param sysUserModel 用户实体
     * @return ResultModel
     * @author wanyong
     * @date 2017-12-03 10:18
     */
    @ApiOperation(value = "新增用户", notes = "根据用户实体新增用户")
    @PostMapping("/add")
    @RequiresPermissions("sys:user:add")
    @SysLogOpt(module = "用户管理", value = "用户新增", operationType = Constants.LogOptEnum.ADD)
    public ResultModel add(@Valid @RequestBody SysUserModel sysUserModel) {
        SysUserModel existSysUserModel = sysUserService.queryByAccount(sysUserModel.getAccount());
        if (ObjectUtil.isNotNull(existSysUserModel)) {
            throw new BusinessException("已存在相同账号的用户");
        }
        // 设置初始密码: 123456
        sysUserModel.setPassword(SecurityUtil.encryptPassword("123456"));
        sysUserModel.setCreateBy(getCurrUser());
        sysUserService.add(sysUserModel);
        return ResultUtil.ok();
    }

    /**
     * 根据用户ID集合批量删除用户
     *
     * @param ids 用户ID集合
     * @return ResultModel
     * @author wanyong
     * @date 2018-01-04 11:32
     */
    @ApiOperation(value = "批量删除用户", notes = "根据主键ID集合批量删除用户")
    @PostMapping("/delBatchByIds")
    @RequiresPermissions("sys:user:delete")
    @SysLogOpt(module = "用户管理", value = "用户批量删除", operationType = Constants.LogOptEnum.DELETE)
    public ResultModel delBatchByIds(@RequestBody List<Long> ids) {
        if (ids.size() == 0) {
            throw new BusinessException("用户ID集合不能为空");
        }
        return ResultUtil.ok(sysUserService.delBatchByIds(ids));
    }

    /**
     * 修改用户
     *
     * @param sysUserModel 用户实体
     * @return ResultModel
     * @author wanyong
     * @date 2018-01-04 11:33
     */
    @ApiOperation(value = "修改用户", notes = "根据用户ID修改用户")
    @PostMapping("/modify")
    @RequiresPermissions("sys:user:update")
    @SysLogOpt(module = "用户管理", value = "用户修改", operationType = Constants.LogOptEnum.MODIFY)
    public ResultModel modify(@RequestBody SysUserModel sysUserModel) {
        sysUserModel.setCreateBy(this.getCurrUser());
        sysUserModel.setUpdateTime(new Date());
        return ResultUtil.ok(sysUserService.modifyUser(sysUserModel));
    }

    /**
     * 个人资料修改
     *
     * @param sysUserModel 用户实体
     * @return ResultModel
     * @author wanyong
     * @date 2018-01-04 11:33
     */
    @ApiOperation(value = "修改个人资料", notes = "根据用户ID修改用户个人资料")
    @PostMapping("/modifyMySelf")
    @SysLogOpt(module = "用户管理", value = "个人资料修改", operationType = Constants.LogOptEnum.MODIFY)
    public ResultModel modifyMySelf(@RequestBody SysUserModel sysUserModel) {
        if (!sysUserModel.getId().equals(WebUtil.getCurrentUser())) {
            throw new BusinessException("不能修改其他用户信息");
        }
        sysUserModel.setCreateBy(this.getCurrUser());
        sysUserModel.setUpdateTime(new Date());
        return ResultUtil.ok(sysUserService.modifyById(sysUserModel));
    }

    /**
     * 根据用户id查询用户角色关系
     *
     * @param userId 用户ID
     * @return com.jww.common.web.model.ResultModel
     * @author RickyWang
     * @date 17/12/25 21:26:57
     */
    @ApiOperation(value = "查询用户角色关系", notes = "根据用户id查询用户角色关系")
    @GetMapping("/queryUserRoles/{userId}")
    @RequiresPermissions("sys:user:read")
    public ResultModel queryUserRoles(@PathVariable(value = "userId") Long userId) {
        Assert.notNull(userId);
        List<SysUserRoleModel> list = sysUserService.queryUserRoles(userId);
        return ResultUtil.ok(list);
    }

    /**
     * 修改密码
     *
     * @param sysUserModel 用户实体
     * @return ResultModel
     * @author wanyong
     * @date 2017/12/30 22:18
     */
    @ApiOperation(value = "修改密码", notes = "修改密码")
    @PostMapping("/modifyPassword")
    @RequiresPermissions("sys:user:update")
    @SysLogOpt(module = "用户管理", value = "修改密码", operationType = Constants.LogOptEnum.MODIFY)
    public ResultModel modifyPassword(@RequestBody SysUserModel sysUserModel) {
        Assert.notEmpty(sysUserModel.getOldPassword());
        Assert.notEmpty(sysUserModel.getPassword());
        String encryptOldPassword = SecurityUtil.encryptPassword(sysUserModel.getOldPassword());
        SysUserModel currentSysUserModel = sysUserService.queryById(getCurrUser());
        if (!encryptOldPassword.equals(currentSysUserModel.getPassword())) {
            throw new BusinessException("旧密码不正确");
        }
        String encryptPassword = SecurityUtil.encryptPassword(sysUserModel.getPassword());
        sysUserModel.setPassword(encryptPassword);
        sysUserModel.setId(getCurrUser());
        return ResultUtil.ok(sysUserService.modifyById(sysUserModel));
    }

}
