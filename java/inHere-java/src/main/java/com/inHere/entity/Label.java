package com.inHere.entity;

import java.util.HashMap;
import java.util.Map;

/**
 * 吐槽+问答的实体类
 * 
 * @author lwh
 *
 */
public class Label {

	private Integer id;// 标签编号
	private Integer ext_type;// 栏目类别
	private String name;// 标签名称
	private Integer num;// 标签数量

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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name == null ? null : name.trim();
	}

	public Integer getNum() {
		return num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}

	@Override
	public String toString() {
		return "Label [id=" + id + ", ext_type=" + ext_type + ", name=" + name + ", num=" + num + "]";
	}

	public Map<String, Object> toMap() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", this.id);
		map.put("name", this.name);
		return map;
	}

}