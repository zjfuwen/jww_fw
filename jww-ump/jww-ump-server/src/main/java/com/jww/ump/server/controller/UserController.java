package com.jww.ump.server.controller;

import com.fasterxml.jackson.databind.ser.Serializers;
import com.jww.common.core.model.PageModel;
import com.jww.common.web.BaseController;
import com.jww.common.web.ResultModel;
import com.jww.common.web.util.ResultUtil;
import com.jww.ump.model.UmpUserModel;
import com.jww.ump.rpc.api.UmpUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
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
public class UserController extends BaseController {

    @Autowired
    private UmpUserService umpUserService;

    @GetMapping("/queryUser/{id}")
    public ResultModel<UmpUserModel> queryUser(@PathVariable String id, HttpServletRequest request) {
        log.info("UserController id: {}", id);
        UmpUserModel umpUserModel = umpUserService.findById(id);

        request.getSession().setAttribute("request Url", request.getRequestURL());
        log.info("setAttribute:" + request.getRequestURL());
        return ResultUtil.ok(umpUserModel);
    }


    @GetMapping("/updateUser/{id}/{remark}")
    public ResultModel<UmpUserModel> updateUser(@PathVariable String id, @PathVariable String remark, HttpServletRequest request) {
        log.info("updateUser id: {},remark: {}", id, remark);

        UmpUserModel umpUserModel = new UmpUserModel();
        umpUserModel.setId(Long.valueOf(id));
        umpUserModel.setRemark(remark);
        boolean res = umpUserService.updateById(umpUserModel);

        log.info("updateUser id: {},remark: {}, result: {}", id, remark, res);
        return ResultUtil.ok(res);
    }

    @RequestMapping(value = "/sessions", method = RequestMethod.GET)
    public Object sessions(HttpServletRequest request) {
        Map<String, Object> map = new HashMap<String, Object>(2);
        map.put("sessionId", request.getSession().getId());
        map.put("message", request.getSession().getAttribute("request Url"));
        return map;
    }

    @GetMapping("/query/{current}/{size}")
    public ResultModel query(@PathVariable int current, @PathVariable int size) {
        // PageModel<UmpUserModel> pageModel = new PageModel<UmpUserModel>(current, size);
        PageModel<UmpUserModel> pageModel = new PageModel<UmpUserModel>();
        pageModel.setCurrent(current);
        pageModel.setSize(size);
        pageModel = (PageModel<UmpUserModel>) umpUserService.selectPage(pageModel);
        return ResultUtil.ok(pageModel);
    }

    /**
     * TODO
     *
     * @param
     * @return
     * @author wanyong
     * @date 2017/11/24 22:04
     */
    @PostMapping("/query")
    public ResultModel query(UmpUserModel umpUserModel) {
        log.info(umpUserModel.toString());
        Map<String, Object> map = new HashMap<String, Object>(2);
        map.put("user_name", "123");
        map.put("password_", "1234");
        List<UmpUserModel> umpUserModelList = umpUserService.selectByMap(map);
        return ResultUtil.ok(umpUserModelList);
    }

    /**
     * 分页查询用户列表
     *
     * @param pageModel
     * @return ResultModel
     * @author wanyong
     * @date 2017/12/4 14:31
     */
    @PostMapping("/listPage")
    public ResultModel queryListPage(@RequestBody PageModel<UmpUserModel> pageModel) {
        pageModel = (PageModel<UmpUserModel>) umpUserService.findListPage(pageModel);
        return ResultUtil.ok(pageModel);
    }

}
