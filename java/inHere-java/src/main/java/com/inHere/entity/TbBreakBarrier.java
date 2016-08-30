package com.inHere.entity;

/**
 * 关卡清单实体
 * 
 * @author lwh
 * @date 2016-08-27
 */
public class TbBreakBarrier {

	private Integer barId;// 关卡编号
	private String barName;// 关卡名称
	private Integer labelId;// 标签编号
	private String labelData;// 标签数据
	private String barProcess;// 关卡过程状态
	private String beginTime;// 开始时间
	private String endTime;// 结束时间
	private Boolean hasPass;// 是否已通过
	private String userIdCreate;// 关卡创建者
	private String praiseNum;// 赞数目
	private String lowNum;// 踩数目

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

	public Integer getBarId() {
		return barId;
	}

	public void setBarId(Integer barId) {
		this.barId = barId;
	}

	public String getBarName() {
		return barName;
	}

	public void setBarName(String barName) {
		this.barName = barName == null ? null : barName.trim();
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

	public String getBarProcess() {
		return barProcess;
	}

	public void setBarProcess(String barProcess) {
		this.barProcess = barProcess == null ? null : barProcess.trim();
	}

	public String getBeginTime() {
		return beginTime;
	}

	public void setBeginTime(String beginTime) {
		this.beginTime = beginTime == null ? null : beginTime.trim();
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime == null ? null : endTime.trim();
	}

	public Boolean getHasPass() {
		return hasPass;
	}

	public void setHasPass(Boolean hasPass) {
		this.hasPass = hasPass;
	}

	public String getUserIdCreate() {
		return userIdCreate;
	}

	public void setUserIdCreate(String userIdCreate) {
		this.userIdCreate = userIdCreate == null ? null : userIdCreate.trim();
	}

	@Override
	public String toString() {
		return "TbBreakBarrier [barId=" + barId + ", barName=" + barName + ", labelId=" + labelId + ", labelData="
				+ labelData + ", barProcess=" + barProcess + ", beginTime=" + beginTime + ", endTime=" + endTime
				+ ", hasPass=" + hasPass + ", userIdCreate=" + userIdCreate + ", praiseNum=" + praiseNum + ", lowNum="
				+ lowNum + "]";
	}

}