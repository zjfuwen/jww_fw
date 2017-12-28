package com.jww.ump.server.controller;

import com.jww.common.core.Constants;
import com.jww.common.core.exception.BusinessException;
import com.jww.common.core.model.PageModel;
import com.jww.common.web.BaseController;
import com.jww.common.web.model.ResultModel;
import com.jww.common.web.util.ResultUtil;
import com.jww.ump.model.UmpDeptModel;
import com.jww.ump.model.UmpRoleModel;
import com.jww.ump.rpc.api.UmpDeptService;
import com.jww.ump.rpc.api.UmpRoleService;
import com.jww.ump.server.annotation.LogData;
import com.xiaoleilu.hutool.lang.Assert;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
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
    private UmpRoleService umpRoleService;

    @Autowired
    private UmpDeptService umpDeptService;

    /**
     * 根据角色ID查询角色
     *
     * @param roleId
     * @return ResultModel<UmpRoleModel>
     * @author wanyong
     * @date 2017-12-05 13:35
     */
    @ApiOperation(value = "查询角色", notes = "根据角色主键ID查询角色")
    @ApiImplicitParam(name = "id", value = "角色ID", required = true, dataType = "Long")
    @PostMapping("/query")
    @LogData(module = "角色管理", value = "角色查询", operationType = Constants.LOG_OPERATION_TYPE_QUERY)
    public ResultModel<UmpRoleModel> query(@RequestBody Long roleId) {
        Assert.notNull(roleId);
        UmpRoleModel umpRoleModel = umpRoleService.queryById(roleId);
        UmpDeptModel umpDeptModel = umpDeptService.queryById(umpRoleModel.getDeptId());
        umpRoleModel.setDeptName(umpDeptModel.getDeptName());
        return ResultUtil.ok(umpRoleModel);
    }

    @PostMapping("/listPage")
    @LogData(module = "角色管理", value = "角色分页查询", operationType = Constants.LOG_OPERATION_TYPE_QUERY)
    public ResultModel queryListPage(@RequestBody PageModel pageModel) {
        pageModel = (PageModel<UmpRoleModel>) umpRoleService.queryListPage(pageModel);
        return ResultUtil.ok(pageModel);
    }

    @PostMapping("/add")
    @LogData(module = "角色管理", value = "角色新增", operationType = Constants.LOG_OPERATION_TYPE_INSERT)
    public ResultModel add(@Valid @RequestBody UmpRoleModel umpRoleModel) {
        umpRoleModel.setCreateBy(getCurrUser());
        umpRoleModel.setUpdateBy(getCurrUser());
        return ResultUtil.ok(umpRoleService.add(umpRoleModel));
    }

    @PostMapping("/modify")
    @LogData(module = "角色管理", value = "角色修改", operationType = Constants.LOG_OPERATION_TYPE_MODIFY)
    public ResultModel modify(@Valid @RequestBody UmpRoleModel umpRoleModel) {
        umpRoleModel.setUpdateBy(getCurrUser());
        umpRoleService.modifyById(umpRoleModel);
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
    @LogData(module = "角色管理", value = "角色批量删除", operationType = Constants.LOG_OPERATION_TYPE_DELETE)
    public ResultModel delBatchByIds(@RequestBody List<Long> ids) {
        if (ids.size() == 0) {
            throw new BusinessException("角色ID集合不能为空");
        }
        return ResultUtil.ok(umpRoleService.delBatchByIds(new UmpRoleModel(), ids));
    }
}

