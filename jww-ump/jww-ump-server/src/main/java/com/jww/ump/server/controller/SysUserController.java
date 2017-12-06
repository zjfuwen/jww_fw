package com.jww.ump.server.controller;

import com.jww.common.core.exception.BusinessException;
import com.jww.common.core.model.PageModel;
import com.jww.common.core.util.SecurityUtil;
import com.jww.common.web.BaseController;
import com.jww.common.web.ResultModel;
import com.jww.common.web.util.ResultUtil;
import com.jww.ump.model.UmpUserModel;
import com.jww.ump.rpc.api.UmpUserService;
import com.xiaoleilu.hutool.lang.Assert;
import com.xiaoleilu.hutool.util.ObjectUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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
    @PostMapping("/query")
    public ResultModel<UmpUserModel> query(@RequestBody Long id) {
        Assert.notNull(id);
        UmpUserModel umpUserModel = umpUserService.findById(id);
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
    public ResultModel queryListPage(@RequestBody PageModel<UmpUserModel> pageModel) {
        pageModel = (PageModel<UmpUserModel>) umpUserService.findListPage(pageModel);
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
    public ResultModel add(@Valid @RequestBody UmpUserModel umpUserModel) {
        UmpUserModel existUmpUserModel = umpUserService.findByAccount(umpUserModel.getAccount());
        if (ObjectUtil.isNotNull(existUmpUserModel)) {
            throw new BusinessException("已存在相同账号的用户");
        }
        // 设置初始密码: 123456
        umpUserModel.setPassword(SecurityUtil.encryptPassword("123456"));
        umpUserModel.setCreateBy(getCurrUser());
        umpUserService.save(umpUserModel);
        return ResultUtil.ok();
    }


    @PostMapping("/delBatchByIds")
    public ResultModel delBatchByIds(@RequestBody List<Long> ids) {
        if (ids.size() == 0) {
            throw new BusinessException("用户ID集合不能为空");
        }
        return ResultUtil.ok(umpUserService.delBatchByIds(ids));
    }

    @PostMapping("/modify")
    public ResultModel modify(@RequestBody UmpUserModel umpUserModel) {
        return ResultUtil.ok(umpUserService.renewById(umpUserModel));
    }

}
