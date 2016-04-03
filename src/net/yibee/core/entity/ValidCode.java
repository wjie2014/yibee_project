package net.yibee.core.entity;

import java.io.Serializable;
import java.util.Date;

public class ValidCode implements Serializable{

	private static final long serialVersionUID = 5526467797225513203L;

	//验证码标示符
	private String codeKey;
	
	//验证码
	private String code;
	
	//创建时间，根据这个标识，可以删除一些过期的数据
	private Date createTime = new Date();
	
	public ValidCode(String codeKey, String code) {
		super();
		this.codeKey = codeKey;
		this.code = code;
	}

	public ValidCode() {
		super();
	}

	public String getCodeKey() {
		return codeKey;
	}

	public void setCodeKey(String codeKey) {
		this.codeKey = codeKey;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
}
