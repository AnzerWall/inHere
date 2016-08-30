package com.inHere.entity;

public class TbNotification {
	private Integer noticeId;

	private Boolean noticeType;

	private String currentTime;

	private String userIdPush;

	private String noticeContent;

	private String noticePoint;

	private Boolean isOver;

	private String waitAcceptUsers;

	private String hasAcceptUsers;

	public String getWaitAcceptUsers() {
		return waitAcceptUsers;
	}

	public void setWaitAcceptUsers(String waitAcceptUsers) {
		this.waitAcceptUsers = waitAcceptUsers == null ? null : waitAcceptUsers.trim();
	}

	public String getHasAcceptUsers() {
		return hasAcceptUsers;
	}

	public void setHasAcceptUsers(String hasAcceptUsers) {
		this.hasAcceptUsers = hasAcceptUsers == null ? null : hasAcceptUsers.trim();
	}

	public Integer getNoticeId() {
		return noticeId;
	}

	public void setNoticeId(Integer noticeId) {
		this.noticeId = noticeId;
	}

	public Boolean getNoticeType() {
		return noticeType;
	}

	public void setNoticeType(Boolean noticeType) {
		this.noticeType = noticeType;
	}

	public String getCurrentTime() {
		return currentTime;
	}

	public void setCurrentTime(String currentTime) {
		this.currentTime = currentTime == null ? null : currentTime.trim();
	}

	public String getUserIdPush() {
		return userIdPush;
	}

	public void setUserIdPush(String userIdPush) {
		this.userIdPush = userIdPush == null ? null : userIdPush.trim();
	}

	public String getNoticeContent() {
		return noticeContent;
	}

	public void setNoticeContent(String noticeContent) {
		this.noticeContent = noticeContent == null ? null : noticeContent.trim();
	}

	public String getNoticePoint() {
		return noticePoint;
	}

	public void setNoticePoint(String noticePoint) {
		this.noticePoint = noticePoint == null ? null : noticePoint.trim();
	}

	public Boolean getIsOver() {
		return isOver;
	}

	public void setIsOver(Boolean isOver) {
		this.isOver = isOver;
	}
}