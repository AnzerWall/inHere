package com.inHere.entity;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TbBegHelp {

	private Integer helpId;
	private Integer lableId;
	private String labelData;
	private String wordDesc;
	private String picDesc;
	private Integer hasResolved;
	private String userIdBeg;
	private String praiseNum;
	private Date createTime; // 创建时间
	private Date updateTime; // 更新时间

	public String getCreateTime() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdf.format(createTime);
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getUpdateTime() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdf.format(updateTime);
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public Integer getHelpId() {
		return helpId;
	}

	public void setHelpId(Integer helpId) {
		this.helpId = helpId;
	}

	public Integer getLableId() {
		return lableId;
	}

	public void setLableId(Integer lableId) {
		this.lableId = lableId;
	}

	public String getLabelData() {
		return labelData;
	}

	public void setLabelData(String labelData) {
		this.labelData = labelData == null ? null : labelData.trim();
	}

	public String getWordDesc() {
		return wordDesc;
	}

	public void setWordDesc(String wordDesc) {
		this.wordDesc = wordDesc == null ? null : wordDesc.trim();
	}

	public String getPicDesc() {
		return picDesc;
	}

	public void setPicDesc(String picDesc) {
		this.picDesc = picDesc == null ? null : picDesc.trim();
	}

	public Integer getHasResolved() {
		return hasResolved;
	}

	public void setHasResolved(Integer hasResolved) {
		this.hasResolved = hasResolved;
	}

	public String getUserIdBeg() {
		return userIdBeg;
	}

	public void setUserIdBeg(String userIdBeg) {
		this.userIdBeg = userIdBeg == null ? null : userIdBeg.trim();
	}

	public String getPraiseNum() {
		return praiseNum;
	}

	public void setPraiseNum(String praiseNum) {
		this.praiseNum = praiseNum == null ? null : praiseNum.trim();
	}

	@Override
	public String toString() {
		return "TbBegHelp [helpId=" + helpId + ", lableId=" + lableId + ", labelData=" + labelData + ", wordDesc="
				+ wordDesc + ", picDesc=" + picDesc + ", hasResolved=" + hasResolved + ", userIdBeg=" + userIdBeg
				+ ", praiseNum=" + praiseNum + ", createTime=" + createTime + ", updateTime=" + updateTime + "]";
	}

}