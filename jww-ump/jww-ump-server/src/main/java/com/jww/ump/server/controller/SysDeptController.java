package com.jww.ump.server.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.jww.common.core.exception.BusinessException;
import com.jww.common.core.model.PageModel;
import com.jww.common.web.BaseController;
import com.jww.common.web.ResultModel;
import com.jww.common.web.util.ResultUtil;
import com.jww.ump.model.UmpDeptModel;
import com.jww.ump.rpc.api.UmpDeptService;
import com.xiaoleilu.hutool.lang.Assert;
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
public class SysDeptController extends BaseController {

    @Autowired
    private UmpDeptService umpDeptService;

    /**
     * 根据用户ID查询
     *
     * @param id
     * @return ResultModel<UmpDeptModel>
     * @author wanyong
     * @date 2017-12-05 13:35
     */
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

    @PostMapping("/queryListPage")
    public ResultModel queryListPage(@RequestBody PageModel<UmpDeptModel> pageModel) {
        pageModel = (PageModel<UmpDeptModel>) umpDeptService.queryListPage(pageModel);
        return ResultUtil.ok(pageModel);
    }

    @PostMapping("/add")
    public ResultModel add(@Valid @RequestBody UmpDeptModel UmpDeptModel) {
        log.info("DeptController->add: UmpDeptModel={}", UmpDeptModel);

        if (UmpDeptModel != null) {
            UmpDeptModel.setUnitId(Long.valueOf(1));
            Date now = new Date();
            UmpDeptModel.setCreateTime(now);
            UmpDeptModel.setCreateBy(this.getCurrUser());
            UmpDeptModel.setUpdateBy(this.getCurrUser());
            UmpDeptModel.setUpdateTime(now);
        }
        umpDeptService.insert(UmpDeptModel);
        return ResultUtil.ok();
    }

    @PostMapping("/del")
    public ResultModel del(@RequestBody UmpDeptModel UmpDeptModel) {
        log.info("DeptController->del: UmpDeptModel={}", UmpDeptModel.getId());
        EntityWrapper<UmpDeptModel> entityWrapper = new EntityWrapper<UmpDeptModel>();
        entityWrapper.setEntity(UmpDeptModel);
        umpDeptService.delete(entityWrapper);
        return ResultUtil.ok();
    }

    @PostMapping("/modify")
    public ResultModel modify(@RequestBody UmpDeptModel UmpDeptModel) {
        log.info("DeptController->mod: UmpDeptModel={}", UmpDeptModel.getId());
        UmpDeptModel.setUpdateBy(this.getCurrUser());
        UmpDeptModel.setUpdateTime(new Date());
        umpDeptService.updateById(UmpDeptModel);
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
