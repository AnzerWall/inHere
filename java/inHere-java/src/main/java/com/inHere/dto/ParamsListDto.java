package com.inHere.dto;

import java.util.Arrays;

/**
 * 列表参数传输对象
 * 
 * @author lwh
 *
 */
public class ParamsListDto {

	private Integer limit; // 返回条数
	private Integer offset;// 偏移量
	private Integer[] ext_type;// 标签过滤：1-快递，2-转让，3-帮忙, 4-丢失, 5-捡到, 6-走起
	private Integer is_end;// 结束过滤: 0-未结束，1-已结束
	private String creator;// 创建者过滤
	private String order_by;// 排序类别: time 、 praise
	private String orcer;// 倒序正序：desc 、asc

	public Integer getLimit() {
		return limit;
	}

	public void setLimit(Integer limit) {
		this.limit = limit;
	}

	public Integer getOffset() {
		return offset;
	}

	public void setOffset(Integer offset) {
		this.offset = offset;
	}

	public Integer[] getExt_type() {
		return ext_type;
	}

	public void setExt_type(Integer[] ext_type) {
		this.ext_type = ext_type;
	}

	public Integer getIs_end() {
		return is_end;
	}

	public void setIs_end(Integer is_end) {
		this.is_end = is_end;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public String getOrder_by() {
		return order_by;
	}

	public void setOrder_by(String order_by) {
		this.order_by = order_by;
	}

	public String getOrcer() {
		return orcer;
	}

	public void setOrcer(String orcer) {
		this.orcer = orcer;
	}

	@Override
	public String toString() {
		return "ParamsListDto [limit=" + limit + ", offset=" + offset + ", ext_type=" + Arrays.toString(ext_type)
				+ ", is_end=" + is_end + ", creator=" + creator + ", order_by=" + order_by + ", orcer=" + orcer + "]";
	}

}
