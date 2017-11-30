package com.jww.ump.model;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import com.jww.common.core.base.BaseModel;

import java.util.Date;

/**
 * <p>
 * 角色授权表
 * </p>
 *
 * @author wanyong
 * @since 2017-11-29
 */
@TableName("sys_role_menu")
public class UmpRoleMenu extends BaseModel {

    private static final long serialVersionUID = 1L;

	@TableId(value = "id_", type = IdType.ID_WORKER)
	private Long id;
	@TableField("role_id")
	private Long roleId;
	@TableField("menu_id")
	private Long menuId;
    /**
     * 权限标识
     */
	@TableField("permission_")
	private String permission;
	@TableField("enable_")
	private Integer enable;
	@TableField("remark_")
	private String remark;
	@TableField("create_by")
	private Long createBy;
	@TableField("create_time")
	private Date createTime;
	@TableField("update_by")
	private Long updateBy;
	@TableField("update_time")
	private Date updateTime;


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

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

	public Integer getEnable() {
		return enable;
	}

	public void setEnable(Integer enable) {
		this.enable = enable;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Long getCreateBy() {
		return createBy;
	}

	public void setCreateBy(Long createBy) {
		this.createBy = createBy;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Long getUpdateBy() {
		return updateBy;
	}

	public void setUpdateBy(Long updateBy) {
		this.updateBy = updateBy;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	@Override
	public String toString() {
		return "SysRoleMenu{" +
			", id=" + id +
			", roleId=" + roleId +
			", menuId=" + menuId +
			", permission=" + permission +
			", enable=" + enable +
			", remark=" + remark +
			", createBy=" + createBy +
			", createTime=" + createTime +
			", updateBy=" + updateBy +
			", updateTime=" + updateTime +
			"}";
	}
}
