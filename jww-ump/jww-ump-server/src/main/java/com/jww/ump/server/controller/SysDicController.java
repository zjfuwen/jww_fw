package com.jww.ump.server.controller;

import com.jww.common.core.Constants;
import com.jww.common.core.exception.BusinessException;
import com.jww.common.core.model.PageModel;
import com.jww.common.web.BaseController;
import com.jww.common.web.model.ResultModel;
import com.jww.common.web.util.ResultUtil;
import com.jww.ump.model.SysDicModel;
import com.jww.ump.rpc.api.SysDicService;
import com.jww.ump.server.annotation.SysLogOpt;
import com.xiaoleilu.hutool.lang.Assert;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

/**
 * <p>
 * 字典管理 前端控制器
 * </p>
 *
 * @author wanyong
 * @since 2017-11-17
 */
@RestController
@RequestMapping("/dic")
public class SysDicController extends BaseController {

    @Autowired
    private SysDicService umpDicService;

    /**
     * 根据字典ID查询字典
     *
     * @param dicId 字典主键
     * @return ResultModel<SysDicModel>
     * @author wanyong
     * @date 2017-12-05 13:35
     */
    @ApiOperation(value = "查询字典", notes = "根据字典主键ID查询字典")
    @ApiImplicitParam(name = "id", value = "角色ID", required = true, dataType = "Long")
    @PostMapping("/query")
    public ResultModel query(@RequestBody Long dicId) {
        Assert.notNull(dicId);
        SysDicModel sysDicModel = umpDicService.queryById(dicId);
        return ResultUtil.ok(sysDicModel);
    }

    @PostMapping("/listPage")
    public ResultModel queryListPage(@RequestBody PageModel pageModel) {
        pageModel = (PageModel) umpDicService.queryListPage(pageModel);
        return ResultUtil.ok(pageModel);
    }

    @PostMapping("/add")
    @SysLogOpt(module = "字典管理", value = "字典新增", operationType = Constants.LogOptEnum.ADD)
    public ResultModel add(@Valid @RequestBody SysDicModel sysDicModel) {
        sysDicModel.setCreateBy(getCurrUser());
        sysDicModel.setUpdateBy(getCurrUser());
        return ResultUtil.ok(umpDicService.add(sysDicModel));
    }

    @PostMapping("/modify")
    @SysLogOpt(module = "字典管理", value = "字典修改", operationType = Constants.LogOptEnum.MODIFY)
    public ResultModel modify(@Valid @RequestBody SysDicModel sysDicModel) {
        sysDicModel.setUpdateBy(getCurrUser());
        umpDicService.modifyById(sysDicModel);
        return ResultUtil.ok();
    }

    /**
     * 根据字典ID集合批量删除
     *
     * @param ids 主键集合
     * @return ResultModel
     * @author wanyong
     * @date 2017-12-23 02:46
     */
    @PostMapping("/delBatchByIds")
    @SysLogOpt(module = "字典管理", value = "字典批量删除", operationType = Constants.LogOptEnum.DELETE)
    public ResultModel delBatchByIds(@RequestBody List<Long> ids) {
        if (ids.size() == 0) {
            throw new BusinessException("字典ID集合不能为空");
        }
        return ResultUtil.ok(umpDicService.delBatchByIds(new SysDicModel(), ids));
    }
}

