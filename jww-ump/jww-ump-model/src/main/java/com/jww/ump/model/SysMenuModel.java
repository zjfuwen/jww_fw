package com.jww.ump.model;

import java.util.List;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
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
public class SysMenuModel extends BaseModel {

    private static final long serialVersionUID = 1L;

    /**
     * 菜单名称
     */
    @TableField("menu_name")
    private String menuName;
    /**
     * 菜单类型(0:目录;1:菜单;2:按钮;)
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
     * start 页面所需字段（数据库没有）
     */
    @TableField(exist = false)
    private String parentName;
    @TableField(exist = false)
    private Integer leaf = 1;
    @TableField(exist = false)
    private String typeName;
    @TableField(exist = false)
    private String permissionText;
    @TableField(exist = false)
    private List<SysMenuModel> menuBeans;
    /**
     * end 页面所需字段（数据库没有）
     */
}
