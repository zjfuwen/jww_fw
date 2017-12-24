package com.jww.ump.model;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.jww.common.core.base.BaseModel;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Size;

/**
 * <p>
 * 全局参数表
 * </p>
 *
 * @author shadj
 * @since 2017-12-24
 */
@Data
@TableName("sys_param")
public class UmpParamModel extends BaseModel {

    private static final long serialVersionUID = 1L;

    /**
     * 参数键名
     */
    @NotBlank(message = "参数名称不能为空")
	@Size(min = 1,max = 50,message = "参数名称长度必须在1至50之间")
	@TableField("param_key")
	private String paramKey;
    /**
     * 参数键值
     */
    @NotBlank(message = "参数值不能为空")
	@Size(min = 0,max = 50,message = "参数值长度必须在1至50之间")
	@TableField("param_value")
	private String paramValue;
	@TableField("catalog_id")
	private Long catalogId;

}
