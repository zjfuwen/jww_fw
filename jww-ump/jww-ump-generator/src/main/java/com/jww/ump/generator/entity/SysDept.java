package com.jww.ump.generator.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.jww.common.core.model.BaseModel;

/**
 * <p>
 * 部门
 * </p>
 *
 * @author wanyong
 * @since 2017-11-25
 */
@TableName("sys_dept")
public class SysDept extends BaseModel {

    private static final long serialVersionUID = 1L;

    /**
     * 隶属单位
     */
	@TableField("unit_id")
	private Long unitId;
    /**
     * 部门名称
     */
	@TableField("dept_name")
	private String deptName;
    /**
     * 上级部门编号
     */
	@TableField("parent_id")
	private Long parentId;
    /**
     * 排序号
     */
	@TableField("sort_no")
	private Integer sortNo;
    /**
     * 叶子节点(0:树枝节点;1:叶子节点)
     */
	@TableField("leaf_")
	private Integer leaf;


	public Long getUnitId() {
		return unitId;
	}

	public void setUnitId(Long unitId) {
		this.unitId = unitId;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	public Integer getSortNo() {
		return sortNo;
	}

	public void setSortNo(Integer sortNo) {
		this.sortNo = sortNo;
	}

	public Integer getLeaf() {
		return leaf;
	}

	public void setLeaf(Integer leaf) {
		this.leaf = leaf;
	}

	@Override
	public String toString() {
		return "SysDept{" +
			", unitId=" + unitId +
			", deptName=" + deptName +
			", parentId=" + parentId +
			", sortNo=" + sortNo +
			", leaf=" + leaf +
			"}";
	}
}
