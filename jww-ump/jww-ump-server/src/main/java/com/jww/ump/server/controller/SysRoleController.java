package com.jww.ump.server.controller;

import com.jww.common.core.model.PageModel;
import com.jww.common.web.BaseController;
import com.jww.common.web.model.ResultModel;
import com.jww.common.web.util.ResultUtil;
import com.jww.ump.model.UmpRoleModel;
import com.jww.ump.rpc.api.UmpRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

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
}

