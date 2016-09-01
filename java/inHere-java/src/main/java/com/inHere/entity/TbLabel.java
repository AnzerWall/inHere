package com.inHere.entity;

import java.util.Date;

public class TbLabel {
	
    private Integer labelId;
    private String labelName;
    private String labelConfig;
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

	public Integer getLabelId() {
        return labelId;
    }

    public void setLabelId(Integer labelId) {
        this.labelId = labelId;
    }

    public String getLabelName() {
        return labelName;
    }

    public void setLabelName(String labelName) {
        this.labelName = labelName == null ? null : labelName.trim();
    }

    public String getLabelConfig() {
        return labelConfig;
    }

    public void setLabelConfig(String labelConfig) {
        this.labelConfig = labelConfig == null ? null : labelConfig.trim();
    }
}