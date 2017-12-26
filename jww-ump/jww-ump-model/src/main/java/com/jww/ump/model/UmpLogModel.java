package com.jww.ump.model;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import com.jww.common.core.base.BaseModel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

/**
 * 日志表
 *
 * @author RickyWang
 * @date 17/12/26 12:41:27
 */
@TableName("sys_log")
@ToString
public class UmpLogModel extends BaseModel {

    private static final long serialVersionUID = 1L;
    /**
     * 用户名
     */
	@TableField("user_name")
	@Getter@Setter
	private String userName;
    /**
     * 用户操作
     */
	@Getter@Setter
	private String operation;
    /**
     * 请求方法
     */
	@Getter@Setter
	private String method;
    /**
     * 请求参数
     */
	@Getter@Setter
	private String params;
    /**
     * 执行时长(毫秒)
     */
	@Getter@Setter
	private Long time;
    /**
     * IP地址
     */
	@Getter@Setter
	private String ip;
}
