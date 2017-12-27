package com.jww.ump.server.controller;

import com.jww.common.core.Constants;
import com.jww.common.core.exception.BusinessException;
import com.jww.common.core.model.PageModel;
import com.jww.common.core.util.SecurityUtil;
import com.jww.common.web.BaseController;
import com.jww.common.web.model.ResultModel;
import com.jww.common.web.util.ResultUtil;
import com.jww.ump.model.UmpRoleModel;
import com.jww.ump.model.UmpUserModel;
import com.jww.ump.model.UmpUserRoleModel;
import com.jww.ump.rpc.api.UmpUserService;
import com.jww.ump.server.annotation.LogData;
import com.xiaoleilu.hutool.lang.Assert;
import com.xiaoleilu.hutool.util.ObjectUtil;
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
 * 用户管理控制器
 *
 * @author wanyong
 * @date 2017/11/17 00:22
 */
@Slf4j
@Api(value = "用户管理", description = "用户管理")
@RestController
@RequestMapping("/user")
public class SysUserController extends BaseController {

    @Autowired
    private UmpUserService umpUserService;

    /**
     * 根据用户ID查询用户
     *
     * @param id
     * @return ResultModel<UmpUserModel>
     * @author wanyong
     * @date 2017-12-05 13:35
     */
    @ApiOperation(value = "查询用户", notes = "根据用户主键ID查询用户")
    @ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "Long")
    @GetMapping("/query/{id}")
    @LogData(module = "用户管理", value = "用户查询", operationType = Constants.LOG_OPERATION_TYPE_QUERY)
    public ResultModel<UmpUserModel> query(@PathVariable Long id) {
        Assert.notNull(id);
        UmpUserModel umpUserModel = umpUserService.queryOne(id);
        return ResultUtil.ok(umpUserModel);
    }

    /**
     * 分页查询用户列表
     *
     * @param pageModel
     * @return ResultModel
     * @author wanyong
     * @date 2017/12/2 14:31
     */
    @PostMapping("/listPage")
    @LogData(module = "用户管理", value = "用户分页查询", operationType = Constants.LOG_OPERATION_TYPE_QUERY)
    public ResultModel queryListPage(@RequestBody PageModel pageModel) {
        pageModel = (PageModel<UmpUserModel>) umpUserService.queryListPage(pageModel);
        return ResultUtil.ok(pageModel);
    }

    /**
     * 新增用户
     *
     * @param umpUserModel
     * @return ResultModel
     * @author wanyong
     * @date 2017-12-03 10:18
     */
    @PostMapping("/add")
    @LogData(module = "用户管理", value = "用户新增", operationType = Constants.LOG_OPERATION_TYPE_INSERT)
    public ResultModel add(@Valid @RequestBody UmpUserModel umpUserModel) {
        UmpUserModel existUmpUserModel = umpUserService.queryByAccount(umpUserModel.getAccount());
        if (ObjectUtil.isNotNull(existUmpUserModel)) {
            throw new BusinessException("已存在相同账号的用户");
        }
        // 设置初始密码: 123456
        umpUserModel.setPassword(SecurityUtil.encryptPassword("123456"));
        umpUserModel.setCreateBy(getCurrUser());
        umpUserService.add(umpUserModel);
        return ResultUtil.ok();
    }


    @PostMapping("/delBatchByIds")
    @LogData(module = "用户管理", value = "用户批量删除", operationType = Constants.LOG_OPERATION_TYPE_DELETE)
    public ResultModel delBatchByIds(@RequestBody List<Long> ids) {
        if (ids.size() == 0) {
            throw new BusinessException("用户ID集合不能为空");
        }
        return ResultUtil.ok(umpUserService.delBatchByIds(ids));
    }

    @PostMapping("/modify")
    @LogData(module = "用户管理", value = "用户修改", operationType = Constants.LOG_OPERATION_TYPE_MODIFY)
    public ResultModel modify(@RequestBody UmpUserModel umpUserModel) {
        umpUserModel.setCreateBy(this.getCurrUser());
        umpUserModel.setUpdateTime(new Date());
        return ResultUtil.ok(umpUserService.modifyUser(umpUserModel));
    }

    @GetMapping("/queryRoles/{deptId}")
    public ResultModel queryRoles(@PathVariable Long deptId) {
        Assert.notNull(deptId);
        List<UmpRoleModel> list = umpUserService.queryRoles(deptId);
        return ResultUtil.ok(list);
    }

    /**
     * 根据用户id查询用户角色关系
     *
     * @param userId
     * @return com.jww.common.web.model.ResultModel
     * @author RickyWang
     * @date 17/12/25 21:26:57
     */
    @GetMapping("/queryUserRoles/{userId}")
    @LogData(module = "用户管理", value = "用户角色关系查询", operationType = Constants.LOG_OPERATION_TYPE_QUERY)
    public ResultModel queryUserRoles(@PathVariable Long userId) {
        Assert.notNull(userId);
        List<UmpUserRoleModel> list = umpUserService.queryUserRoles(userId);
        return ResultUtil.ok(list);
    }

}
