package com.inHere.entity;

/**
 * 吐槽+问答的实体类
 * 
 * @author lwh
 *
 */
public class Label {

	private Integer id;// 标签编号
	private String name;// 标签名称

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name == null ? null : name.trim();
	}

	@Override
	public String toString() {
		return "Label [id=" + id + ", name=" + name + "]";
	}

}