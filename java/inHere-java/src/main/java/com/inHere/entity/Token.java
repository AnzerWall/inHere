package com.inHere.entity;

import com.alibaba.fastjson.JSONArray;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * token实体类
 * 
 * @author lwh
 *
 */
public class Token {

	private String key; // token
	private String user_id; // 用户帐号
	private Integer school_id; // 所在学校
    private JSONArray roles; // 角色集合
	private JSONArray permissions; // 权限集合

	public Token() {
	}

	public Token(String key, String user_id, Integer school_id, JSONArray roles, JSONArray permissions) {
		this.key =  key;
		this.user_id = user_id;
		this.school_id = school_id;
		this.roles = roles;
		this.permissions = permissions;
	}

    public JSONArray getRoles() {
        return roles;
    }

    public void setRoles(JSONArray roles) {
        this.roles = roles;
    }

    public JSONArray getPermissions() {
        return permissions;
    }

    public void setPermissions(JSONArray permissions) {
        this.permissions = permissions;
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

    @Override
    public String toString() {
        return "Token{" +
                "key='" + key + '\'' +
                ", user_id='" + user_id + '\'' +
                ", school_id=" + school_id +
                ", roles=" + roles +
                ", permissions=" + permissions +
                '}';
    }

    public Map<String, String> toMap() {
		Map<String, String> map = new LinkedHashMap<>();
		map.put("user_id", user_id);
		map.put("school_id", String.valueOf(school_id));
        map.put("roles", roles.toString());
        map.put("permissions", permissions.toString());
		return map;
	}

}
