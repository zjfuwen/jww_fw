package com.jww.ump.server.controller;


import com.jww.common.core.Constants;
import com.jww.common.core.model.PageModel;
import com.jww.common.web.BaseController;
import com.jww.common.web.model.ResultModel;
import com.jww.common.web.util.ResultUtil;
import com.jww.ump.model.UmpLogModel;
import com.jww.ump.rpc.api.UmpLogService;
import com.jww.ump.server.annotation.LogData;
import com.xiaoleilu.hutool.lang.Assert;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 系统日志 前端控制器
 * </p>
 *
 * @author wanyong
 * @since 2017-12-26
 */
@RestController
@RequestMapping("/log")
@Slf4j
@Api(value = "系统日志", description = "系统日志")
public class SysLogController extends BaseController {
    @Autowired
    private UmpLogService umpLogService;

    /**
     * 查询日志分页方法
     *
     * @param pageModel
     * @return com.jww.common.web.model.ResultModel
     * @author RickyWang
     * @date 17/12/26 12:28:13
     */
    @PostMapping("/queryListPage")
    @LogData(module = "日志管理", value = "日志查询", operationType = Constants.LOG_OPERATION_TYPE_QUERY)
    public ResultModel queryListPage(@RequestBody PageModel<UmpLogModel> pageModel) {
        pageModel = (PageModel<UmpLogModel>) umpLogService.queryListPage(pageModel);
        return ResultUtil.ok(pageModel);
    }

    /**
     * 批量删除日志方法
     *
     * @param ids
     * @return com.jww.common.web.model.ResultModel
     * @author RickyWang
     * @date 17/12/26 11:29:23
     */
    @DeleteMapping("/delBatchByIds")
    public ResultModel delBatchByIds(@RequestBody Long[] ids) {
        Assert.notNull(ids);
        return ResultUtil.ok(umpLogService.delBatchByIds(ids));
    }
}

