package com.inHere.dto;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * 用户传输对象
 */
@JsonInclude(Include.NON_NULL)
public class UserDto {
	
	private String user_id;
	private String user_name;
	private String head_img;
	private JSONObject contact_way;
	private Integer sex;
	private String area;
	private Integer school_id;
	private String school;
	private Integer role_id;
	
	private String token;
	
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public String getHead_img() {
		return head_img;
	}
	public void setHead_img(String head_img) {
		this.head_img = head_img;
	}
	public JSONObject getContact_way() {
		return contact_way;
	}
	public void setContact_way(JSONObject contact_way) {
		this.contact_way = contact_way;
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
		this.area = area;
	}
	public Integer getSchool_id() {
		return school_id;
	}
	public void setSchool_id(Integer school_id) {
		this.school_id = school_id;
	}
	public String getSchool() {
		return school;
	}
	public void setSchool(String school) {
		this.school = school;
	}
	public Integer getRole_id() {
		return role_id;
	}
	public void setRole_id(Integer role_id) {
		this.role_id = role_id;
	}
	
}
