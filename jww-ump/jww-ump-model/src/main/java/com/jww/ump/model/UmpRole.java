package com.jww.ump.model;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import com.jww.common.core.base.BaseModel;

import java.util.Date;

/**
 * <p>
 * 角色信息表
 * </p>
 *
 * @author wanyong
 * @since 2017-11-29
 */
@TableName("sys_role")
public class UmpRole extends BaseModel {

    private static final long serialVersionUID = 1L;

    /**
     * 角色编号
     */
	@TableId(value = "id_", type = IdType.ID_WORKER)
	private Long id;
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
	@TableField("enable_")
	private Integer enable;
    /**
     * 备注
     */
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
		return "SysRole{" +
			", id=" + id +
			", roleName=" + roleName +
			", deptId=" + deptId +
			", roleType=" + roleType +
			", enable=" + enable +
			", remark=" + remark +
			", createBy=" + createBy +
			", createTime=" + createTime +
			", updateBy=" + updateBy +
			", updateTime=" + updateTime +
			"}";
	}
}
