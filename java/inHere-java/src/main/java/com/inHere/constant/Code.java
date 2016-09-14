package com.inHere.constant;

/**
 * 系统状态代码
 * 
 * @author lwh
 *
 */
public enum Code {

	Error(0, "服务器错误"),
	NotFound(404, "资源不存在"),
	NoLogin(1000, "用户未登录"),
	InputErr(1001, "输入异常"),
	Success(200, "正常");

	// 状态码
	private Integer code;
	// 状态描述
	private String status;

	private Code(Integer code, String status) {
		this.code = code;
		this.status = status;
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
