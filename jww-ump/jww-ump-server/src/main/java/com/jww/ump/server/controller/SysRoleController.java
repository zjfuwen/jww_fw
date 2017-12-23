package com.jww.ump.server.controller;

import com.jww.common.core.exception.BusinessException;
import com.jww.common.core.model.PageModel;
import com.jww.common.web.BaseController;
import com.jww.common.web.model.ResultModel;
import com.jww.common.web.util.ResultUtil;
import com.jww.ump.model.UmpDeptModel;
import com.jww.ump.model.UmpRoleModel;
import com.jww.ump.rpc.api.UmpDeptService;
import com.jww.ump.rpc.api.UmpRoleService;
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
    public ResultModel<UmpRoleModel> query(@RequestBody Long roleId) {
        Assert.notNull(roleId);
        UmpRoleModel umpRoleModel = umpRoleService.queryById(roleId);
        UmpDeptModel umpDeptModel = umpDeptService.queryById(umpRoleModel.getDeptId());
        umpRoleModel.setDeptName(umpDeptModel.getDeptName());
        return ResultUtil.ok(umpRoleModel);
    }

    @PostMapping("/listPage")
    public ResultModel queryListPage(@RequestBody PageModel pageModel) {
        pageModel = (PageModel<UmpRoleModel>) umpRoleService.queryListPage(pageModel);
        return ResultUtil.ok(pageModel);
    }

    @PostMapping("/add")
    public ResultModel add(@Valid @RequestBody UmpRoleModel umpRoleModel) {
        umpRoleModel.setCreateBy(getCurrUser());
        umpRoleModel.setUpdateBy(getCurrUser());
        return ResultUtil.ok(umpRoleService.add(umpRoleModel));
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
    public ResultModel delBatchByIds(@RequestBody List<Long> ids) {
        if (ids.size() == 0) {
            throw new BusinessException("角色ID集合不能为空");
        }
        return ResultUtil.ok(umpRoleService.delBatchByIds(new UmpRoleModel(), ids));
    }
}

