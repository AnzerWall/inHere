package com.inHere.dto;

import java.util.List;

import com.alibaba.fastjson.JSONObject;

/**
 * 任务传输对象
 * 
 * @author lwh
 *
 */
public class TasksDto {

	private Integer item_id; // 记录编号
	private Integer label_id; // 标签ID
	private String label_name; // 标签名称
	private String create_time; // 创建时间
	private String word_desc; // 文字描述
	private List<PhotoDto> photo_desc; // 图片描述
	private String user_id; // 用户账号
	private JSONObject private_data; // 不同标签的任务私有属性
	private Integer is_resolved; // 是否解决

	public TasksDto() {
		super();
	}

	public TasksDto(Integer item_id, Integer label_id, String create_time, String word_desc, String user_id,
			Integer is_resolved) {
		super();
		this.item_id = item_id;
		this.label_id = label_id;
		this.create_time = create_time;
		this.word_desc = word_desc;
		this.user_id = user_id;
		this.is_resolved = is_resolved;
	}

	public Integer getItem_id() {
		return item_id;
	}

	public void setItem_id(Integer item_id) {
		this.item_id = item_id;
	}

	public Integer getLabel_id() {
		return label_id;
	}

	public void setLabel_id(Integer label_id) {
		this.label_id = label_id;
	}

	public String getLabel_name() {
		return label_name;
	}

	public void setLabel_name(String label_name) {
		this.label_name = label_name;
	}

	public String getCreate_time() {
		return create_time;
	}

	public void setCreate_time(String create_time) {
		this.create_time = create_time;
	}

	public String getWord_desc() {
		return word_desc;
	}

	public void setWord_desc(String word_desc) {
		this.word_desc = word_desc;
	}

	public List<PhotoDto> getPhoto_desc() {
		return photo_desc;
	}

	public void setPhoto_desc(List<PhotoDto> photo_desc) {
		this.photo_desc = photo_desc;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public JSONObject getPrivate_data() {
		return private_data;
	}

	public void setPrivate_data(JSONObject private_data) {
		this.private_data = private_data;
	}

	public Integer getIs_resolved() {
		return is_resolved;
	}

	public void setIs_resolved(Integer is_resolved) {
		this.is_resolved = is_resolved;
	}

}
