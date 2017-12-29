package com.jww.ump.rpc.api;

import com.baomidou.mybatisplus.plugins.Page;
import com.jww.common.core.base.BaseService;
import com.jww.ump.model.SysDeptModel;
import com.jww.ump.model.SysTreeModel;

import java.util.List;

/**
 * 部门管理服务接口
 *
 * @author Ricky Wang
 * @date 2017-12-27 12:02
 */
public interface SysDeptService extends BaseService<SysDeptModel> {
    public boolean addDept(SysDeptModel sysDeptModel);

    public boolean modifyDept(SysDeptModel sysDeptModel);

    Page<SysDeptModel> queryListPage(Page<SysDeptModel> page);

    public SysDeptModel queryOne(Long id);

    boolean delBatchByIds(Long[] ids);

    public List<SysTreeModel> queryTree();

    public List<SysTreeModel> queryTree(Long id);

    public boolean delDept(Long id);

    public List<SysDeptModel> querySubDept(Long id);

    public int querySubDeptCount(Long id);

    public int querySubDeptCount(Long[] ids);
}
