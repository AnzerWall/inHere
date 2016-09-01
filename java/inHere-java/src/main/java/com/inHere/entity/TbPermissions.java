package com.inHere.entity;

import java.util.Date;

public class TbPermissions {
	
    private Integer permId;
	private Date createTime; // 创建时间
	private Date updateTime; // 更新时间
	
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

	public Integer getPermId() {
        return permId;
    }

    public void setPermId(Integer permId) {
        this.permId = permId;
    }
}