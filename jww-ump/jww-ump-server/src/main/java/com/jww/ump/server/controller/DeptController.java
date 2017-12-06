package com.jww.ump.server.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.jww.common.core.exception.BusinessException;
import com.jww.common.core.model.PageModel;
import com.jww.common.web.BaseController;
import com.jww.common.web.ResultModel;
import com.jww.common.web.util.ResultUtil;
import com.jww.ump.model.UmpDept;
import com.jww.ump.rpc.api.UmpDeptService;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;

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
            EntityWrapper entityWrapper = new EntityWrapper<UmpDept>();
            entityWrapper.like("dept_name",deptName);
            pageModel = (PageModel<UmpDept>) umpDeptService.selectPage(pageModel, entityWrapper);
        }
        return ResultUtil.ok(pageModel);
    }

    @PostMapping("/queryList")
    public ResultModel queryListPage(@RequestBody PageModel<UmpDept> pageModel) {
        pageModel = (PageModel<UmpDept>) umpDeptService.queryListPage(pageModel);
        return ResultUtil.ok(pageModel);
    }

    @PostMapping("/add")
    public ResultModel add(@Valid @RequestBody UmpDept umpDept){
        log.info("DeptController->add: umpDept={}",umpDept);

        if(umpDept!=null){
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
    public ResultModel del(@RequestBody UmpDept umpDept){
        log.info("DeptController->del: umpDept={}",umpDept.getId());
        EntityWrapper<UmpDept> entityWrapper = new EntityWrapper<UmpDept>();
        entityWrapper.setEntity(umpDept);
        umpDeptService.delete(entityWrapper);
        return ResultUtil.ok();
    }

    @PostMapping("/mod")
    public ResultModel mod(@RequestBody UmpDept umpDept){
        log.info("DeptController->mod: umpDept={}",umpDept.getId());
        umpDept.setUpdateBy(this.getCurrUser());
        umpDept.setUpdateTime(new Date());
        umpDeptService.updateById(umpDept);
        return ResultUtil.ok();
    }

    @PostMapping("/delBatchByIds")
    public ResultModel delBatchByIds(@RequestBody List<Long> ids) {
        if (ids.size() == 0) {
            throw new BusinessException("部门ID集合不能为空");
        }
        return ResultUtil.ok(umpDeptService.delBatchByIds(ids));
    }

}
