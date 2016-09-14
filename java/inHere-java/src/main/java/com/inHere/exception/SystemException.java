package com.inHere.exception;

public class SystemException extends RuntimeException {

	private static final long serialVersionUID = -8516641829524002012L;

	private Integer code; // 错误编码
	private String status; // 错误状态描述
	private String message; // 错误信息

	public SystemException(Integer code, String status) {
		this.code = code;
		this.status = status;
	}

	public SystemException(Integer code, String status, String message) {
		this.code = code;
		this.status = status;
		this.message = message;
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

	public String getMessage() {
		return message != null ? message : super.getMessage();
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
