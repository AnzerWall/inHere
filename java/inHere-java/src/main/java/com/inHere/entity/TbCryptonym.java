package com.inHere.entity;

import java.util.Date;

/**
 * 匿名表
 * 
 * @author lwh
 *
 */
public class TbCryptonym {

	private Integer cryptId; // 匿名编号
	private String cryptName; // 匿名名称
	private Date createTime; // 匿名时间
	

	public TbCryptonym() {
		super();
	}

	public TbCryptonym(String cryptName) {
		super();
		this.cryptName = cryptName;
	}

	public Integer getCryptId() {
		return cryptId;
	}

	public void setCryptId(Integer cryptId) {
		this.cryptId = cryptId;
	}

	public String getCryptName() {
		return cryptName;
	}

	public void setCryptName(String cryptName) {
		this.cryptName = cryptName;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

}
