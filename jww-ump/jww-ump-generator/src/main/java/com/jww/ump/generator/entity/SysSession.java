package com.jww.ump.generator.entity;

import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.jww.common.core.model.BaseModel;

/**
 * <p>
 * 会话管理
 * </p>
 *
 * @author wanyong
 * @since 2017-11-25
 */
@TableName("sys_session")
public class SysSession extends BaseModel {

    private static final long serialVersionUID = 1L;

	@TableField("session_id")
	private String sessionId;
	@TableField("account_")
	private String account;
	@TableField("ip_")
	private String ip;
	@TableField("start_time")
	private Date startTime;


	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	@Override
	public String toString() {
		return "SysSession{" +
			", sessionId=" + sessionId +
			", account=" + account +
			", ip=" + ip +
			", startTime=" + startTime +
			"}";
	}
}
