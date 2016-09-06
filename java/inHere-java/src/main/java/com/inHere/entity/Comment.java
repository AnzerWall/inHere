package com.inHere.entity;

import java.util.Date;

public class Comment {
	private Integer id;

	private Integer extType;

	private Integer recordId;

	private String content;

	private String userId;

	private Date createTime;

	private Date updateTime;

	private String low;

	private String praise;

	private Integer floor;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getExtType() {
		return extType;
	}

	public void setExtType(Integer extType) {
		this.extType = extType;
	}

	public Integer getRecordId() {
		return recordId;
	}

	public void setRecordId(Integer recordId) {
		this.recordId = recordId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content == null ? null : content.trim();
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId == null ? null : userId.trim();
	}

	public Date getCretaeTime() {
		return createTime;
	}

	public void setCretaeTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getLow() {
		return low;
	}

	public void setLow(String low) {
		this.low = low == null ? null : low.trim();
	}

	public String getPraise() {
		return praise;
	}

	public void setPraise(String praise) {
		this.praise = praise == null ? null : praise.trim();
	}

	public Integer getFloor() {
		return floor;
	}

	public void setFloor(Integer floor) {
		this.floor = floor;
	}
}