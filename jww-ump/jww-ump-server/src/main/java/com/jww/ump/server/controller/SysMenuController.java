package com.jww.ump.server.controller;


import com.jww.common.core.Constants;
import com.jww.common.core.model.PageModel;
import com.jww.common.web.BaseController;
import com.jww.common.web.model.ResultModel;
import com.jww.common.web.util.ResultUtil;
import com.jww.ump.model.SysMenuModel;
import com.jww.ump.model.SysTreeModel;
import com.jww.ump.rpc.api.SysMenuService;
import com.jww.ump.server.annotation.SysLogOpt;
import com.xiaoleilu.hutool.lang.Assert;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 菜单 前端控制器
 * </p>
 *
 * @author wanyong
 * @since 2017-11-29
 */
@RestController
@RequestMapping("/menu")
@Api(value = "菜单管理", description = "菜单管理")
public class SysMenuController extends BaseController {

    @Autowired
    private SysMenuService sysMenuService;

    /**
     * 查询所有菜单
     *
     * @return ResultModel
     * @author wanyong
     * @date 2017-12-02 00:24
     */
    @ApiOperation(value = "查询菜单列表", notes = "查询全部菜单列表")
    @PostMapping("/queryList")
    @RequiresAuthentication
    public ResultModel queryList() {
        return ResultUtil.ok(sysMenuService.queryList());
    }

    /**
     * 分页查询菜单列表
     *
     * @param pageModel 分页实体
     * @return ResultModel
     * @author shadj
     * @date 2017/12/18 21:34
     */
    @ApiOperation(value = "分页查询菜单列表", notes = "根据分页参数查询菜单列表")
    @PostMapping("/queryListPage")
    @RequiresPermissions("sys:menu:read")
    public ResultModel queryListPage(@RequestBody PageModel pageModel) {
        return ResultUtil.ok(sysMenuService.queryListPage(pageModel));
    }

    /**
     * 查询用户权限菜单
     *
     * @param userId 用户ID
     * @return ResultModel
     * @author shadj
     * @date 2017/12/18 21:36
     */
    @ApiOperation(value = "查询用户权限菜单", notes = "根据用户ID查询用户权限菜单")
    @GetMapping("/tree/{userId}")
    @RequiresAuthentication
    public ResultModel queryMenuTreeByUserId(@PathVariable(value = "userId") Long userId) {
        return ResultUtil.ok(sysMenuService.queryMenuTreeByUserId(userId));
    }

    /**
     * 根据ID删除菜单
     *
     * @param id 菜单ID
     * @return ResultModel
     * @author shadj
     * @date 2017/12/18 21:51
     */
    @ApiOperation(value = "删除菜单", notes = "根据菜单ID删除菜单")
    @DeleteMapping("/delete")
    @RequiresPermissions("sys:menu:delete")
    @SysLogOpt(module = "菜单管理", value = "菜单删除", operationType = Constants.LogOptEnum.DELETE)
    public ResultModel delete(@RequestBody Long id) {
        return ResultUtil.ok(sysMenuService.delete(id));
    }

    /**
     * 批量删除菜单
     *
     * @param ids 菜单ID集合
     * @return ResultModel
     * @author shadj
     * @date 2017/12/18 21:52
     */
    @ApiOperation(value = "批量删除菜单", notes = "根据主键ID集合批量删除菜单")
    @PostMapping("/deleteBatchIds")
    @RequiresPermissions("sys:menu:delete")
    @SysLogOpt(module = "菜单管理", value = "菜单批量删除", operationType = Constants.LogOptEnum.DELETE)
    public ResultModel deleteBatchIds(@RequestBody Long[] ids) {
        Assert.notNull(ids);
        return ResultUtil.ok(sysMenuService.deleteBatch(ids));
    }

    /**
     * 查询菜单
     *
     * @param id 菜单ID
     * @return ResultModel
     * @author wanyong
     * @date 2018-01-03 14:10
     */
    @ApiOperation(value = "查询菜单", notes = "根据主键ID查询菜单")
    @GetMapping("/query/{id}")
    @RequiresPermissions("sys:menu:read")
    public ResultModel query(@PathVariable(value = "id") Long id) {
        Assert.notNull(id);
        SysMenuModel sysMenuModel = sysMenuService.selectById(id);
        return ResultUtil.ok(sysMenuModel);
    }

    /**
     * 根据ID修改菜单
     *
     * @param sysMenuModel 菜单实体
     * @return ResultModel
     * @author shadj
     * @date 2017/12/18 21:54
     */
    @ApiOperation(value = "修改菜单", notes = "根据主键ID修改菜单")
    @PostMapping("/modify")
    @RequiresPermissions("sys:menu:update")
    @SysLogOpt(module = "菜单管理", value = "菜单修改", operationType = Constants.LogOptEnum.MODIFY)
    public ResultModel modify(@RequestBody SysMenuModel sysMenuModel) {
        sysMenuModel.setUpdateBy(this.getCurrUser());
        sysMenuModel.setUpdateTime(new Date());
        sysMenuService.modifyById(sysMenuModel);
        return ResultUtil.ok();
    }

    /**
     * 新增菜单
     *
     * @param sysMenuModel 菜单实体
     * @return ResultModel
     * @author shadj
     * @date 2017/12/18 21:54
     */
    @ApiOperation(value = "新增菜单", notes = "根据菜单实体新增菜单")
    @PostMapping("/add")
    @RequiresPermissions("sys:menu:add")
    @SysLogOpt(module = "菜单管理", value = "菜单新增", operationType = Constants.LogOptEnum.ADD)
    public ResultModel add(@Valid @RequestBody SysMenuModel sysMenuModel) {
        if (sysMenuModel != null) {
            Date now = new Date();
            sysMenuModel.setCreateTime(now);
            sysMenuModel.setCreateBy(this.getCurrUser());
            sysMenuModel.setUpdateBy(this.getCurrUser());
            sysMenuModel.setUpdateTime(now);
        }
        sysMenuService.add(sysMenuModel);
        return ResultUtil.ok();
    }

    /**
     * 根据角色ID查询角色对应权限功能树
     *
     * @param roleId 角色ID
     * @return ResultModel
     * @author wanyong
     * @date 2018-01-03 19:20
     */
    @ApiOperation(value = "查询权限功能树", notes = "根据角色ID查询角色对应权限功能树")
    @PostMapping("/roleFuncTree")
    @RequiresPermissions("sys:menu:read")
    public ResultModel queryFuncMenuTree(@RequestBody Long roleId) {
        List<SysTreeModel> treeModelList = sysMenuService.queryFuncMenuTree(roleId);
        return ResultUtil.ok(treeModelList);
    }

    /**
     * 查询所有权限功能树
     *
     * @return ResultModel
     * @author wanyong
     * @date 2018-01-03 19:22
     */
    @ApiOperation(value = "查询所有权限功能树", notes = "查询所有权限功能树")
    @PostMapping("/funcTree")
    @RequiresPermissions("sys:menu:read")
    public ResultModel queryFuncMenuTree() {
        List<SysTreeModel> treeModelList = sysMenuService.queryFuncMenuTree(null);
        return ResultUtil.ok(treeModelList);
    }

    /**
     * 根据菜单类型和菜单ID查询菜单树
     *
     * @param menuType 菜单类型
     * @param menuId   菜单ID
     * @return ResultModel
     * @author wanyong
     * @date 2018-01-03 19:23
     */
    @ApiOperation(value = "查询菜单树", notes = "根据菜单类型和菜单ID查询菜单树")
    @GetMapping("/queryTree/{menuType}/{menuId}")
    @RequiresPermissions("sys:menu:update")
    public ResultModel queryTree(@PathVariable(required = false, value = "menuType") Integer menuType, @PathVariable(value = "menuId") Long menuId) {
        List<SysTreeModel> list = sysMenuService.queryTree(menuId, menuType);
        return ResultUtil.ok(list);
    }

    /**
     * 根据菜单类型查询菜单树
     *
     * @param menuType 菜单类型
     * @return ResultModel
     * @author wanyong
     * @date 2018-01-03 19:25
     */
    @ApiOperation(value = "查询菜单树", notes = "根据菜单类型查询菜单树")
    @GetMapping("/queryTree/{menuType}")
    @RequiresPermissions("sys:menu:add")
    public ResultModel queryTree(@PathVariable(required = false, value = "menuType") Integer menuType) {
        List<SysTreeModel> list = sysMenuService.queryTree(null, menuType);
        return ResultUtil.ok(list);
    }
}

