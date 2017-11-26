package com.jww.ump.generator.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.jww.common.core.model.BaseModel;

/**
 * <p>
 * 邮件模版表
 * </p>
 *
 * @author wanyong
 * @since 2017-11-25
 */
@TableName("sys_email_template")
public class SysEmailTemplate extends BaseModel {

    private static final long serialVersionUID = 1L;

    /**
     * 邮件名称
     */
	@TableField("email_name")
	private String emailName;
    /**
     * 发送邮件帐号
     */
	@TableField("email_account")
	private String emailAccount;
    /**
     * 排序号
     */
	@TableField("sort_")
	private Integer sort;
    /**
     * 标题模版
     */
	@TableField("title_")
	private String title;
    /**
     * 内容模板
     */
	@TableField("template_")
	private String template;


	public String getEmailName() {
		return emailName;
	}

	public void setEmailName(String emailName) {
		this.emailName = emailName;
	}

	public String getEmailAccount() {
		return emailAccount;
	}

	public void setEmailAccount(String emailAccount) {
		this.emailAccount = emailAccount;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getTemplate() {
		return template;
	}

	public void setTemplate(String template) {
		this.template = template;
	}

	@Override
	public String toString() {
		return "SysEmailTemplate{" +
			", emailName=" + emailName +
			", emailAccount=" + emailAccount +
			", sort=" + sort +
			", title=" + title +
			", template=" + template +
			"}";
	}
}
