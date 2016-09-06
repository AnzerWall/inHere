package com.inHere.entity;

import java.io.IOException;
import java.util.Date;

public class Demand {
	private Integer id;

	private Integer extType;

	private String extData;

	private String text;

	private String photos;

	private Integer isEnd;

	private String userId;

	private Date createTime;

	private Date updateTime;

	private String praise;

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

	/**
	 * json字段乱码处理
	 * 
	 * @return
	 * @throws IOException
	 */
	public String getExtData() throws IOException {
		return new String(extData.getBytes("ISO-8859-1"), "UTF-8");
	}

	public void setExtData(String extData) {
		this.extData = extData == null ? null : extData.trim();
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text == null ? null : text.trim();
	}

	public String getPhotos() {
		return photos;
	}

	public void setPhotos(String photos) {
		this.photos = photos == null ? null : photos.trim();
	}

	public Integer getIsEnd() {
		return isEnd;
	}

	public void setIsEnd(Integer isEnd) {
		this.isEnd = isEnd;
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
		return updateTime == null ? createTime : updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getPraise() {
		return praise;
	}

	public void setPraise(String praise) {
		this.praise = praise == null ? null : praise.trim();
	}
}