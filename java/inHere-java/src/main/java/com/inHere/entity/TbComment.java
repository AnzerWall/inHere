package com.inHere.entity;

import java.util.Date;

public class TbComment {

	private Integer commentId; // 评论编号
	private String tabName; // 栏目名称
	private Integer recordId; // 对应记录编号
	private String commentContent; // 评论内容
	private String commentTime; // 评论时间
	private String userIdPush; // 评论者
	private String atUserId; // @谁
	private String praiseNum; // 赞数目
	private String lowNum; // 踩数目
	private Date updateTime; // 更新时间

	public String getAtUserId() {
		return atUserId;
	}

	public void setAtUserId(String atUserId) {
		this.atUserId = atUserId;
	}

	public String getPraiseNum() {
		return praiseNum;
	}

	public void setPraiseNum(String praiseNum) {
		this.praiseNum = praiseNum;
	}

	public String getLowNum() {
		return lowNum;
	}

	public void setLowNum(String lowNum) {
		this.lowNum = lowNum;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public Integer getCommentId() {
		return commentId;
	}

	public void setCommentId(Integer commentId) {
		this.commentId = commentId;
	}

	public String getTabName() {
		return tabName;
	}

	public void setTabName(String tabName) {
		this.tabName = tabName == null ? null : tabName.trim();
	}

	public Integer getRecordId() {
		return recordId;
	}

	public void setRecordId(Integer recordId) {
		this.recordId = recordId;
	}

	public String getCommentContent() {
		return commentContent;
	}

	public void setCommentContent(String commentContent) {
		this.commentContent = commentContent == null ? null : commentContent.trim();
	}

	public String getCommentTime() {
		return commentTime;
	}

	public void setCommentTime(String commentTime) {
		this.commentTime = commentTime == null ? null : commentTime.trim();
	}

	public String getUserIdPush() {
		return userIdPush;
	}

	public void setUserIdPush(String userIdPush) {
		this.userIdPush = userIdPush == null ? null : userIdPush.trim();
	}
}