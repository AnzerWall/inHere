package com.inHere.constant;

/**
 * 模块标签常量
 * 
 * @author lwh
 *
 */
public enum LabelEnum {

	// 校内吐槽模块
	Hearsay(1, "小道消息");

	private Integer id;

	private String name;

	private LabelEnum(Integer id, String name) {
		this.id = id;
		this.name = name;
	}

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
		this.name = name;
	}

}
