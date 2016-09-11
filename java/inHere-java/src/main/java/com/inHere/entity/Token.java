package com.inHere.entity;

import java.util.HashMap;
import java.util.Map;

/**
 * token实体类
 * 
 * @author lwh
 *
 */
public class Token {

	private String key;
	private String user_id;
	private Integer school_id;
	private Integer role_id;

	public Token() {
		super();
	}

	public Token(String key, String user_id, Integer school_id, Integer role_id) {
		super();
		this.key =  key;
		this.user_id = user_id;
		this.school_id = school_id;
		this.role_id = role_id;
	}

	public Token(Map<String, String> userMap) {
		this.user_id = userMap.get("user_id");
		this.school_id = Integer.parseInt(userMap.get("school_id"));
		this.role_id = Integer.parseInt(userMap.get("role_id"));
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key =  key;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public Integer getSchool_id() {
		return school_id;
	}

	public void setSchool_id(Integer school_id) {
		this.school_id = school_id;
	}

	public Integer getRole_id() {
		return role_id;
	}

	public void setRole_id(Integer role_id) {
		this.role_id = role_id;
	}

	@Override
	public String toString() {
		return "Token [key=" + key + ", user_id=" + user_id + ", school_id=" + school_id + ", role_id=" + role_id
				+ ", getKey()=" + getKey() + ", getUser_id()=" + getUser_id() + ", getSchool_id()=" + getSchool_id()
				+ ", getRole_id()=" + getRole_id() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode()
				+ ", toString()=" + super.toString() + "]";
	}

	public Map<String, String> toMap() {
		Map<String, String> map = new HashMap<String, String>();
		map.put("user_id", user_id);
		map.put("school_id", String.valueOf(school_id));
		map.put("role_id", String.valueOf(role_id));
		return map;
	}

	public User toUser(Map<String, String> map) {
		User user = null;
		if (map != null) {
			try {
				user = new User();
				user.setUserId(map.get("user_id"));
				user.setSchoolId(Integer.parseInt(map.get("school_id")));
				user.setRoleId(Integer.parseInt(map.get("role_id")));
			} catch (Exception ex) {
				return user;
			}
		}
		return user;
	}

}
