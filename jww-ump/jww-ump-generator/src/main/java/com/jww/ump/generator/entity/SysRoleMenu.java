package com.jww.ump.generator.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.jww.common.core.base.BaseModel;

/**
 * <p>
 * 角色授权表
 * </p>
 *
 * @author wanyong
 * @since 2017-11-25
 */
@TableName("sys_role_menu")
public class SysRoleMenu extends BaseModel {

    private static final long serialVersionUID = 1L;

	@TableField("role_id")
	private Long roleId;
	@TableField("menu_id")
	private Long menuId;
    /**
     * 权限标识
     */
	@TableField("permission_")
	private String permission;


	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	public Long getMenuId() {
		return menuId;
	}

	public void setMenuId(Long menuId) {
		this.menuId = menuId;
	}

	public String getPermission() {
		return permission;
	}

	public void setPermission(String permission) {
		this.permission = permission;
	}

	@Override
	public String toString() {
		return "SysRoleMenu{" +
			", roleId=" + roleId +
			", menuId=" + menuId +
			", permission=" + permission +
			"}";
	}
}
