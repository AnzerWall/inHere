package com.inHere.entity;

import com.alibaba.fastjson.JSONObject;

import java.io.IOException;
import java.util.Date;
import java.util.List;

/**
 * 有求必应实体
 * 
 * @author Administrator
 *
 */
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

	private JSONObject extDataJSON;

	// 评论列表
	private List<Comment> comments;

	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

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

	public String getPhotos() throws IOException {
		photos = photos == null ? null : new String(photos.getBytes("ISO-8859-1"), "UTF-8");
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

	public String getPraise() throws IOException {
		praise = praise == null ? null : new String(praise.getBytes("ISO-8859-1"), "UTF-8");
		return praise;
	}

	public void setPraise(String praise) {
		this.praise = praise == null ? null : praise.trim();
	}

	public JSONObject getExtDataJSON() {
		return extDataJSON;
	}

	public void setExtDataJSON(JSONObject extDataJSON) {
		this.extDataJSON = extDataJSON;
	}

	@Override
	public String toString() {
		return "Demand [id=" + id + ", extType=" + extType + ", extData=" + extData + ", text=" + text + ", photos="
				+ photos + ", isEnd=" + isEnd + ", userId=" + userId + ", createTime=" + createTime + ", updateTime="
				+ updateTime + ", praise=" + praise + ", comments=" + comments + "]";
	}

}