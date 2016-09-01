package com.inHere.constant;

/**
 * 系统状态代码
 * 
 * @author lwh
 *
 */
public enum Code {

	Error("0", "未知错误"), 
	NotFound("404", "资源不存在"), 
	NoHandlerFound("404", "找不着路..."), 
	ServerError("500", "服务器发生错误"), 
	NoLogin("1000", "未登录"), 
	UploadEx("1001", "上传异常"), 
	Success("200", "正常");

	// 状态码
	private String code;
	// 状态描述
	private String status;

	private Code(String code, String status) {
		this.code = code;
		this.status = status;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return this.getCode();
	}

}
