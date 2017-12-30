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
public class SysMenuController extends BaseController {

    @Autowired
    private SysMenuService sysMenuService;

    /**
     * 查询所有菜单
     *
     * @return ResultModel<List<SysMenuModel>>
     * @author wanyong
     * @date 2017-12-02 00:24
     */
    @PostMapping("/queryList")
    @RequiresAuthentication
    public ResultModel<List<SysMenuModel>> queryList() {
        return ResultUtil.ok(sysMenuService.queryList());
    }

    /**
     * 分页查询菜单列表
     *
     * @param pageModel
     * @return com.jww.common.web.model.ResultModel<java.util.List<com.jww.ump.model.SysMenuModel>>
     * @author shadj
     * @date 2017/12/18 21:34
     */
    @PostMapping("/queryListPage")
    @RequiresPermissions("sys:menu:read")
    public ResultModel<List<SysMenuModel>> queryListPage(@RequestBody PageModel pageModel) {
        return ResultUtil.ok(sysMenuService.queryListPage(pageModel));
    }

    /**
     * 查询用户权限菜单
     *
     * @param userId
     * @return ResultModel
     * @author shadj
     * @date 2017/12/18 21:36
     */
    @GetMapping("/tree/{userId}")
    @RequiresAuthentication
    public ResultModel<List<SysMenuModel>> queryMenuTreeByUserId(@PathVariable(value = "userId") Long userId) {
        return ResultUtil.ok(sysMenuService.queryMenuTreeByUserId(userId));
    }

    /**
     * 根据ID删除菜单
     *
     * @param id
     * @return ResultModel
     * @author shadj
     * @date 2017/12/18 21:51
     */
    @DeleteMapping("/delete")
    @RequiresPermissions("sys:menu:delete")
    @SysLogOpt(module = "菜单管理", value = "菜单删除", operationType = Constants.LogOptEnum.DELETE)
    public ResultModel delete(@RequestBody Long id) {
        return ResultUtil.ok(sysMenuService.delete(id));
    }

    /**
     * 批量删除菜单
     *
     * @param ids
     * @return ResultModel
     * @author shadj
     * @date 2017/12/18 21:52
     */
    @PostMapping("/deleteBatchIds")
    @RequiresPermissions("sys:menu:delete")
    @SysLogOpt(module = "菜单管理", value = "菜单批量删除", operationType = Constants.LogOptEnum.DELETE)
    public ResultModel deleteBatchIds(@RequestBody Long[] ids) {
        Assert.notNull(ids);
        return ResultUtil.ok(sysMenuService.deleteBatch(ids));
    }

    @GetMapping("/query/{id}")
    @RequiresPermissions("sys:menu:read")
    public ResultModel<SysMenuModel> query(@PathVariable Long id) {
        Assert.notNull(id);
        SysMenuModel sysMenuModel = sysMenuService.selectById(id);
        return ResultUtil.ok(sysMenuModel);
    }

    /**
     * 根据ID修改菜单
     *
     * @param sysMenuModel
     * @return ResultModel
     * @author shadj
     * @date 2017/12/18 21:54
     */
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
     * @param sysMenuModel
     * @return ResultModel
     * @author shadj
     * @date 2017/12/18 21:54
     */
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

    @PostMapping("/roleFuncTree")
    @RequiresPermissions("sys:menu:read")
    public ResultModel queryFuncMenuTree(@RequestBody Long roleId) {
        List<SysTreeModel> treeModelList = sysMenuService.queryFuncMenuTree(roleId);
        return ResultUtil.ok(treeModelList);
    }

    @PostMapping("/funcTree")
    @RequiresPermissions("sys:menu:read")
    public ResultModel queryFuncMenuTree() {
        List<SysTreeModel> treeModelList = sysMenuService.queryFuncMenuTree(null);
        return ResultUtil.ok(treeModelList);
    }

    @GetMapping("/queryTree/{menuType}/{menuId}")
    @RequiresPermissions("sys:menu:update")
    public ResultModel queryTree(@PathVariable(required = false) Integer menuType, @PathVariable Long menuId) {
        List<SysTreeModel> list = sysMenuService.queryTree(menuId, menuType);
        return ResultUtil.ok(list);
    }

    @GetMapping("/queryTree/{menuType}")
    @RequiresPermissions("sys:menu:add")
    public ResultModel queryTree(@PathVariable(required = false) Integer menuType) {
        List<SysTreeModel> list = sysMenuService.queryTree(null, menuType);
        return ResultUtil.ok(list);
    }
}

