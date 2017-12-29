package com.jww.ump.model;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.jww.common.core.base.BaseModel;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * <p>
 * 数据字典明细表
 * </p>
 *
 * @author wanyong
 * @since 2017-12-17
 */
@Data
@TableName("sys_dic")
public class SysDicModel extends BaseModel {

    private static final long serialVersionUID = 1L;

    /**
     * 类型
     */
    @NotBlank(message = "字典类型不能为空")
    @Size(min = 1, max = 50, message = "字典类型长度必须在1至50之间")
    @TableField("type_")
    private String type;
    /**
     * 字典值
     */
    @NotBlank(message = "字典值不能为空")
    @Size(min = 1, max = 50, message = "字典值长度必须在1至50之间")
    @TableField("code_")
    private String code;
    /**
     * 字典名称
     */
    @TableField("code_text")
    private String codeText;
    @TableField("parent_type")
    private String parentType;
    @TableField("parent_code")
    private String parentCode;
    /**
     * 排序
     */
    @TableField("sort_no")
    private Integer sortNo;
    @TableField("editable_")
    private Integer editable;
}
