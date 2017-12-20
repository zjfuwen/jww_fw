package com.jww.ump.server.controller;


import com.jww.common.web.BaseController;
import com.jww.common.web.model.ResultModel;
import com.jww.common.web.util.ResultUtil;
import com.jww.ump.model.UmpMenuModel;
import com.jww.ump.model.UmpTreeModel;
import com.jww.ump.rpc.api.UmpMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 菜单 前端控制器
 * </p>
 *
 * @author wanyong
 * @since 2017-11-29
 */
@RestController
@RequestMapping("/menu")
public class SysMenuController extends BaseController {

    @Autowired
    private UmpMenuService umpMenuService;

    /**
     * 查询所有菜单
     *
     * @return ResultModel<List<UmpMenuModel>>
     * @author wanyong
     * @date 2017-12-02 00:24
     */
    @PostMapping("/queryList")
    public ResultModel<List<UmpMenuModel>> queryList() {
        return ResultUtil.ok(umpMenuService.queryList());
    }

    @GetMapping("/tree/{userId}")
    public ResultModel<List<UmpTreeModel>> queryMenuTreeByUserId(@PathVariable(value = "userId") Long userId) {
        return ResultUtil.ok(umpMenuService.queryMenuTreeByUserId(userId));
    }

    @PostMapping("/functree")
    public ResultModel queryFuncMenuTree() {
        /*StringBuilder str = new StringBuilder();
        str.append("[");
        str.append("{title:\"节点1\",value:\"jd1\",checked:false,disabled:false,data:[");
        str.append("{title:\"节点1.1\",value:\"jd1.1\",checked:false,disabled:false,data:[]}");
        str.append(",{title:\"节点1.2\",value:\"jd1.2\",checked:false,disabled:false,data:[]}");
        str.append(",{title:\"节点1.3\",value:\"jd1.3\",checked:false,disabled:false,data:[]}");
        str.append(",{title:\"节点1.4\",value:\"jd1.4\",checked:false,disabled:false,data:[]}");
        str.append("]}");
        str.append(",{title:\"节点2\",value:\"jd2\",checked:false,disabled:false,data:[");
        str.append("{title:\"节点2.1\",value:\"jd2.1\",checked:false,disabled:false,data:[]}");
        str.append(",{title:\"节点2.2\",value:\"jd2.2\",checked:false,disabled:true,data:[");
        str.append("{title:\"节点2.2.1\",value:\"jd2.2.1\",checked:true,disabled:false,data:[]}");
        str.append(",{title:\"节点2.2.2\",value:\"jd2.2.2\",checked:false,disabled:false,data:[]}");
        str.append(",{title:\"节点2.2.3\",value:\"jd2.2.3\",checked:false,disabled:true,data:[]}");
        str.append(",{title:\"节点2.2.4\",value:\"jd2.2.4\",checked:true,disabled:true,data:[]}]}");
        str.append(",{title:\"节点2.3\",value:\"jd2.3\",checked:false,disabled:false,data:[]}");
        str.append(",{title:\"节点2.4\",value:\"jd2.4\",checked:false,disabled:false,data:[]}");
        str.append("]}");
        str.append(",{title:\"节点3\",value:\"jd3\",checked:false,disabled:false,data:[]}");
        str.append(",{title:\"节点4\",value:\"jd4\",checked:false,disabled:false,data:[");
        str.append("{title:\"节点4.1\",value:\"jd4.1\",checked:false,disabled:false,data:[");
        str.append("{title:\"节点4.1.1\",value:\"jd4.1.1\",checked:false,disabled:false,data:[");
        str.append("{title:\"节点4.1.1.1\",value:\"jd4.1.1.1\",checked:false,disabled:false,data:[]}");
        str.append(",{title:\"节点4.1.1.2\",value:\"jd4.1.1.2\",checked:false,disabled:false,data:[");
        str.append("{title:\"节点4.1.1.2.1\",value:\"jd4.1.1.2.1\",checked:false,disabled:false,data:[]}");
        str.append("]}");
        str.append("]}");
        str.append("]}");
        str.append(",{title:\"节点4.2\",value:\"jd4.2\",checked:false,disabled:true,data:[]}");
        str.append(",{title:\"节点4.3\",value:\"jd4.3\",checked:false,disabled:true,data:[]}");
        str.append(",{title:\"节点4.4\",value:\"jd4.4\",checked:false,disabled:false,data:[]}");
        str.append(",{title:\"节点4.5\",value:\"jd4.5\",checked:false,disabled:false,data:[]}");
        str.append(",{title:\"节点4.6\",value:\"jd4.6\",checked:false,disabled:false,data:[]}");
        str.append("]}");
        str.append("]");
        return ResultUtil.ok(str);*/
        List<UmpTreeModel> treeModelList = umpMenuService.queryFuncMenuTree();
        return ResultUtil.ok(treeModelList);
    }

}

