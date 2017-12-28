package com.jww.ump.server.controller;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.jww.common.core.Constants;
import com.jww.common.core.model.PageModel;
import com.jww.common.web.BaseController;
import com.jww.common.web.model.ResultModel;
import com.jww.common.web.util.ResultUtil;
import com.jww.ump.model.UmpDeptModel;
import com.jww.ump.model.UmpMenuModel;
import com.jww.ump.model.UmpTreeModel;
import com.jww.ump.rpc.api.UmpMenuService;
import com.jww.ump.server.annotation.LogData;
import com.xiaoleilu.hutool.lang.Assert;
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
    private UmpMenuService umpMenuService;

    /**
     * 查询所有菜单
     *
     * @return ResultModel<List<UmpMenuModel>>
     * @author wanyong
     * @date 2017-12-02 00:24
     */
    @PostMapping("/queryList")
    @LogData(module = "菜单管理", value = "菜单查询", operationType = Constants.LOG_OPERATION_TYPE_QUERY)
    public ResultModel<List<UmpMenuModel>> queryList() {
        return ResultUtil.ok(umpMenuService.queryList());
    }

    /**
     * 分页查询菜单列表
     *
     * @param pageModel
     * @return com.jww.common.web.model.ResultModel<java.util.List<com.jww.ump.model.UmpMenuModel>>
     * @author shadj
     * @date 2017/12/18 21:34
     */
    @PostMapping("/queryListPage")
    @LogData(module = "菜单管理", value = "菜单分页查询", operationType = Constants.LOG_OPERATION_TYPE_QUERY)
    public ResultModel<List<UmpMenuModel>> queryListPage(@RequestBody PageModel pageModel) {
        return ResultUtil.ok(umpMenuService.queryListPage(pageModel));
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
    @LogData(module = "菜单管理", value = "权限树查询", operationType = Constants.LOG_OPERATION_TYPE_QUERY)
    public ResultModel<List<UmpMenuModel>> queryMenuTreeByUserId(@PathVariable(value = "userId") Long userId) {
        return ResultUtil.ok(umpMenuService.queryMenuTreeByUserId(userId));
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
    @LogData(module = "菜单管理", value = "菜单删除", operationType = Constants.LOG_OPERATION_TYPE_DELETE)
    public ResultModel delete(@RequestBody Long id) {
        return ResultUtil.ok(umpMenuService.delete(id));
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
    @LogData(module = "菜单管理", value = "菜单批量删除", operationType = Constants.LOG_OPERATION_TYPE_DELETE)
    public ResultModel deleteBatchIds(@RequestBody Long[] ids) {
        Assert.notNull(ids);
        return ResultUtil.ok(umpMenuService.deleteBatch(ids));
    }

    @GetMapping("/query/{id}")
    @LogData(module = "菜单管理", value = "菜单实体查询", operationType = Constants.LOG_OPERATION_TYPE_QUERY)
    public ResultModel<UmpMenuModel> query(@PathVariable Long id) {
        Assert.notNull(id);
        UmpMenuModel umpMenuModel = umpMenuService.selectById(id);
        return ResultUtil.ok(umpMenuModel);
    }

    /**
     * 根据ID修改菜单
     *
     * @param umpMenuModel
     * @return ResultModel
     * @author shadj
     * @date 2017/12/18 21:54
     */
    @PostMapping("/modify")
    @LogData(module = "菜单管理", value = "菜单修改", operationType = Constants.LOG_OPERATION_TYPE_MODIFY)
    public ResultModel modify(@RequestBody UmpMenuModel umpMenuModel) {
        umpMenuModel.setUpdateBy(this.getCurrUser());
        umpMenuModel.setUpdateTime(new Date());
        umpMenuService.modifyById(umpMenuModel);
        return ResultUtil.ok();
    }

    /**
     * 新增菜单
     *
     * @param umpMenuModel
     * @return ResultModel
     * @author shadj
     * @date 2017/12/18 21:54
     */
    @PostMapping("/add")
    @LogData(module = "菜单管理", value = "菜单新增", operationType = Constants.LOG_OPERATION_TYPE_INSERT)
    public ResultModel add(@Valid @RequestBody UmpMenuModel umpMenuModel) {
        if (umpMenuModel != null) {
            Date now = new Date();
            umpMenuModel.setCreateTime(now);
            umpMenuModel.setCreateBy(this.getCurrUser());
            umpMenuModel.setUpdateBy(this.getCurrUser());
            umpMenuModel.setUpdateTime(now);
        }
        umpMenuService.insert(umpMenuModel);
        return ResultUtil.ok();
    }

    @PostMapping("/roleFuncTree")
    @LogData(module = "菜单管理", value = "菜单功能树查询", operationType = Constants.LOG_OPERATION_TYPE_QUERY)
    public ResultModel queryFuncMenuTree(@RequestBody Long roleId) {
        List<UmpTreeModel> treeModelList = umpMenuService.queryFuncMenuTree(roleId);
        return ResultUtil.ok(treeModelList);
    }

    @PostMapping("/funcTree")
    @LogData(module = "菜单管理", value = "菜单功能树查询", operationType = Constants.LOG_OPERATION_TYPE_QUERY)
    public ResultModel queryFuncMenuTree() {
        List<UmpTreeModel> treeModelList = umpMenuService.queryFuncMenuTree(null);
        return ResultUtil.ok(treeModelList);
    }

    @GetMapping("/queryTree/{menuType}/{menuId}")
    @LogData(module = "菜单管理", value = "菜单树查询", operationType = Constants.LOG_OPERATION_TYPE_QUERY)
    public ResultModel queryTree(@PathVariable(required = false) Integer menuType,@PathVariable Long menuId) {
        List<UmpTreeModel> list = umpMenuService.queryTree(menuId,menuType);
        return ResultUtil.ok(list);
    }

    @GetMapping("/queryTree/{menuType}")
    @LogData(module = "菜单管理", value = "菜单树查询", operationType = Constants.LOG_OPERATION_TYPE_QUERY)
    public ResultModel queryTree(@PathVariable(required = false) Integer menuType) {
        List<UmpTreeModel> list = umpMenuService.queryTree(null,menuType);
        return ResultUtil.ok(list);
    }
}

