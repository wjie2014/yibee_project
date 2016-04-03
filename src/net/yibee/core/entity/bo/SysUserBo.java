package net.yibee.core.entity.bo;

import java.util.Date;

public class SysUserBo {
	
	private int id;
	/**
	 * 用户名
	 */
	private String userName = "";
	
	private String userPass = "";
	
	private String nickName = "";
	
	private String email = "";
	/**
	 * 上次登录时间
	 */
	private Date loginTime;
	/**
	 * 用户头像
	 */
	private String userIconUrl = "";
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserPass() {
		return userPass;
	}
	public void setUserPass(String userPass) {
		this.userPass = userPass;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Date getLoginTime() {
		return loginTime;
	}
	public void setLoginTime(Date loginTime) {
		this.loginTime = loginTime;
	}
	public String getUserIconUrl() {
		return userIconUrl;
	}
	public void setUserIconUrl(String userIconUrl) {
		this.userIconUrl = userIconUrl;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	
	@Override
	public String toString() {
		return "SysUserBo [id=" + id + ", userName=" + userName + ", userPass="
				+ userPass + ", nickName=" + nickName + ", email=" + email
				+ ", loginTime=" + loginTime + ", userIconUrl=" + userIconUrl
				+ "]";
	}

}
