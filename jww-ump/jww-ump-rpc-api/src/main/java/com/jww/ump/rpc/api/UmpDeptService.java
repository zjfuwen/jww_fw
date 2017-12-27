package com.jww.ump.rpc.api;

import com.baomidou.mybatisplus.plugins.Page;
import com.jww.common.core.base.BaseService;
import com.jww.ump.model.UmpDeptModel;
import com.jww.ump.model.UmpTreeModel;

import java.util.List;

/**
 * 部门管理服务接口
 *
 * @author Ricky Wang
 * @date 2017-12-27 12:02
 */
public interface UmpDeptService extends BaseService<UmpDeptModel> {
    public boolean addDept(UmpDeptModel umpDeptModel);

    public boolean modifyDept(UmpDeptModel umpDeptModel);

    Page<UmpDeptModel> queryListPage(Page<UmpDeptModel> page);

    public UmpDeptModel queryOne(Long id);

    boolean delBatchByIds(Long[] ids);

    public List<UmpTreeModel> queryTree();

    public List<UmpTreeModel> queryTree(Long id);

    public boolean delDept(Long id);

    public List<UmpDeptModel> querySubDept(Long id);

    public int querySubDeptCount(Long id);

    public int querySubDeptCount(Long[] ids);
}
