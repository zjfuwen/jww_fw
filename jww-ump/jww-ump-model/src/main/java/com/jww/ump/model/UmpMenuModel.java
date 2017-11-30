package com.jww.ump.model;

import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import com.jww.common.core.base.BaseModel;
import lombok.Data;

/**
 * <p>
 * 菜单
 * </p>
 *
 * @author wanyong
 * @since 2017-11-29
 */
@Data
@TableName("sys_menu")
public class UmpMenuModel extends BaseModel {

    private static final long serialVersionUID = 1L;

    /**
     * 菜单编号
     */
    @TableId(value = "id_", type = IdType.ID_WORKER)
    private Long id;
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
    /**
     * 备注
     */
    @TableField("remark_")
    private String remark;
    @TableField("enable_")
    private Integer enable;
    @TableField("create_by")
    private Long createBy;

    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    @TableField("create_time")
    private Date createTime;
    @TableField("update_by")
    private Long updateBy;

    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    @TableField("update_time")
    private Date updateTime;
}
