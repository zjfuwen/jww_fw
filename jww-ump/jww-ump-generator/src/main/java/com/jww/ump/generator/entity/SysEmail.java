package com.jww.ump.generator.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.jww.common.core.model.BaseModel;

/**
 * <p>
 * 邮件表
 * </p>
 *
 * @author wanyong
 * @since 2017-11-25
 */
@TableName("sys_email")
public class SysEmail extends BaseModel {

    private static final long serialVersionUID = 1L;

    /**
     * 邮件名称
     */
	@TableField("email_name")
	private String emailName;
    /**
     * 使用发送
     */
	@TableField("sender_")
	private String sender;
    /**
     * 发送标题
     */
	@TableField("email_title")
	private String emailTitle;
    /**
     * 发送内容
     */
	@TableField("email_content")
	private String emailContent;


	public String getEmailName() {
		return emailName;
	}

	public void setEmailName(String emailName) {
		this.emailName = emailName;
	}

	public String getSender() {
		return sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}

	public String getEmailTitle() {
		return emailTitle;
	}

	public void setEmailTitle(String emailTitle) {
		this.emailTitle = emailTitle;
	}

	public String getEmailContent() {
		return emailContent;
	}

	public void setEmailContent(String emailContent) {
		this.emailContent = emailContent;
	}

	@Override
	public String toString() {
		return "SysEmail{" +
			", emailName=" + emailName +
			", sender=" + sender +
			", emailTitle=" + emailTitle +
			", emailContent=" + emailContent +
			"}";
	}
}
