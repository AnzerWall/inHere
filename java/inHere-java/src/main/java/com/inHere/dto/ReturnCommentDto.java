package com.inHere.dto;

/**
 * 评论数据传输对象
 * 
 * @author lwh
 *
 */
public class ReturnCommentDto {

	private Integer id; //实体编号号
	private String user_id; // 用户账号
	private String name; // 匿名名称
	private Long create_time; // 创建时间
	private String content; // 评论内容
	private Integer praise;//点赞数
	private Integer praised;//是否点赞
	private Integer floor; // 楼层
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getPraise() {
		return praise;
	}

	public void setPraise(Integer praise) {
		this.praise = praise;
	}

	public Integer getPraised() {
		return praised;
	}

	public void setPraised(Integer praised) {
		this.praised = praised;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getCreate_time() {
		return create_time;
	}

	public void setCreate_time(Long create_time) {
		this.create_time = create_time;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Integer getFloor() {
		return floor;
	}

	public void setFloor(Integer floor) {
		this.floor = floor;
	}

	@Override
	public String toString() {
		return "ReturnCommentDto [id=" + id + ", user_id=" + user_id + ", name=" + name + ", create_time=" + create_time
				+ ", content=" + content + ", praise=" + praise + ", praised=" + praised + ", floor=" + floor + "]";
	}

}
