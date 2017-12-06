package com.jww.ump.server.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.jww.common.core.model.PageModel;
import com.jww.common.web.BaseController;
import com.jww.common.web.ResultModel;
import com.jww.common.web.util.ResultUtil;
import com.jww.ump.model.UmpDeptModel;
import com.jww.ump.rpc.api.UmpDeptService;
import com.xiaoleilu.hutool.lang.Assert;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;

/**
 * @Title:部门管理控制器
 * @Description:
 * @Author: Ricky Wang
 * @Date: 17/12/1 11:23:17
 */
@RestController
@RequestMapping("/dept")
@Slf4j
public class DeptController extends BaseController {

    @Autowired
    private UmpDeptService umpDeptService;

    @PostMapping("/query")
    public ResultModel<UmpDeptModel> query(@RequestBody Long id) {
        Assert.notNull(id);
        UmpDeptModel umpDeptModel = umpDeptService.findById(id);
        return ResultUtil.ok(umpDeptModel);
    }

    @GetMapping("/query/{current}/{size}/{deptName}")
    public ResultModel query(@PathVariable int current, @PathVariable int size, @PathVariable String deptName) {
        log.info("DeptController->query: current={},size={},deptName={}", current, size, deptName);
        // PageModel<UmpUserModel> pageModel = new PageModel<UmpUserModel>(current, size);
        PageModel<UmpDeptModel> pageModel = new PageModel<UmpDeptModel>();
        pageModel.setCurrent(current);
        pageModel.setSize(size);
        if ("all".equals(deptName.trim())) {
            pageModel = (PageModel<UmpDeptModel>) umpDeptService.selectPage(pageModel);
        } else {
            EntityWrapper entityWrapper = new EntityWrapper<UmpDeptModel>();
            entityWrapper.like("dept_name", deptName);
            pageModel = (PageModel<UmpDeptModel>) umpDeptService.selectPage(pageModel, entityWrapper);
        }
        return ResultUtil.ok(pageModel);
    }

    @PostMapping("/add")
    public ResultModel add(@Valid @RequestBody UmpDeptModel umpDept) {
        log.info("DeptController->add: umpDept={}", umpDept);

        if (umpDept != null) {
            umpDept.setUnitId(Long.valueOf(1));
            Date now = new Date();
            umpDept.setCreateTime(now);
            umpDept.setCreateBy(this.getCurrUser());
            umpDept.setUpdateBy(this.getCurrUser());
            umpDept.setUpdateTime(now);
        }
        umpDeptService.insert(umpDept);
        return ResultUtil.ok();
    }

    @PostMapping("/del")
    public ResultModel del(@RequestBody UmpDeptModel umpDept) {
        log.info("DeptController->del: umpDept={}", umpDept.getId());
        EntityWrapper<UmpDeptModel> entityWrapper = new EntityWrapper<UmpDeptModel>();
        entityWrapper.setEntity(umpDept);
        umpDeptService.delete(entityWrapper);
        return ResultUtil.ok();
    }

    @PostMapping("/mod")
    public ResultModel mod(@RequestBody UmpDeptModel umpDept) {
        log.info("DeptController->mod: umpDept={}", umpDept.getId());
        umpDeptService.updateById(umpDept);
        return ResultUtil.ok();
    }

}
