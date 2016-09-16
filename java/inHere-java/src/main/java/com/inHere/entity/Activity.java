package com.inHere.entity;

import java.io.IOException;
import java.util.Date;

public class Activity {

	private Integer id;
	private String title;
	private Integer extType;
	private String extData;
	private Date startTime;
	private Date endTime;
	private String coverImg;
	private Integer urlType;
	private String userId;
	private Date createTime;
	private Date updateTime;
	private String praise;
	private String urlContent;

	private User user; // 发布者

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title == null ? null : title.trim();
	}

	public Integer getExtType() {
		return extType;
	}

	public void setExtType(Integer extType) {
		this.extType = extType;
	}

	public String getExtData() throws IOException {
		return extData != null ? new String(extData.getBytes("ISO-8859-1"), "UTF-8") : "{}";
	}

	public void setExtData(String extData) {
		this.extData = extData == null ? null : extData.trim();
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public String getCoverImg() {
		return coverImg;
	}

	public void setCoverImg(String coverImg) {
		this.coverImg = coverImg == null ? null : coverImg.trim();
	}

	public Integer getUrlType() {
		return urlType;
	}

	public void setUrlType(Integer urlType) {
		this.urlType = urlType;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId == null ? null : userId.trim();
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getPraise() throws IOException {
		return praise != null ? new String(praise.getBytes("ISO-8859-1"), "UTF-8") : "{}";
	}

	public void setPraise(String praise) {
		this.praise = praise == null ? null : praise.trim();
	}

	public String getUrlContent() {
		return urlContent;
	}

	public void setUrlContent(String urlContent) {
		this.urlContent = urlContent == null ? null : urlContent.trim();
	}

	@Override
	public String toString() {
		return "Activity [id=" + id + ", title=" + title + ", extType=" + extType + ", extData=" + extData
				+ ", startTime=" + startTime + ", endTime=" + endTime + ", coverImg=" + coverImg + ", urlType="
				+ urlType + ", userId=" + userId + ", createTime=" + createTime + ", updateTime=" + updateTime
				+ ", praise=" + praise + ", urlContent=" + urlContent + ", user=" + user + "]";
	}

}