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

    /**
     * 添加部门
     *
     * @param sysDeptModel
     * @return com.jww.ump.model.SysDeptModel
     * @author RickyWang
     * @date 18/1/1 15:04:47
     */
    public SysDeptModel addDept(SysDeptModel sysDeptModel);

    /**
     * 修改部门
     *
     * @param sysDeptModel
     * @return boolean
     * @author RickyWang
     * @date 18/1/1 15:05:10
     */
    public boolean modifyDept(SysDeptModel sysDeptModel);

    /**
     * 分页查询部门
     *
     * @param page
     * @return com.baomidou.mybatisplus.plugins.Page<com.jww.ump.model.SysDeptModel>
     * @author RickyWang
     * @date 18/1/1 15:05:30
     */
    Page<SysDeptModel> queryListPage(Page<SysDeptModel> page);

    /**
     * 查询单个部门
     *
     * @param id
     * @return com.jww.ump.model.SysDeptModel
     * @author RickyWang
     * @date 18/1/1 15:05:53
     */
    public SysDeptModel queryOne(Long id);

    /**
     * 批量删除部门
     *
     * @param ids
     * @return java.lang.Integer
     * @author RickyWang
     * @date 18/1/1 15:06:11
     */
    public Integer deleteBatch(Long[] ids);

    /**
     * 查询部门数
     *
     * @param
     * @return java.util.List<com.jww.ump.model.SysTreeModel>
     * @author RickyWang
     * @date 18/1/1 15:06:33
     */
    public List<SysTreeModel> queryTree();
    /**
     * 查询部门数，过滤该id下所有部门
     *
     * @param id
     * @return java.util.List<com.jww.ump.model.SysTreeModel>
     * @author RickyWang
     * @date 18/1/1 15:06:33
     */
    public List<SysTreeModel> queryTree(Long id);

    /**
     * 删除部门
     *
     * @param id
     * @return boolean
     * @author RickyWang
     * @date 18/1/1 15:07:49
     */
    public boolean delDept(Long id);

    /**
     * 按id查询子部门
     *
     * @param id
     * @return java.util.List<com.jww.ump.model.SysDeptModel>
     * @author RickyWang
     * @date 18/1/1 15:08:09
     */
    public List<SysDeptModel> querySubDept(Long id);

    /**
     * 查询子部门数量
     *
     * @param id
     * @return int
     * @author RickyWang
     * @date 18/1/1 15:08:35
     */
    public int querySubDeptCount(Long id);

    /**
     * 查询子部门数量
     *
     * @param ids
     * @return int
     * @author RickyWang
     * @date 18/1/1 15:08:53
     */
    public int querySubDeptCount(Long[] ids);
}
