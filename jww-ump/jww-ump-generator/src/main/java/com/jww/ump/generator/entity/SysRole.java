package com.jww.ump.generator.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.jww.common.core.model.BaseModel;

/**
 * <p>
 * 角色信息表
 * </p>
 *
 * @author wanyong
 * @since 2017-11-25
 */
@TableName("sys_role")
public class SysRole extends BaseModel {

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


	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public Long getDeptId() {
		return deptId;
	}

	public void setDeptId(Long deptId) {
		this.deptId = deptId;
	}

	public Integer getRoleType() {
		return roleType;
	}

	public void setRoleType(Integer roleType) {
		this.roleType = roleType;
	}

	@Override
	public String toString() {
		return "SysRole{" +
			", roleName=" + roleName +
			", deptId=" + deptId +
			", roleType=" + roleType +
			"}";
	}
}
