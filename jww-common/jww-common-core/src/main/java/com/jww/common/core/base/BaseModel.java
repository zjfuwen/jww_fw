package com.jww.common.core.base;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 数据模型基类
 *
 * @author wanyong
 * @date 2017/11/10 12:02
 */
@Data
public abstract class BaseModel implements Serializable {
    private static final long serialVersionUID = 7258436689721815928L;

    @TableId(value = "id_", type = IdType.ID_WORKER)
    private Long id;

    /**
     * 是否启用
     */
    @TableField("enable_")
    private Integer enable;
    /**
     * 备注
     */
    @TableField("remark_")
    private String remark;
    /**
     * 创建时间
     */
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    @TableField("create_time")
    private Date createTime;
    /**
     * 创建人
     */
    @TableField("create_by")
    private Long createBy;
    /**
     * 更新时间
     */
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    @TableField("update_time")
    private Date updateTime;
    /**
     * 更新人
     */
    @TableField("update_by")
    private Long updateBy;
}
