package com.jww.ump.generator.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.jww.common.core.base.BaseModel;

/**
 * <p>
 * 第三方用户
 * </p>
 *
 * @author wanyong
 * @since 2017-11-25
 */
@TableName("sys_user_thirdparty")
public class SysUserThirdparty extends BaseModel {

    private static final long serialVersionUID = 1L;

	@TableField("user_id")
	private Long userId;
    /**
     * 第三方类型
     */
	@TableField("provider_")
	private String provider;
    /**
     * 第三方Id
     */
	@TableField("open_id")
	private String openId;


	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getProvider() {
		return provider;
	}

	public void setProvider(String provider) {
		this.provider = provider;
	}

	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

	@Override
	public String toString() {
		return "SysUserThirdparty{" +
			", userId=" + userId +
			", provider=" + provider +
			", openId=" + openId +
			"}";
	}
}
