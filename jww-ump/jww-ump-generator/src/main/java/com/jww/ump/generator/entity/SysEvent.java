package com.jww.ump.generator.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.jww.common.core.base.BaseModel;

/**
 * <p>
 * 
 * </p>
 *
 * @author wanyong
 * @since 2017-11-25
 */
@TableName("sys_event")
public class SysEvent extends BaseModel {

    private static final long serialVersionUID = 1L;

	@TableField("title_")
	private String title;
	@TableField("request_uri")
	private String requestUri;
	@TableField("parameters_")
	private String parameters;
	@TableField("method_")
	private String method;
	@TableField("client_host")
	private String clientHost;
	@TableField("user_agent")
	private String userAgent;
	@TableField("status_")
	private Integer status;


	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getRequestUri() {
		return requestUri;
	}

	public void setRequestUri(String requestUri) {
		this.requestUri = requestUri;
	}

	public String getParameters() {
		return parameters;
	}

	public void setParameters(String parameters) {
		this.parameters = parameters;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public String getClientHost() {
		return clientHost;
	}

	public void setClientHost(String clientHost) {
		this.clientHost = clientHost;
	}

	public String getUserAgent() {
		return userAgent;
	}

	public void setUserAgent(String userAgent) {
		this.userAgent = userAgent;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "SysEvent{" +
			", title=" + title +
			", requestUri=" + requestUri +
			", parameters=" + parameters +
			", method=" + method +
			", clientHost=" + clientHost +
			", userAgent=" + userAgent +
			", status=" + status +
			"}";
	}
}
