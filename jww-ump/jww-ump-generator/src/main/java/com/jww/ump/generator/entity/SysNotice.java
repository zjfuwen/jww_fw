package com.jww.ump.generator.entity;

import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.jww.common.core.model.BaseModel;

/**
 * <p>
 * 通知公告表
 * </p>
 *
 * @author wanyong
 * @since 2017-11-25
 */
@TableName("sys_notice")
public class SysNotice extends BaseModel {

    private static final long serialVersionUID = 1L;

    /**
     * 公告标题
     */
	@TableField("notice_title")
	private String noticeTitle;
    /**
     * 公告类型
     */
	@TableField("notice_type")
	private String noticeType;
    /**
     * 发布时间
     */
	@TableField("send_time")
	private Date sendTime;
    /**
     * 信息来源
     */
	@TableField("info_sources")
	private String infoSources;
    /**
     * 来源地址
     */
	@TableField("sources_url")
	private String sourcesUrl;
    /**
     * 内容
     */
	@TableField("content_")
	private String content;
    /**
     * 阅读次数
     */
	@TableField("reader_times")
	private Integer readerTimes;
    /**
     * 发布状态
     */
	@TableField("status_")
	private String status;


	public String getNoticeTitle() {
		return noticeTitle;
	}

	public void setNoticeTitle(String noticeTitle) {
		this.noticeTitle = noticeTitle;
	}

	public String getNoticeType() {
		return noticeType;
	}

	public void setNoticeType(String noticeType) {
		this.noticeType = noticeType;
	}

	public Date getSendTime() {
		return sendTime;
	}

	public void setSendTime(Date sendTime) {
		this.sendTime = sendTime;
	}

	public String getInfoSources() {
		return infoSources;
	}

	public void setInfoSources(String infoSources) {
		this.infoSources = infoSources;
	}

	public String getSourcesUrl() {
		return sourcesUrl;
	}

	public void setSourcesUrl(String sourcesUrl) {
		this.sourcesUrl = sourcesUrl;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Integer getReaderTimes() {
		return readerTimes;
	}

	public void setReaderTimes(Integer readerTimes) {
		this.readerTimes = readerTimes;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "SysNotice{" +
			", noticeTitle=" + noticeTitle +
			", noticeType=" + noticeType +
			", sendTime=" + sendTime +
			", infoSources=" + infoSources +
			", sourcesUrl=" + sourcesUrl +
			", content=" + content +
			", readerTimes=" + readerTimes +
			", status=" + status +
			"}";
	}
}
