package com.inHere.entity;

/**
 * 用户实体类
 * 
 * @author lwh
 * @date 2016-08-27
 */
public class TbUser {

	private String userId;// 用户账号
	private String passwd; // 密码
	private String saltKey;// 盐值
	private String headImg;// 头像
	private String contactWay;// 联系方式
	private Boolean userSex;// 性别
	private String userArea;// 用户所在地区
	private String userSchool;// 所属学校

	public TbUser() {
	}

	public TbUser(String userId, String passwd, String saltKey) {
		this.userId = userId;
		this.passwd = passwd;
		this.saltKey = saltKey;
	}

	public TbUser(String userId, String passwd, String saltKey, String headImg, String contactWay, Boolean userSex,
			String userArea, String userSchool) {
		this.userId = userId;
		this.passwd = passwd;
		this.saltKey = saltKey;
		this.headImg = headImg;
		this.contactWay = contactWay;
		this.userSex = userSex;
		this.userArea = userArea;
		this.userSchool = userSchool;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId == null ? null : userId.trim();
	}

	public String getPasswd() {
		return passwd;
	}

	public void setPasswd(String passwd) {
		this.passwd = passwd == null ? null : passwd.trim();
	}

	public String getSaltKey() {
		return saltKey;
	}

	public void setSaltKey(String saltKey) {
		this.saltKey = saltKey == null ? null : saltKey.trim();
	}

	public String getHeadImg() {
		return headImg;
	}

	public void setHeadImg(String headImg) {
		this.headImg = headImg == null ? null : headImg.trim();
	}

	public String getContactWay() {
		return contactWay;
	}

	public void setContactWay(String contactWay) {
		this.contactWay = contactWay == null ? null : contactWay.trim();
	}

	public Boolean getUserSex() {
		return userSex;
	}

	public void setUserSex(Boolean userSex) {
		this.userSex = userSex;
	}

	public String getUserArea() {
		return userArea;
	}

	public void setUserArea(String userArea) {
		this.userArea = userArea == null ? null : userArea.trim();
	}

	public String getUserSchool() {
		return userSchool;
	}

	public void setUserSchool(String userSchool) {
		this.userSchool = userSchool == null ? null : userSchool.trim();
	}

	@Override
	public String toString() {
		return "TbUser [userId=" + userId + ", passwd=" + passwd + ", saltKey=" + saltKey + ", headImg=" + headImg
				+ ", contactWay=" + contactWay + ", userSex=" + userSex + ", userArea=" + userArea + ", userSchool="
				+ userSchool + "]";
	}

}