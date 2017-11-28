package com.jww.ump.generator.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.jww.common.core.base.BaseModel;

/**
 * <p>
 * 菜单
 * </p>
 *
 * @author wanyong
 * @since 2017-11-25
 */
@TableName("sys_menu")
public class SysMenu extends BaseModel {

    private static final long serialVersionUID = 1L;

    /**
     * 菜单名称
     */
	@TableField("menu_name")
	private String menuName;
    /**
     * 菜单类型(0:CURD;1:系统菜单;2:业务菜单;)
     */
	@TableField("menu_type")
	private Integer menuType;
    /**
     * 上级菜单编号
     */
	@TableField("parent_id")
	private Long parentId;
    /**
     * 节点图标CSS类名
     */
	@TableField("iconcls_")
	private String iconcls;
    /**
     * 请求地址
     */
	@TableField("request_")
	private String request;
    /**
     * 展开状态(1:展开;0:收缩)
     */
	@TableField("expand_")
	private Integer expand;
    /**
     * 排序号
     */
	@TableField("sort_no")
	private Integer sortNo;
    /**
     * 叶子节点(0:树枝节点;1:叶子节点)
     */
	@TableField("is_show")
	private Integer isShow;
    /**
     * 权限标识
     */
	@TableField("permission_")
	private String permission;


	public String getMenuName() {
		return menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	public Integer getMenuType() {
		return menuType;
	}

	public void setMenuType(Integer menuType) {
		this.menuType = menuType;
	}

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	public String getIconcls() {
		return iconcls;
	}

	public void setIconcls(String iconcls) {
		this.iconcls = iconcls;
	}

	public String getRequest() {
		return request;
	}

	public void setRequest(String request) {
		this.request = request;
	}

	public Integer getExpand() {
		return expand;
	}

	public void setExpand(Integer expand) {
		this.expand = expand;
	}

	public Integer getSortNo() {
		return sortNo;
	}

	public void setSortNo(Integer sortNo) {
		this.sortNo = sortNo;
	}

	public Integer getIsShow() {
		return isShow;
	}

	public void setIsShow(Integer isShow) {
		this.isShow = isShow;
	}

	public String getPermission() {
		return permission;
	}

	public void setPermission(String permission) {
		this.permission = permission;
	}

	@Override
	public String toString() {
		return "SysMenu{" +
			", menuName=" + menuName +
			", menuType=" + menuType +
			", parentId=" + parentId +
			", iconcls=" + iconcls +
			", request=" + request +
			", expand=" + expand +
			", sortNo=" + sortNo +
			", isShow=" + isShow +
			", permission=" + permission +
			"}";
	}
}
