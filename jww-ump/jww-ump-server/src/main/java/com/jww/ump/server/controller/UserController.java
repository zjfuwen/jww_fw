package com.jww.ump.server.controller;

import com.jww.common.log.annotation.SysLogAnnotation;
import com.jww.common.web.ResultModel;
import com.jww.common.web.util.ResultUtil;
import com.jww.ump.model.UmpUserModel;
import com.jww.ump.rpc.api.UmpUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

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
    public ResultModel<UmpUserModel> queryUser(@PathVariable String id, HttpServletRequest request) {
        log.info("UserController id: {}", id);
        UmpUserModel umpUserModel = umpUserService.findById(id);

        request.getSession().setAttribute("request Url", request.getRequestURL());
        log.info("setAttribute:" + request.getRequestURL());
        return ResultUtil.ok(umpUserModel);
    }

    @RequestMapping(value = "/sessions", method = RequestMethod.GET)
    public Object sessions (HttpServletRequest request){
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("sessionId", request.getSession().getId());
        map.put("message", request.getSession().getAttribute("request Url"));
        return map;
    }
}
