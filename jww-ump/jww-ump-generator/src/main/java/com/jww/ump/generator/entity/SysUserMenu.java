package com.jww.ump.generator.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.jww.common.core.model.BaseModel;

/**
 * <p>
 * 用户授权表
 * </p>
 *
 * @author wanyong
 * @since 2017-11-25
 */
@TableName("sys_user_menu")
public class SysUserMenu extends BaseModel {

    private static final long serialVersionUID = 1L;

	@TableField("user_id")
	private Long userId;
	@TableField("menu_id")
	private Long menuId;
    /**
     * 权限标识
     */
	@TableField("permission_")
	private String permission;


	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
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
		return "SysUserMenu{" +
			", userId=" + userId +
			", menuId=" + menuId +
			", permission=" + permission +
			"}";
	}
}
