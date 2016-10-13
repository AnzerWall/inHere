package com.inHere.entity;

public class AskReplyUser {

	private String userId;

	private Integer askReplyId;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId == null ? null : userId.trim();
	}

	public Integer getAskReplyId() {
		return askReplyId;
	}

	public void setAskReplyId(Integer askReplyId) {
		this.askReplyId = askReplyId;
	}
}
