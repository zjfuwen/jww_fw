package com.jww.ump.generator.entity;

import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.jww.common.core.model.BaseModel;

/**
 * <p>
 * 
 * </p>
 *
 * @author wanyong
 * @since 2017-11-25
 */
@TableName("task_fire_log")
public class TaskFireLog extends BaseModel {

    private static final long serialVersionUID = 1L;

	@TableField("group_name")
	private String groupName;
	@TableField("task_name")
	private String taskName;
	@TableField("start_time")
	private Date startTime;
	@TableField("end_time")
	private Date endTime;
	@TableField("status_")
	private String status;
    /**
     * 服务器名
     */
	@TableField("server_host")
	private String serverHost;
    /**
     * 服务器网卡序列号
     */
	@TableField("server_duid")
	private String serverDuid;
	@TableField("fire_info")
	private String fireInfo;


	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public String getTaskName() {
		return taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getServerHost() {
		return serverHost;
	}

	public void setServerHost(String serverHost) {
		this.serverHost = serverHost;
	}

	public String getServerDuid() {
		return serverDuid;
	}

	public void setServerDuid(String serverDuid) {
		this.serverDuid = serverDuid;
	}

	public String getFireInfo() {
		return fireInfo;
	}

	public void setFireInfo(String fireInfo) {
		this.fireInfo = fireInfo;
	}

	@Override
	public String toString() {
		return "TaskFireLog{" +
			", groupName=" + groupName +
			", taskName=" + taskName +
			", startTime=" + startTime +
			", endTime=" + endTime +
			", status=" + status +
			", serverHost=" + serverHost +
			", serverDuid=" + serverDuid +
			", fireInfo=" + fireInfo +
			"}";
	}
}
