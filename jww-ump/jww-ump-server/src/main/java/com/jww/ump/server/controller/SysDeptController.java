package com.jww.ump.server.controller;

import com.jww.common.core.Constants;
import com.jww.common.core.model.PageModel;
import com.jww.common.web.BaseController;
import com.jww.common.web.model.ResultModel;
import com.jww.common.web.util.ResultUtil;
import com.jww.ump.model.SysDeptModel;
import com.jww.ump.model.SysTreeModel;
import com.jww.ump.rpc.api.SysDeptService;
import com.jww.ump.server.annotation.SysLogOpt;
import com.xiaoleilu.hutool.lang.Assert;
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
 * 部门管理控制器
 *
 * @author: Ricky Wang
 * @Date: 17/12/1 11:23:17
 */
@RestController
@RequestMapping("/dept")
@Slf4j
@Api(value = "部门管理", description = "部门管理")
public class SysDeptController extends BaseController {

    @Autowired
    private SysDeptService sysDeptService;

    /**
     * 根据部门ID查询
     *
     * @param id
     * @return ResultModel<SysDeptModel>
     * @author RickyWang
     * @date 2017-12-05 13:35
     */
    @ApiOperation(value = "查询部门", notes = "根据部门主键ID查询部门")
    @ApiImplicitParam(name = "id", value = "部门ID", required = true, dataType = "Long")
    @GetMapping("/query/{id}")
    @RequiresPermissions("sys:dept:read")
    public ResultModel<SysDeptModel> query(@PathVariable Long id) {
        Assert.notNull(id);
        SysDeptModel sysDeptModel = sysDeptService.queryOne(id);
        return ResultUtil.ok(sysDeptModel);
    }

    /**
     * 查询部门分页方法
     *
     * @param pageModel
     * @return com.jww.common.web.model.ResultModel
     * @author RickyWang
     * @date 17/12/25 21:28:13
     */
    @PostMapping("/queryListPage")
    @RequiresPermissions("sys:dept:read")
    public ResultModel queryListPage(@RequestBody PageModel<SysDeptModel> pageModel) {
        pageModel = (PageModel<SysDeptModel>) sysDeptService.queryListPage(pageModel);
        return ResultUtil.ok(pageModel);
    }

    /**
     * 新增部门方法
     *
     * @param sysDeptModel
     * @return com.jww.common.web.model.ResultModel
     * @author RickyWang
     * @date 17/12/25 21:28:41
     */
    @PostMapping("/add")
    @RequiresPermissions("sys:dept:add")
    @SysLogOpt(module = "部门管理", value = "部门新增", operationType = Constants.LogOptEnum.ADD)
    public ResultModel add(@Valid @RequestBody SysDeptModel sysDeptModel) {
        log.info("DeptController->add: SysDeptModel={}", sysDeptModel);
        if (sysDeptModel != null) {
            sysDeptModel.setCreateBy(this.getCurrUser());
            sysDeptModel.setUpdateBy(this.getCurrUser());
        }
        return ResultUtil.ok(sysDeptService.addDept(sysDeptModel));
    }

    /**
     * 修改部门方法
     *
     * @param sysDeptModel
     * @return com.jww.common.web.model.ResultModel
     * @author RickyWang
     * @date 17/12/25 21:29:09
     */
    @PutMapping("/modify")
    @RequiresPermissions("sys:dept:update")
    @SysLogOpt(module = "部门管理", value = "部门修改", operationType = Constants.LogOptEnum.MODIFY)
    public ResultModel modify(@RequestBody SysDeptModel sysDeptModel) {
        log.info("DeptController->mod: SysDeptModel={}", sysDeptModel);
        sysDeptModel.setUpdateBy(this.getCurrUser());
        sysDeptModel.setUpdateTime(new Date());
        sysDeptService.modifyById(sysDeptModel);
        return ResultUtil.ok();
    }

    /**
     * 批量删除部门方法
     *
     * @param ids
     * @return com.jww.common.web.model.ResultModel
     * @author RickyWang
     * @date 17/12/25 21:29:23
     */
    @DeleteMapping("/delBatchByIds")
    @RequiresPermissions("sys:dept:delete")
    @SysLogOpt(module = "部门管理", value = "部门批量删除", operationType = Constants.LogOptEnum.DELETE)
    public ResultModel delBatchByIds(@RequestBody Long[] ids) {
        Assert.notNull(ids);
        return ResultUtil.ok(sysDeptService.deleteBatch(ids));
    }

    /**
     * 根据部门id过滤查询部门树方法
     *
     * @param id
     * @return com.jww.common.web.model.ResultModel
     * @author RickyWang
     * @date 17/12/25 21:29:52
     */
    @GetMapping("/queryTree/{id}")
    @RequiresPermissions("sys:dept:read")
    public ResultModel queryTree(@PathVariable(value = "id", required = false) Long id) {
        List<SysTreeModel> list = sysDeptService.queryTree(id);
        return ResultUtil.ok(list);
    }

    /**
     * 查询部门树方法
     *
     * @param
     * @return com.jww.common.web.model.ResultModel
     * @author RickyWang
     * @date 17/12/25 21:30:28
     */
    @GetMapping("/queryTree")
    @RequiresPermissions("sys:dept:read")
    public ResultModel queryTree() {
        List<SysTreeModel> list = sysDeptService.queryTree();
        return ResultUtil.ok(list);
    }

    /**
     * 删除部门方法
     *
     * @param id
     * @return com.jww.common.web.model.ResultModel
     * @author RickyWang
     * @date 17/12/25 21:30:45
     */
    @DeleteMapping("/delDept")
    @RequiresPermissions("sys:dept:delete")
    @SysLogOpt(module = "部门管理", value = "部门删除", operationType = Constants.LogOptEnum.DELETE)
    public ResultModel delDept(@RequestBody Long id) {
        Assert.notNull(id);
        return ResultUtil.ok(sysDeptService.delDept(id));
    }

}
