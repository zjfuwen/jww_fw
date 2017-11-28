package com.jww.ump.generator.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.jww.common.core.base.BaseModel;

/**
 * <p>
 * 邮件配置表
 * </p>
 *
 * @author wanyong
 * @since 2017-11-25
 */
@TableName("sys_email_config")
public class SysEmailConfig extends BaseModel {

    private static final long serialVersionUID = 1L;

    /**
     * SMTP服务器
     */
	@TableField("smtp_host")
	private String smtpHost;
    /**
     * SMTP服务器端口
     */
	@TableField("smtp_port")
	private String smtpPort;
    /**
     * 发送方式
     */
	@TableField("send_method")
	private String sendMethod;
    /**
     * 名称
     */
	@TableField("sender_name")
	private String senderName;
    /**
     * 发邮件邮箱账号
     */
	@TableField("sender_account")
	private String senderAccount;
    /**
     * 发邮件邮箱密码
     */
	@TableField("sender_password")
	private String senderPassword;


	public String getSmtpHost() {
		return smtpHost;
	}

	public void setSmtpHost(String smtpHost) {
		this.smtpHost = smtpHost;
	}

	public String getSmtpPort() {
		return smtpPort;
	}

	public void setSmtpPort(String smtpPort) {
		this.smtpPort = smtpPort;
	}

	public String getSendMethod() {
		return sendMethod;
	}

	public void setSendMethod(String sendMethod) {
		this.sendMethod = sendMethod;
	}

	public String getSenderName() {
		return senderName;
	}

	public void setSenderName(String senderName) {
		this.senderName = senderName;
	}

	public String getSenderAccount() {
		return senderAccount;
	}

	public void setSenderAccount(String senderAccount) {
		this.senderAccount = senderAccount;
	}

	public String getSenderPassword() {
		return senderPassword;
	}

	public void setSenderPassword(String senderPassword) {
		this.senderPassword = senderPassword;
	}

	@Override
	public String toString() {
		return "SysEmailConfig{" +
			", smtpHost=" + smtpHost +
			", smtpPort=" + smtpPort +
			", sendMethod=" + sendMethod +
			", senderName=" + senderName +
			", senderAccount=" + senderAccount +
			", senderPassword=" + senderPassword +
			"}";
	}
}
