package com.inHere.dto;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * 有求必应传输对象
 * 
 * @author lwh
 *
 */
@JsonInclude(Include.NON_NULL)
public class ReturnDemandDto {

	private Integer id; // 记录编号
	private Integer ext_type; // 标签ID
	// private String type_name; // 标签名称
	private String text; // 文字描述
	private JSONArray photos; // 图片描述
	private Long create_time; // 创建时间
	private Long update_time; // 更新时间
	private String user_id; // 用户账号
	private JSONObject ext_data; // 类别数据
	private ReturnListDto comment; // 评论列表数据
	private Integer is_end; // 是否解决

	public ReturnDemandDto() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getExt_type() {
		return ext_type;
	}

	public void setExt_type(Integer ext_type) {
		this.ext_type = ext_type;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public JSONArray getPhotos() {
		return photos;
	}

	public void setPhotos(JSONArray photos) {
		this.photos = photos;
	}

	public Long getCreate_time() {
		return create_time;
	}

	public void setCreate_time(Long create_time) {
		this.create_time = create_time;
	}

	public Long getUpdate_time() {
		return update_time;
	}

	public void setUpdate_time(Long update_time) {
		this.update_time = update_time;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public JSONObject getExt_data() {
		return ext_data;
	}

	public void setExt_data(JSONObject ext_data) {
		this.ext_data = ext_data;
	}

	public Integer getIs_end() {
		return is_end;
	}

	public void setIs_end(Integer is_end) {
		this.is_end = is_end;
	}

	public ReturnListDto getComment() {
		return comment;
	}

	public void setComment(ReturnListDto comment) {
		this.comment = comment;
	}

	@Override
	public String toString() {
		return "TasksDto [id=" + id + ", ext_type=" + ext_type + ", text=" + text + ", photos=" + photos
				+ ", create_time=" + create_time + ", update_time=" + update_time + ", user_id=" + user_id
				+ ", ext_data=" + ext_data + ", is_end=" + is_end + "]";
	}

}
