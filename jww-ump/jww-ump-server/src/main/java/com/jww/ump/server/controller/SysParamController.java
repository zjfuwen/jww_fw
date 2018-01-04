package com.jww.ump.server.controller;

import com.jww.common.core.Constants;
import com.jww.common.core.exception.BusinessException;
import com.jww.common.core.model.PageModel;
import com.jww.common.web.BaseController;
import com.jww.common.web.model.ResultModel;
import com.jww.common.web.util.ResultUtil;
import com.jww.ump.model.SysParamModel;
import com.jww.ump.rpc.api.SysParamService;
import com.jww.ump.server.annotation.SysLogOpt;
import com.xiaoleilu.hutool.lang.Assert;
import com.xiaoleilu.hutool.util.CollectionUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * <p>
 * 参数管理 前端控制器
 * </p>
 *
 * @author shadj
 * @since 2017-12-24
 */
@RestController
@RequestMapping("/param")
@Api(value = "参数管理", description = "参数管理")
public class SysParamController extends BaseController {

    @Autowired
    private SysParamService sysParamService;

    /**
     * 根据参数ID查询参数
     *
     * @param paramId 参数主键
     * @return ResultModel<SysParamModel>
     * @author shadj
     * @date 2017-12-05 13:35
     */
    @ApiOperation(value = "查询参数", notes = "根据参数主键ID查询参数")
    @ApiImplicitParam(name = "id", value = "参数主键ID", required = true, dataType = "Long")
    @PostMapping("/query")
    @RequiresPermissions("sys:param:read")
    public ResultModel query(@RequestBody Long paramId) {
        Assert.notNull(paramId);
        SysParamModel sysParamModel = sysParamService.queryById(paramId);
        return ResultUtil.ok(sysParamModel);
    }

    /**
     * 分页查询参数列表
     *
     * @param pageModel 分页对象
     * @return ResultModel
     * @author wanyong
     * @date 2018-01-03 19:27
     */
    @ApiOperation(value = "分页查询参数", notes = "根据分页参数查询参数列表")
    @PostMapping("/listPage")
    @RequiresPermissions("sys:param:read")
    public ResultModel queryListPage(@RequestBody PageModel pageModel) {
        pageModel = (PageModel) sysParamService.queryListPage(pageModel);
        return ResultUtil.ok(pageModel);
    }

    /**
     * 新增参数
     *
     * @param sysParamModel 参数实体
     * @return ResultModel
     * @author shadj
     * @date 2017/12/18 21:54
     */
    @ApiOperation(value = "新增参数", notes = "根据参数实体新增参数")
    @PostMapping("/add")
    @RequiresPermissions("sys:param:add")
    @SysLogOpt(module = "参数管理", value = "参数新增", operationType = Constants.LogOptEnum.ADD)
    public ResultModel add(@Valid @RequestBody SysParamModel sysParamModel) {
        sysParamModel.setCreateBy(getCurrUser());
        sysParamModel.setUpdateBy(getCurrUser());
        return ResultUtil.ok(sysParamService.add(sysParamModel));
    }

    /**
     * 修改参数
     *
     * @param sysParamModel 参数实体
     * @return ResultModel
     * @author wanyong
     * @date 2018-01-03 22:36
     */
    @ApiOperation(value = "修改参数", notes = "根据参数ID修改参数")
    @PostMapping("/modify")
    @RequiresPermissions("sys:param:update")
    @SysLogOpt(module = "参数管理", value = "参数修改", operationType = Constants.LogOptEnum.MODIFY)
    public ResultModel modify(@RequestBody SysParamModel sysParamModel) {
        sysParamModel.setUpdateBy(getCurrUser());
        sysParamService.modifyById(sysParamModel);
        return ResultUtil.ok();
    }

    /**
     * 根据参数ID集合批量删除
     *
     * @param ids 主键集合
     * @return ResultModel
     * @author shadj
     * @date 2017-12-24 18:30
     */
    @ApiOperation(value = "批量删除参数", notes = "根据主键ID集合批量删除参数")
    @DeleteMapping("/deleteBatchByIds")
    @RequiresPermissions("sys:param:delete")
    @SysLogOpt(module = "参数管理", value = "参数批量删除", operationType = Constants.LogOptEnum.DELETE)
    public ResultModel deleteBatchByIds(@RequestBody List<Long> ids) {
        if (CollectionUtil.isEmpty(ids)) {
            throw new BusinessException("参数ID集合不能为空");
        }
        return ResultUtil.ok(sysParamService.delBatchByIds(new SysParamModel(), ids));
    }
}

