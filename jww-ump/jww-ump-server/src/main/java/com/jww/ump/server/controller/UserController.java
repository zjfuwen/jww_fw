package com.jww.ump.server.controller;

import com.jww.common.annotation.SysLogAnnotation;
import lombok.extern.slf4j.Slf4j;
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

    @SysLogAnnotation("queryUser")
    @GetMapping("/queryUser/{id}")
    public String queryUser(@PathVariable String id) {
        log.info("id: {}", id);
        return "id:" + id;
    }
}
