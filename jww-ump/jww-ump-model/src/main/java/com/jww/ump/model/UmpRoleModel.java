package com.jww.ump.model;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import com.jww.common.core.base.BaseModel;
import lombok.Data;

import java.util.Date;

/**
 * <p>
 * 角色信息表
 * </p>
 *
 * @author wanyong
 * @since 2017-11-29
 */
@Data
@TableName("sys_role")
public class UmpRoleModel extends BaseModel {

    private static final long serialVersionUID = 1L;

	/**
	 * 角色名称
	 */
	@TableField("role_name")
	private String roleName;
	/**
	 * 所属部门编号
	 */
	@TableField("dept_id")
	private Long deptId;
	/**
	 * 角色类型(1:业务角色;2:管理角色 ;3:系统内置角色)
	 */
	@TableField("role_type")
	private Integer roleType;
}
