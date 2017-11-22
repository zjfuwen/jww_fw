package com.jww.ump.model;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.jww.common.db.BaseModel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author wanyong
 * @description: 用户实体类
 * @date 2017/11/17 15:32
 */
@ToString
@TableName("sys_user")
public class UmpUserModel extends BaseModel {

    /**
     * 用户名
     */
    @Getter
    @Setter
    public String userName;

    /**
     * 密码
     */
    @Getter
    @Setter
    @TableField("password_")
    public String password;
}
