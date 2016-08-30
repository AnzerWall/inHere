package com.inHere.entity;

public class TbQueAns {
	private Integer qAId;

	private String qContent;

	private String qTime;

	private Integer labelId;

	private String labelData;

	private String userIdQue;

	private String praiseNum;

	private String lowNum;

	public String getPraiseNum() {
		return praiseNum;
	}

	public void setPraiseNum(String praiseNum) {
		this.praiseNum = praiseNum == null ? null : praiseNum.trim();
	}

	public String getLowNum() {
		return lowNum;
	}

	public void setLowNum(String lowNum) {
		this.lowNum = lowNum == null ? null : lowNum.trim();
	}

	public Integer getqAId() {
		return qAId;
	}

	public void setqAId(Integer qAId) {
		this.qAId = qAId;
	}

	public String getqContent() {
		return qContent;
	}

	public void setqContent(String qContent) {
		this.qContent = qContent == null ? null : qContent.trim();
	}

	public String getqTime() {
		return qTime;
	}

	public void setqTime(String qTime) {
		this.qTime = qTime == null ? null : qTime.trim();
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

	public String getUserIdQue() {
		return userIdQue;
	}

	public void setUserIdQue(String userIdQue) {
		this.userIdQue = userIdQue == null ? null : userIdQue.trim();
	}
}