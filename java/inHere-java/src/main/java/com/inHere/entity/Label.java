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

	// 非字段
	private Integer comment_num;// 该标签评论数

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

	public Integer getComment_num() {
		return comment_num;
	}

	public void setComment_num(Integer comment_num) {
		this.comment_num = comment_num;
	}

	@Override
	public String toString() {
		return "Label [id=" + id + ", name=" + name + "]";
	}

}