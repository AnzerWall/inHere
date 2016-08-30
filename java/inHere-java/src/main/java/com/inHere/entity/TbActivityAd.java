package com.inHere.entity;

public class TbActivityAd {
	private Integer acAdId;

	private String acAdTitle;

	private String acAdSubtitle;

	private Integer labelId;

	private String labelData;

	private String startTime;

	private String endTime;

	private String coverImg;

	private Boolean urlType;

	private Boolean hasEnded;

	private String userIdPush;

	private String praiseNum;

	private String urlContent;

	public String getPraiseNum() {
		return praiseNum;
	}

	public void setPraiseNum(String praiseNum) {
		this.praiseNum = praiseNum == null ? null : praiseNum.trim();
	}

	public String getUrlContent() {
		return urlContent;
	}

	public void setUrlContent(String urlContent) {
		this.urlContent = urlContent == null ? null : urlContent.trim();
	}

	public Integer getAcAdId() {
		return acAdId;
	}

	public void setAcAdId(Integer acAdId) {
		this.acAdId = acAdId;
	}

	public String getAcAdTitle() {
		return acAdTitle;
	}

	public void setAcAdTitle(String acAdTitle) {
		this.acAdTitle = acAdTitle == null ? null : acAdTitle.trim();
	}

	public String getAcAdSubtitle() {
		return acAdSubtitle;
	}

	public void setAcAdSubtitle(String acAdSubtitle) {
		this.acAdSubtitle = acAdSubtitle == null ? null : acAdSubtitle.trim();
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

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime == null ? null : startTime.trim();
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime == null ? null : endTime.trim();
	}

	public String getCoverImg() {
		return coverImg;
	}

	public void setCoverImg(String coverImg) {
		this.coverImg = coverImg == null ? null : coverImg.trim();
	}

	public Boolean getUrlType() {
		return urlType;
	}

	public void setUrlType(Boolean urlType) {
		this.urlType = urlType;
	}

	public Boolean getHasEnded() {
		return hasEnded;
	}

	public void setHasEnded(Boolean hasEnded) {
		this.hasEnded = hasEnded;
	}

	public String getUserIdPush() {
		return userIdPush;
	}

	public void setUserIdPush(String userIdPush) {
		this.userIdPush = userIdPush == null ? null : userIdPush.trim();
	}
}