package com.inHere.entity;

public class TbTalkCircle {
	private Integer talkId;

	private Boolean talkPlace;

	private Integer labelId;

	private String labelData;

	private String talkContent;

	private String talkPic;

	private String talkTime;

	private String userIdTalk;

	private String praiseNum;

	private String rowNum;

	public String getPraiseNum() {
		return praiseNum;
	}

	public void setPraiseNum(String praiseNum) {
		this.praiseNum = praiseNum == null ? null : praiseNum.trim();
	}

	public String getRowNum() {
		return rowNum;
	}

	public void setRowNum(String rowNum) {
		this.rowNum = rowNum == null ? null : rowNum.trim();
	}

	public Integer getTalkId() {
		return talkId;
	}

	public void setTalkId(Integer talkId) {
		this.talkId = talkId;
	}

	public Boolean getTalkPlace() {
		return talkPlace;
	}

	public void setTalkPlace(Boolean talkPlace) {
		this.talkPlace = talkPlace;
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

	public String getTalkContent() {
		return talkContent;
	}

	public void setTalkContent(String talkContent) {
		this.talkContent = talkContent == null ? null : talkContent.trim();
	}

	public String getTalkPic() {
		return talkPic;
	}

	public void setTalkPic(String talkPic) {
		this.talkPic = talkPic == null ? null : talkPic.trim();
	}

	public String getTalkTime() {
		return talkTime;
	}

	public void setTalkTime(String talkTime) {
		this.talkTime = talkTime == null ? null : talkTime.trim();
	}

	public String getUserIdTalk() {
		return userIdTalk;
	}

	public void setUserIdTalk(String userIdTalk) {
		this.userIdTalk = userIdTalk == null ? null : userIdTalk.trim();
	}
}