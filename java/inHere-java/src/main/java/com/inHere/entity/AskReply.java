package com.inHere.entity;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 吐槽+问答
 * 
 * @author lwh
 *
 */
public class AskReply {

	private Integer id;
	private Integer extType;
	private String extData;
	private Integer labelId;
	private String title;
	private String content;
	private String photos;
	private Integer commentNum;
	private String userId;
	private Date createTime;
	private Date updateTime;
	private String praise;
	private String low;

	private Label label; // 关联标签

	private Comment bestReply; // 最火的评论或答案

	public Comment getBestReply() {
		return bestReply;
	}

	public void setBestReply(Comment bestReply) {
		this.bestReply = bestReply;
	}

	public Label getLabel() {
		return label;
	}

	public void setLabel(Label label) {
		this.label = label;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
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

	public String getExtData() {
		return extData;
	}

	public void setExtData(String extData) {
		this.extData = extData == null ? null : extData.trim();
	}

	public Integer getLabelId() {
		return labelId;
	}

	public void setLabelId(Integer labelId) {
		this.labelId = labelId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title == null ? null : title.trim();
	}

	public String getPhotos() {
		return photos;
	}

	public void setPhotos(String photos) {
		this.photos = photos == null ? null : photos.trim();
	}

	public Integer getCommentNum() {
		return commentNum;
	}

	public void setCommentNum(Integer commentNum) {
		this.commentNum = commentNum;
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

	public String getPraise() {
		return praise;
	}

	public void setPraise(String praise) {
		this.praise = praise == null ? null : praise.trim();
	}

	public String getLow() {
		return low;
	}

	public void setLow(String low) {
		this.low = low == null ? null : low.trim();
	}

	@Override
	public String toString() {
		return "AskReply [id=" + id + ", extType=" + extType + ", extData=" + extData + ", labelId=" + labelId
				+ ", title=" + title + ", content=" + content + ", photos=" + photos + ", commentNum=" + commentNum
				+ ", userId=" + userId + ", createTime=" + createTime + ", updateTime=" + updateTime + ", praise="
				+ praise + ", low=" + low + ", label=" + label + ", bestReply=" + bestReply + "]";
	}

	public Map<String, Object> toMap() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("", "");
		return map;
	}

}