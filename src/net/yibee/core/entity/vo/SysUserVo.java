package net.yibee.core.entity.vo;

import net.yibee.core.entity.bo.SysUserBo;

public class SysUserVo extends SysUserBo {

	private String authCode = "";
	
	private String randomString = "";
	
	private String type = "";

	public String getAuthCode() {
		return authCode;
	}

	public void setAuthCode(String authCode) {
		this.authCode = authCode;
	}

	public String getRandomString() {
		return randomString;
	}

	public void setRandomString(String randomString) {
		this.randomString = randomString;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
}
