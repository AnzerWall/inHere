package com.inHere.entity;

import java.util.Date;

public class Comment {
	
    private Integer id;// 实体编号
    private Integer extType;//栏目类别
    private Integer recordId;//记录编号
    private String content;//评论内容
    private String userId;//用户编号
    private String name;//匿名
    private Date createTime;//创建时间
    private Date updateTime;//更新时间
    private String low;//踩用户列表
    private String praise;//赞用户列表
    private Integer floor;//楼层

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

    public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	@Override
	public String toString() {
		return "Comment [id=" + id + ", extType=" + extType + ", recordId=" + recordId + ", content=" + content
				+ ", userId=" + userId + ", name=" + name + ", createTime=" + createTime + ", updateTime=" + updateTime
				+ ", low=" + low + ", praise=" + praise + ", floor=" + floor + "]";
	}
    
}