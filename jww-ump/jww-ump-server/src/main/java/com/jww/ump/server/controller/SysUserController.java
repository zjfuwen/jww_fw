package com.jww.ump.server.controller;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.databind.ser.Serializers;
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
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 用户管理控制器
 *
 * @author wanyong
 * @date 2017/11/17 00:22
 */
@Slf4j
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
    @PostMapping("/query")
    public ResultModel<UmpUserModel> query(@PathVariable String id) {
        Assert.notBlank(id);
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
        UmpUserModel addUmpUserModel = umpUserService.findByAccount(umpUserModel.getAccount());
        if (ObjectUtil.isNotNull(addUmpUserModel)) {
            throw new BusinessException("已存在相同账号的用户");
        }
        // 设置初始密码: 123456
        umpUserModel.setPassword(SecurityUtil.encryptPassword("123456"));
        addUmpUserModel = umpUserService.save(umpUserModel);
        return ResultUtil.ok(addUmpUserModel);
    }

}
