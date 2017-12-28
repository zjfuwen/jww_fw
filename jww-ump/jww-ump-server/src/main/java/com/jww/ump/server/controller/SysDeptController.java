package com.jww.ump.server.controller;

import com.jww.common.core.Constants;
import com.jww.common.core.model.PageModel;
import com.jww.common.web.BaseController;
import com.jww.common.web.model.ResultModel;
import com.jww.common.web.util.ResultUtil;
import com.jww.ump.model.UmpDeptModel;
import com.jww.ump.model.UmpTreeModel;
import com.jww.ump.rpc.api.UmpDeptService;
import com.jww.ump.server.annotation.SysLogOpt;
import com.xiaoleilu.hutool.lang.Assert;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;

/**
 * 部门管理控制器
 *
 * @Author: Ricky Wang
 * @Date: 17/12/1 11:23:17
 */
@RestController
@RequestMapping("/dept")
@Slf4j
@Api(value = "部门管理", description = "部门管理")
public class SysDeptController extends BaseController {

    @Autowired
    private UmpDeptService umpDeptService;

    /**
     * 根据部门ID查询
     *
     * @param id
     * @return ResultModel<UmpDeptModel>
     * @author RickyWang
     * @date 2017-12-05 13:35
     */
    @ApiOperation(value = "查询部门", notes = "根据部门主键ID查询部门")
    @ApiImplicitParam(name = "id", value = "部门ID", required = true, dataType = "Long")
    @GetMapping("/query/{id}")
    public ResultModel<UmpDeptModel> query(@PathVariable Long id) {
        Assert.notNull(id);
        UmpDeptModel umpDeptModel = umpDeptService.queryOne(id);
        return ResultUtil.ok(umpDeptModel);
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
    public ResultModel queryListPage(@RequestBody PageModel<UmpDeptModel> pageModel) {
        pageModel = (PageModel<UmpDeptModel>) umpDeptService.queryListPage(pageModel);
        return ResultUtil.ok(pageModel);
    }

    /**
     * 新增部门方法
     *
     * @param umpDeptModel
     * @return com.jww.common.web.model.ResultModel
     * @author RickyWang
     * @date 17/12/25 21:28:41
     */
    @PostMapping("/add")
    @SysLogOpt(module = "部门管理", value = "部门新增", operationType = Constants.LogOptEnum.ADD)
    public ResultModel add(@Valid @RequestBody UmpDeptModel umpDeptModel) {
        log.info("DeptController->add: UmpDeptModel={}", umpDeptModel);
        if (umpDeptModel != null) {
            umpDeptModel.setUnitId(Long.valueOf(1));
            Date now = new Date();
            umpDeptModel.setCreateTime(now);
            umpDeptModel.setCreateBy(this.getCurrUser());
            umpDeptModel.setUpdateBy(this.getCurrUser());
            umpDeptModel.setUpdateTime(now);
            if (umpDeptModel.getParentId() == null) {
                umpDeptModel.setParentId(0L);
            }
        }
        return ResultUtil.ok(umpDeptService.add(umpDeptModel));
    }

    /**
     * 修改部门方法
     *
     * @param umpDeptModel
     * @return com.jww.common.web.model.ResultModel
     * @author RickyWang
     * @date 17/12/25 21:29:09
     */
    @PutMapping("/modify")
    @SysLogOpt(module = "部门管理", value = "部门修改", operationType = Constants.LogOptEnum.MODIFY)
    public ResultModel modify(@RequestBody UmpDeptModel umpDeptModel) {
        log.info("DeptController->mod: UmpDeptModel={}", umpDeptModel);
        umpDeptModel.setUpdateBy(this.getCurrUser());
        umpDeptModel.setUpdateTime(new Date());
        umpDeptService.modifyById(umpDeptModel);
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
    @SysLogOpt(module = "部门管理", value = "部门批量删除", operationType = Constants.LogOptEnum.DELETE)
    public ResultModel delBatchByIds(@RequestBody Long[] ids) {
        Assert.notNull(ids);
        return ResultUtil.ok(umpDeptService.delBatchByIds(ids));
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
    public ResultModel queryTree(@PathVariable(value = "id", required = false) Long id) {
        List<UmpTreeModel> list = umpDeptService.queryTree(id);
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
    public ResultModel queryTree() {
        List<UmpTreeModel> list = umpDeptService.queryTree();
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
    @SysLogOpt(module = "部门管理", value = "部门删除", operationType = Constants.LogOptEnum.DELETE)
    public ResultModel delDept(@RequestBody Long id) {
        Assert.notNull(id);
        return ResultUtil.ok(umpDeptService.delDept(id));
    }

}
