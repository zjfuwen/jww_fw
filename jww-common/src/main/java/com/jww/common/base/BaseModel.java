package com.jww.common.base;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.enums.IdType;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;
import java.util.Date;

/**
 * @author wanyong
 * @description: 数据模型基类
 * @date 2017/11/10 12:02
 */
@Slf4j
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public abstract class BaseModel implements Serializable {

    private static final long serialVersionUID = 7258436689721815928L;

    @TableId(value = "id_", type = IdType.ID_WORKER)
    private Long id;
    @TableField("enable_")
    private Integer enable;
    @TableField("remark_")
    private String remark;
    private Long createBy;
    private Date createTime;
    private Long updateBy;
    private Date updateTime;
    @TableField(exist = false)
    private String keyword;
}
