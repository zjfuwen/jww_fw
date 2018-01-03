package com.jww.ump.model;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import com.jww.common.core.base.BaseModel;
import lombok.Data;

import java.util.Date;

/**
 * <p>
 * 角色授权表
 * </p>
 *
 * @author wanyong
 * @since 2017-11-29
 */
@Data
@TableName("sys_role_menu")
public class SysRoleMenuModel extends BaseModel {

    private static final long serialVersionUID = 1L;

    @TableField("role_id")
    private Long roleId;
    @TableField("menu_id")
    private Long menuId;


    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public Long getMenuId() {
        return menuId;
    }

    public void setMenuId(Long menuId) {
        this.menuId = menuId;
    }

    @Override
    public String toString() {
        return "SysRoleMenu{" +
                ", roleId=" + roleId +
                ", menuId=" + menuId +
                "}";
    }
}
