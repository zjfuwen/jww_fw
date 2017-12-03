package com.jww.ump.server.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.jww.common.core.model.PageModel;
import com.jww.common.web.ResultModel;
import com.jww.common.web.util.ResultUtil;
import com.jww.ump.model.UmpDept;
import com.jww.ump.rpc.api.UmpDeptService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Title:部门管理控制器
 * @Description:
 * @Author: Ricky Wang
 * @Date: 17/12/1 11:23:17
 */
@RestController
@RequestMapping("/dept")
@Slf4j
public class DeptController {

    @Autowired
    private UmpDeptService umpDeptService;

    @GetMapping("/query/{current}/{size}/{deptName}")
    public ResultModel query(@PathVariable int current, @PathVariable int size, @PathVariable String deptName) {
        log.info("DeptController->query: current={},size={},deptName={}",current,size,deptName);
        // PageModel<UmpUserModel> pageModel = new PageModel<UmpUserModel>(current, size);
        PageModel<UmpDept> pageModel = new PageModel<UmpDept>();
        pageModel.setCurrent(current);
        pageModel.setSize(size);
        if("all".equals(deptName.trim())){
            pageModel = (PageModel<UmpDept>) umpDeptService.selectPage(pageModel);
        }else{
            UmpDept umpDept = new UmpDept();
            umpDept.setDeptName(deptName);
            EntityWrapper<UmpDept> entityWrapper = new EntityWrapper<UmpDept>(umpDept);
            pageModel = (PageModel<UmpDept>) umpDeptService.selectPage(pageModel, entityWrapper);
        }
        return ResultUtil.ok(pageModel);
    }

}
