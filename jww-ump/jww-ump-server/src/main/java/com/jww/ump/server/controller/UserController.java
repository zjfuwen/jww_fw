package com.jww.ump.server.controller;

import com.jww.common.annotation.SysLogAnnotation;
import com.jww.common.model.ResultModel;
import com.jww.common.util.ResultUtil;
import com.jww.ump.model.UmpUserModel;
import com.jww.ump.rpc.api.UmpUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wanyong
 * @description: 用户管理控制器
 * @date 2017/11/17 00:22
 */
@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UmpUserService umpUserService;

    @SysLogAnnotation("queryUser")
    @GetMapping("/queryUser/{id}")
    public ResultModel<UmpUserModel> queryUser(@PathVariable String id) {
        log.info("UserController id: {}", id);
        UmpUserModel umpUserModel = umpUserService.findById(id);
        return ResultUtil.ok(umpUserModel);
    }
}
