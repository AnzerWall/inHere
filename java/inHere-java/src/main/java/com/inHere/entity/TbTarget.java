package com.inHere.entity;

import java.util.Date;

public class TbTarget {
	
    private Integer tarId;
    private String tarName;
    private String tarDesc;
    private Integer labelId;
    private String labelData;
    private Boolean hasFinish;
    private Integer barId;
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

	public Integer getTarId() {
        return tarId;
    }

    public void setTarId(Integer tarId) {
        this.tarId = tarId;
    }

    public String getTarName() {
        return tarName;
    }

    public void setTarName(String tarName) {
        this.tarName = tarName == null ? null : tarName.trim();
    }

    public String getTarDesc() {
        return tarDesc;
    }

    public void setTarDesc(String tarDesc) {
        this.tarDesc = tarDesc == null ? null : tarDesc.trim();
    }

    public Integer getLabelId() {
        return labelId;
    }

    public void setLabelId(Integer labelId) {
        this.labelId = labelId;
    }

    public String getLabelData() {
        return labelData;
    }

    public void setLabelData(String labelData) {
        this.labelData = labelData == null ? null : labelData.trim();
    }

    public Boolean getHasFinish() {
        return hasFinish;
    }

    public void setHasFinish(Boolean hasFinish) {
        this.hasFinish = hasFinish;
    }

    public Integer getBarId() {
        return barId;
    }

    public void setBarId(Integer barId) {
        this.barId = barId;
    }
}