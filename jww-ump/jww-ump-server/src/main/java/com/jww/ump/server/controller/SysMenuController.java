package com.jww.ump.server.controller;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.jww.common.core.model.PageModel;
import com.jww.common.web.BaseController;
import com.jww.common.web.model.ResultModel;
import com.jww.common.web.util.ResultUtil;
import com.jww.ump.model.UmpDeptModel;
import com.jww.ump.model.UmpMenuModel;
import com.jww.ump.model.UmpTreeModel;
import com.jww.ump.rpc.api.UmpMenuService;
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
    @PostMapping("/delete")
    public ResultModel delete(@RequestBody Long id) {
        return ResultUtil.ok(umpMenuService.deleteById(id));
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
    public ResultModel deleteBatchIds(@RequestBody List<Long> ids) {
        Assert.notNull(ids);
        return ResultUtil.ok(umpMenuService.deleteBatchIds(ids));
    }

    @GetMapping("/query/{id}")
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
    public ResultModel modify(@RequestBody UmpMenuModel umpMenuModel) {
        if (umpMenuModel.getEnable() == null) {
            umpMenuModel.setEnable(0);
        }
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
    public ResultModel queryFuncMenuTree(@RequestBody Long roleId) {
        List<UmpTreeModel> treeModelList = umpMenuService.queryFuncMenuTree(roleId);
        return ResultUtil.ok(treeModelList);
    }

    @PostMapping("/funcTree")
    public ResultModel queryFuncMenuTree() {
        List<UmpTreeModel> treeModelList = umpMenuService.queryFuncMenuTree(null);
        return ResultUtil.ok(treeModelList);
    }

    @GetMapping("/queryTree/{id}")
    public ResultModel queryTree(@PathVariable(required = false) Long id) {
        List<UmpTreeModel> list = umpMenuService.queryTree(id);
        return ResultUtil.ok(list);
    }

    @GetMapping("/queryTree")
    public ResultModel queryTree() {
        List<UmpTreeModel> list = umpMenuService.queryTree(null);
        return ResultUtil.ok(list);
    }
}

