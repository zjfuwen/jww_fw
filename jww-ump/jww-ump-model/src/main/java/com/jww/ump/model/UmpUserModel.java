package com.jww.ump.model;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import com.jww.common.core.base.BaseModel;
import lombok.Data;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Date;

/**
 * <p>
 * 用户管理
 * </p>
 *
 * @author wanyong
 * @since 2017-11-28
 */
@Data
@TableName("sys_user")
public class UmpUserModel extends BaseModel {

    private static final long serialVersionUID = 1L;

    /**
     * 登陆帐户
     */
    @NotBlank(message = "账号不能为空")
    @Size(min = 4, max = 20, message = "账号长度必须在4至20之间")
    @TableField("account_")
    private String account;
    /**
     * 密码
     */
    @TableField("password_")
    private String password;
    /**
     * 用户类型(1普通用户2管理员3系统用户)
     */
    @TableField("user_type")
    private String userType;
    /**
     * 姓名
     */
    @NotBlank(message = "姓名不能为空")
    @Size(min = 2, max = 8, message = "姓名长度必须在2至8之间")
    @TableField("user_name")
    private String userName;
    /**
     * 姓名拼音
     */
    @TableField("name_pinyin")
    private String namePinyin;
    /**
     * 性别(0:未知;1:男;2:女)
     */
    @TableField("sex_")
    private Integer sex;
    /**
     * 头像
     */
    @TableField("avatar_")
    private String avatar;
    /**
     * 电话
     */
    @TableField("phone_")
    private String phone;
    /**
     * 邮箱
     */
    @Email(message = "邮箱格式不正确")
    @TableField("email_")
    private String email;
    /**
     * 身份证号码
     */
    @Pattern(regexp = "^(|\\d{18,18}|\\d{15,15}|(\\d{17,17}[x|X]))$", message = "身份证格式不正确")
    @TableField("id_card")
    private String idCard;
    /**
     * 微信
     */
    @TableField("wei_xin")
    private String weiXin;
    /**
     * 微博
     */
    @TableField("wei_bo")
    private String weiBo;
    /**
     * QQ
     */
    @TableField("qq_")
    private String qq;
    /**
     * 出生日期
     */
    @JSONField(format = "yyyy-MM-dd")
    @TableField("birth_day")
    private Date birthDay;
    /**
     * 部门编号
     */
    @TableField("dept_id")
    private Long deptId;
    /**
     * 部门名称
     */
    @TableField(exist = false)
    private String deptName;
    /**
     * 职位
     */
    @TableField("position_")
    private String position;
    /**
     * 详细地址
     */
    @TableField("address_")
    private String address;
    /**
     * 工号
     */
    @TableField("staff_no")
    private String staffNo;

}
