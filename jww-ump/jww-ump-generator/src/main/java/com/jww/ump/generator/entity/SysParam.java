package com.jww.ump.generator.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.jww.common.core.model.BaseModel;

/**
 * <p>
 * 全局参数表
 * </p>
 *
 * @author wanyong
 * @since 2017-11-25
 */
@TableName("sys_param")
public class SysParam extends BaseModel {

    private static final long serialVersionUID = 1L;

    /**
     * 参数键名
     */
	@TableField("param_key")
	private String paramKey;
    /**
     * 参数键值
     */
	@TableField("param_value")
	private String paramValue;
	@TableField("catalog_id")
	private Long catalogId;


	public String getParamKey() {
		return paramKey;
	}

	public void setParamKey(String paramKey) {
		this.paramKey = paramKey;
	}

	public String getParamValue() {
		return paramValue;
	}

	public void setParamValue(String paramValue) {
		this.paramValue = paramValue;
	}

	public Long getCatalogId() {
		return catalogId;
	}

	public void setCatalogId(Long catalogId) {
		this.catalogId = catalogId;
	}

	@Override
	public String toString() {
		return "SysParam{" +
			", paramKey=" + paramKey +
			", paramValue=" + paramValue +
			", catalogId=" + catalogId +
			"}";
	}
}
