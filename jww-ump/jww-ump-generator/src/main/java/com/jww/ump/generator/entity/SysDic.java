package com.jww.ump.generator.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.jww.common.core.base.BaseModel;

/**
 * <p>
 * 数据字典明细表
 * </p>
 *
 * @author wanyong
 * @since 2017-11-25
 */
@TableName("sys_dic")
public class SysDic extends BaseModel {

    private static final long serialVersionUID = 1L;

	@TableField("type_")
	private String type;
	@TableField("code_")
	private String code;
	@TableField("code_text")
	private String codeText;
	@TableField("parent_type")
	private String parentType;
	@TableField("parent_code")
	private String parentCode;
	@TableField("sort_no")
	private Integer sortNo;
	@TableField("editable_")
	private Integer editable;


	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getCodeText() {
		return codeText;
	}

	public void setCodeText(String codeText) {
		this.codeText = codeText;
	}

	public String getParentType() {
		return parentType;
	}

	public void setParentType(String parentType) {
		this.parentType = parentType;
	}

	public String getParentCode() {
		return parentCode;
	}

	public void setParentCode(String parentCode) {
		this.parentCode = parentCode;
	}

	public Integer getSortNo() {
		return sortNo;
	}

	public void setSortNo(Integer sortNo) {
		this.sortNo = sortNo;
	}

	public Integer getEditable() {
		return editable;
	}

	public void setEditable(Integer editable) {
		this.editable = editable;
	}

	@Override
	public String toString() {
		return "SysDic{" +
			", type=" + type +
			", code=" + code +
			", codeText=" + codeText +
			", parentType=" + parentType +
			", parentCode=" + parentCode +
			", sortNo=" + sortNo +
			", editable=" + editable +
			"}";
	}
}
