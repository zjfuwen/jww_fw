package com.jww.ump.server.controller;

import com.jww.common.core.Constants;
import com.jww.common.core.exception.BusinessException;
import com.jww.common.core.model.PageModel;
import com.jww.common.web.BaseController;
import com.jww.common.web.model.ResultModel;
import com.jww.common.web.util.ResultUtil;
import com.jww.ump.model.UmpParamModel;
import com.jww.ump.rpc.api.UmpParamService;
import com.jww.ump.server.annotation.LogData;
import com.xiaoleilu.hutool.lang.Assert;
import com.xiaoleilu.hutool.util.ArrayUtil;
import com.xiaoleilu.hutool.util.CollectionUtil;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * <p>
 * 参数管理 前端控制器
 * </p>
 *
 * @author shadj
 * @since 2017-12-24
 */
@RestController
@RequestMapping("/param")
public class SysParamController extends BaseController {

    @Autowired
    private UmpParamService umpParamService;

    /**
     * 根据参数ID查询参数
     *
     * @param paramId 参数主键
     * @return ResultModel<UmpParamModel>
     * @author shadj
     * @date 2017-12-05 13:35
     */
    @ApiOperation(value = "查询参数", notes = "根据参数主键ID查询参数")
    @ApiImplicitParam(name = "id", value = "参数主键ID", required = true, dataType = "Long")
    @PostMapping("/query")
    @LogData(module = "参数管理", value = "参数查询", operationType = Constants.LOG_OPERATION_TYPE_QUERY)
    public ResultModel query(@RequestBody Long paramId) {
        Assert.notNull(paramId);
        UmpParamModel umpParamModel = umpParamService.queryById(paramId);
        return ResultUtil.ok(umpParamModel);
    }

    @PostMapping("/listPage")
    @LogData(module = "参数管理", value = "参数分页查询", operationType = Constants.LOG_OPERATION_TYPE_QUERY)
    public ResultModel queryListPage(@RequestBody PageModel pageModel) {
        pageModel = (PageModel) umpParamService.queryListPage(pageModel);
        return ResultUtil.ok(pageModel);
    }

    @PostMapping("/add")
    @LogData(module = "参数管理", value = "参数新增", operationType = Constants.LOG_OPERATION_TYPE_INSERT)
    public ResultModel add(@Valid @RequestBody UmpParamModel umpParamModel) {
        umpParamModel.setCreateBy(getCurrUser());
        umpParamModel.setUpdateBy(getCurrUser());
        return ResultUtil.ok(umpParamService.add(umpParamModel));
    }

    @PostMapping("/modify")
    @LogData(module = "参数管理", value = "参数修改", operationType = Constants.LOG_OPERATION_TYPE_MODIFY)
    public ResultModel modify(@RequestBody UmpParamModel umpParamModel) {
        umpParamModel.setUpdateBy(getCurrUser());
        umpParamService.modifyById(umpParamModel);
        return ResultUtil.ok();
    }

    /**
     * 根据参数ID集合批量删除
     *
     * @param ids 主键集合
     * @return ResultModel
     * @author shadj
     * @date 2017-12-24 18:30
     */
    @DeleteMapping("/deleteBatchByIds")
    @LogData(module = "参数管理", value = "参数批量删除", operationType = Constants.LOG_OPERATION_TYPE_DELETE)
    public ResultModel deleteBatchByIds(@RequestBody List<Long> ids) {
        if (CollectionUtil.isEmpty(ids)) {
            throw new BusinessException("参数ID集合不能为空");
        }
        return ResultUtil.ok(umpParamService.delBatchByIds(new UmpParamModel(), ids));
    }
}

