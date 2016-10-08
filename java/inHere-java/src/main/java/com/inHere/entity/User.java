package com.inHere.entity;

import java.io.IOException;
import java.util.Date;
import java.util.List;

/**
 * 用户实体
 */
public class User {

	private String userId;
	private String passwd;
	private String saltKey;
	private String userName;
	private String headImg;
	private String contactWay;
	private Integer sex;
	private String area;
	private Integer schoolId;
	private Integer roleId;
	private Date createTime;
	private Date updateTime;

	// school对象
	private School school;

	// 角色集合
	private List<Roles> roles;

	public School getSchool() {
		return school;
	}

	public void setSchool(School school) {
		this.school = school;
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

	public String getUserName() {
		return userName != null ? userName : "";
	}

	public void setUserName(String userName) {
		this.userName = userName == null ? null : userName.trim();
	}

	public String getHeadImg() throws IOException {
		return headImg == null ? null : new String(headImg.getBytes("ISO-8859-1"), "UTF-8");
	}

	public void setHeadImg(String headImg) {
		this.headImg = headImg == null ? null : headImg.trim();
	}

	public String getContactWay() throws IOException {
		return contactWay == null ? null : new String(contactWay.getBytes("ISO-8859-1"), "UTF-8");
	}

	public void setContactWay(String contactWay) {
		this.contactWay = contactWay == null ? null : contactWay.trim();
	}

	public Integer getSex() {
		return sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area == null ? null : area.trim();
	}

	public Integer getSchoolId() {
		return schoolId;
	}

	public void setSchoolId(Integer schoolId) {
		this.schoolId = schoolId;
	}

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
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

	public List<Roles> getRoles() {
		return roles;
	}

	public void setRoles(List<Roles> roles) {
		this.roles = roles;
	}

	@Override
	public String toString() {
		return "User{" +
				"userId='" + userId + '\'' +
				", passwd='" + passwd + '\'' +
				", saltKey='" + saltKey + '\'' +
				", userName='" + userName + '\'' +
				", headImg='" + headImg + '\'' +
				", contactWay='" + contactWay + '\'' +
				", sex=" + sex +
				", area='" + area + '\'' +
				", schoolId=" + schoolId +
				", roleId=" + roleId +
				", createTime=" + createTime +
				", updateTime=" + updateTime +
				", school=" + school +
				", roles=" + roles +
				'}';
	}
}
