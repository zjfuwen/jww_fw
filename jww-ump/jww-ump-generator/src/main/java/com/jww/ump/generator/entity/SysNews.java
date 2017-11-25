package com.jww.ump.generator.entity;

import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.jww.common.core.model.BaseModel;

/**
 * <p>
 * 新闻表
 * </p>
 *
 * @author wanyong
 * @since 2017-11-25
 */
@TableName("sys_news")
public class SysNews extends BaseModel {

    private static final long serialVersionUID = 1L;

    /**
     * 新闻标题
     */
	@TableField("news_title")
	private String newsTitle;
    /**
     * 新闻类型
     */
	@TableField("news_type")
	private String newsType;
    /**
     * 发布时间
     */
	@TableField("send_time")
	private Date sendTime;
    /**
     * 作者
     */
	@TableField("author_")
	private String author;
    /**
     * 编辑
     */
	@TableField("editor_")
	private String editor;
    /**
     * Tag标签
     */
	@TableField("tags_")
	private String tags;
    /**
     * 关键字
     */
	@TableField("keys_")
	private String keys;
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


	public String getNewsTitle() {
		return newsTitle;
	}

	public void setNewsTitle(String newsTitle) {
		this.newsTitle = newsTitle;
	}

	public String getNewsType() {
		return newsType;
	}

	public void setNewsType(String newsType) {
		this.newsType = newsType;
	}

	public Date getSendTime() {
		return sendTime;
	}

	public void setSendTime(Date sendTime) {
		this.sendTime = sendTime;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getEditor() {
		return editor;
	}

	public void setEditor(String editor) {
		this.editor = editor;
	}

	public String getTags() {
		return tags;
	}

	public void setTags(String tags) {
		this.tags = tags;
	}

	public String getKeys() {
		return keys;
	}

	public void setKeys(String keys) {
		this.keys = keys;
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
		return "SysNews{" +
			", newsTitle=" + newsTitle +
			", newsType=" + newsType +
			", sendTime=" + sendTime +
			", author=" + author +
			", editor=" + editor +
			", tags=" + tags +
			", keys=" + keys +
			", content=" + content +
			", readerTimes=" + readerTimes +
			", status=" + status +
			"}";
	}
}
