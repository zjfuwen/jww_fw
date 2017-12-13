package com.jww.ump.model;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.jww.common.core.base.BaseModel;
import lombok.Data;

/**
 * <p>
 * 部门
 * </p>
 *
 * @author wanyong
 * @since 2017-11-25
 */
@Data
@TableName("sys_dept")
public class UmpDeptModel extends BaseModel {

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
	 * 上级部门名称
	 */
	@TableField(exist = false)
	private String parentName;
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
}
