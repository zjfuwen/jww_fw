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
	private String userName;
	/**
	 * 用户操作
	 */
	@TableField("operation_")
	private String operation;
	/**
	 * 操作类型
	 */
	@TableField("operation_type")
	private Integer operationType;
	/**
	 * 请求方法
	 */
	@TableField("method_")
	private String method;
	/**
	 * 请求参数
	 */
	@TableField("params_")
	private String params;
	/**
	 * 操作结果
	 */
	@TableField("result_")
	private Integer result;
	/**
	 * 执行时长(毫秒)
	 */
	@TableField("time_")
	private Long time;
	/**
	 * IP地址
	 */
	@TableField("ip_")
	private String ip;


	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getOperation() {
		return operation;
	}

	public void setOperation(String operation) {
		this.operation = operation;
	}

	public Integer getOperationType() {
		return operationType;
	}

	public void setOperationType(Integer operationType) {
		this.operationType = operationType;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public String getParams() {
		return params;
	}

	public void setParams(String params) {
		this.params = params;
	}

	public Integer getResult() {
		return result;
	}

	public void setResult(Integer result) {
		this.result = result;
	}

	public Long getTime() {
		return time;
	}

	public void setTime(Long time) {
		this.time = time;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	@Override
	public String toString() {
		return "SysLog{" +
				", userName=" + userName +
				", operation=" + operation +
				", operationType=" + operationType +
				", method=" + method +
				", params=" + params +
				", result=" + result +
				", time=" + time +
				", ip=" + ip +
				"}";
	}
}
