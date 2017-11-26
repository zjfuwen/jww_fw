package com.jww.ump.generator.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.jww.common.core.model.BaseModel;

/**
 * <p>
 * 单位表
 * </p>
 *
 * @author wanyong
 * @since 2017-11-25
 */
@TableName("sys_unit")
public class SysUnit extends BaseModel {

    private static final long serialVersionUID = 1L;

    /**
     * 单位名称
     */
	@TableField("unit_name")
	private String unitName;
    /**
     * 负责人
     */
	@TableField("principal_")
	private String principal;
    /**
     * 联系电话
     */
	@TableField("phone_")
	private String phone;
    /**
     * 地址
     */
	@TableField("address_")
	private String address;
    /**
     * 排序号
     */
	@TableField("sort_")
	private Integer sort;


	public String getUnitName() {
		return unitName;
	}

	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}

	public String getPrincipal() {
		return principal;
	}

	public void setPrincipal(String principal) {
		this.principal = principal;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	@Override
	public String toString() {
		return "SysUnit{" +
			", unitName=" + unitName +
			", principal=" + principal +
			", phone=" + phone +
			", address=" + address +
			", sort=" + sort +
			"}";
	}
}
